/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.chile.core.model.MetaProperty;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.FileStorageAPI;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.core.sys.events.AppContextInitializedEvent;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserSetting;
import com.haulmont.thesis.core.app.*;

import com.company.thesissummer.entity.HasSignatureOrderLoan;
import com.haulmont.thesis.core.app.signature.CryptoProServerSignatureSupport;
import com.haulmont.thesis.core.app.signature.KazakhstanServerSignatureSupport;
import com.haulmont.thesis.core.app.signature.ServerSignatureSupport;
import com.haulmont.thesis.core.app.signature.StandardServerSignatureSupport;
import com.haulmont.thesis.core.config.ThesisConfig;
import com.haulmont.thesis.core.config.TmConfig;
import com.haulmont.thesis.core.entity.*;
import com.haulmont.thesis.core.exception.CardTypeMissingException;
import com.haulmont.thesis.core.exception.HashCalculationException;
import com.haulmont.thesis.core.exception.UnsupportedSignatureException;
import com.haulmont.thesis.core.legaldocintegration.diadoc.common.SignatureInfo;
import com.haulmont.thesis.cryptopro.global.cryptopro.HashAlgorithm;
import com.haulmont.workflow.core.entity.Attachment;
import com.haulmont.workflow.core.entity.Card;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component(SignatureWorkerLoanBean.NAME)
public class SignatureWorkerLoanBean implements SignatureWorker {
    public static final String NAME = "thesissummer_SignatureWorkerLoanBean";


    @Inject
    protected Configuration configuration;
    @Inject
    protected LicensingAPI licensingAPI;
    @Inject
    private FileStorageAPI fileStorage;
    @Inject
    protected Metadata metadata;
    @Inject
    protected Persistence persistence;
    @Inject
    protected UserSettingsWorker userSettingsWorker;
    @Inject
    protected UserSessionSource userSessionSource;
    @Inject
    protected Messages messages;

    protected Log log = LogFactory.getLog(SignatureWorkerBean.class);

    private static final String DATE_FMT = "yyyy-MM-dd HH:mm:ss";

    @EventListener(AppContextInitializedEvent.class)
    public void initCryptoProSecurityProviders() {
        if (licensingAPI.getCryptoProIntegrationEnabled()) {
            List<String> providers = Arrays.asList(
                    "ru.CryptoPro.JCP.JCP", "ru.CryptoPro.Crypto.CryptoProvider", "ru.CryptoPro.reprov.RevCheck");
            for (String providerFQN : providers) {
                addCryptoProProvider(providerFQN);
            }
        }
    }

    protected void addCryptoProProvider(String providerClassFQN) {
        try {
            Class<?> providerClass = Class.forName(providerClassFQN);
            Provider provider = (Provider) providerClass.getDeclaredConstructor().newInstance();
            Security.addProvider(provider);
        } catch (Exception e) {
            log.debug(String.format("Crypto pro provider '%s' not found, jcp is not installed or configured incorrectly", providerClassFQN));
        }
    }

    @Override
    public boolean getUseSignature() {
        return configuration.getConfig(TmConfig.class).getUseSignature() && licensingAPI.getEdition() != 1;
    }

    @Override
    public String getHash(FileDescriptor fileDescriptor, @Nullable String algorithmValue) throws HashCalculationException {
        InputStream inputStream;
        try {
            inputStream = fileStorage.openStream(fileDescriptor);
        } catch (Exception e) {
            log.error("Error getting file hash. File descriptor id: " + fileDescriptor.getId(), e);
            throw new HashCalculationException(e, messages.getMessage(getClass(), "sign.fileError"));
        }
        return getHash(inputStream, algorithmValue);
    }

    @Override
    public <T extends Entity & HasSignatureOrderLoan> String getHash(T signedEntity, @Nullable String algorithmValue) {
        FieldInfoContainer fieldInfoContainer = signedEntity.getFieldInfoContainer();
        if (fieldInfoContainer == null) {
            if (signedEntity instanceof Card) {
                throw new CardTypeMissingException("CardType is missing for " + signedEntity.getMetaClass().getName());
            } else {
                return "";
            }
        }

        MetaClass entityMetaClass = metadata.getClass(signedEntity.getClass());
        MetaClass docOfficeDataMetaClass = metadata.getClass(DocOfficeData.class);

        StringBuilder cardText = new StringBuilder("");
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager em = persistence.getEntityManager();
            T reloadedSignedEntity = (T) em.find(signedEntity.getClass(), signedEntity.getId());

            for (FieldInfo fieldInfo : reloadedSignedEntity.getFieldInfoContainer().getFields(false)) {
                if (fieldInfo.getSigned()) {
                    boolean docOfficeDataProperty = false;
                    MetaProperty property = entityMetaClass.getProperty(fieldInfo.getName());
                    if (property == null && signedEntity instanceof Doc) {
                        property = docOfficeDataMetaClass.getProperty(fieldInfo.getName());
                        docOfficeDataProperty = true;
                    }
                    if (property != null) {
                        Object value = docOfficeDataProperty
                                ? ((Doc) reloadedSignedEntity).getDocOfficeData().getValue(property.getName())
                                : reloadedSignedEntity.getValue(property.getName());
                        if (value instanceof Entity) {
                            cardText.append(((Entity) value).getId());
                        } else {
                            cardText.append(value);
                        }
                    } else {
                        log.warn(fieldInfo.getName());
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        } finally {
            tx.end();
        }

        return getHash(cardText.toString(), algorithmValue);
    }

    @Override
    public <T extends Entity & HasSignatureOrderLoan> List<String> getSignedProperties(T signedEntity) {
        FieldInfoContainer fieldInfoContainer = signedEntity.getFieldInfoContainer();
        if (fieldInfoContainer == null) {
            if (signedEntity instanceof Card) {
                throw new CardTypeMissingException("CardType is missing for " + signedEntity.getMetaClass().getName());
            } else {
                return Collections.emptyList();
            }
        }

        MetaClass entityMetaClass = metadata.getSession().getClass(signedEntity.getClass());
        MetaClass docOfficeDataClass = metadata.getSession().getClass(DocOfficeData.class);

        List<String> signedProperties = new ArrayList<>();
        for (FieldInfo fieldInfo : fieldInfoContainer.getFields(false)) {
            if (fieldInfo.getSigned()) {
                MetaProperty property = entityMetaClass.getProperty(fieldInfo.getName());
                if (property != null) {
                    signedProperties.add(property.getName());
                } else if (signedEntity instanceof Doc) {
                    property = docOfficeDataClass.getProperty(fieldInfo.getName());
                    if (property != null) {
                        signedProperties.add(property.getName());
                    }
                }
            }
        }
        return signedProperties;
    }

    @Override
    public List<SignatureEntity> getSignatureEntities(Attachment attachment) {
        return getSignatureEntities(attachment, false);
    }

    @Override
    public List<SignatureEntity> getInternalSignatureEntities(Attachment attachment) {
        return getInternalSignatureEntities(attachment, false);
    }

    @Override
    public List<SignatureEntity> getEdmInboundSignatureEntities(Attachment attachment) {
        return getEdmInboundSignatureEntities(attachment, false);
    }

    protected boolean isEdmInboundSignatureEntryElement(Element element) {
        Element edmInboundElem = element.element("edmInbound");
        if (edmInboundElem != null) {
            String edmInboundStr = element.elementText("edmInbound").trim();
            return Boolean.valueOf(edmInboundStr);
        }
        return false;
    }

    protected List<SignatureEntity> getSignatureEntities(Attachment attachment, boolean shortInfo, boolean withInternal, boolean withEdmInbound) {
        if (StringUtils.isBlank(attachment.getSignatures()))
            return Collections.emptyList();

        List<SignatureEntity> list = new ArrayList<>();
        Transaction tx = persistence.createTransaction();
        try {
            Document document = Dom4j.readDocument(attachment.getSignatures());
            Element rootElem = document.getRootElement();
            for (Element element : Dom4j.elements(rootElem, "signatureEntry")) {

                boolean isEdmInbound = isEdmInboundSignatureEntryElement(element);

                if ((isEdmInbound && withEdmInbound) || (!isEdmInbound && withInternal)) {

                    SignatureEntity entity = new SignatureEntity();

                    entity.setName(attachment.getFile().getName());

                    entity.setEdmInbound(isEdmInbound);

                    if (entity.getEdmInbound()) {
                        if (element.element("edmBoxId") != null) {
                            String edmBoxId = element.elementText("edmBoxId").trim();
                            entity.setEdmBoxId(edmBoxId);
                            if (StringUtils.isNotEmpty(edmBoxId)) {
                                EntityManager em = persistence.getEntityManager();
                                SubscriberEdm subscriberEdm =
                                        em.createQuery("select se from ts$SubscriberEdm se where se.boxId = :boxId", SubscriberEdm.class)
                                                .setViewName("withContractor")
                                                .setParameter("boxId", edmBoxId)
                                                .getFirstResult();
                                entity.setSubscriberEdm(subscriberEdm);
                            }
                        }
                    } else {
                        Element userElem = element.element("user");
                        if (userElem.element("id") != null) {
                            UUID userId = UuidProvider.fromString(userElem.elementText("id").trim());
                            EntityManager em = persistence.getEntityManager();
                            User user = em.find(User.class, userId);
                            entity.setUser(user);
                        }
                    }

                    if (element.element("date") != null) {
                        String dateStr = element.elementText("date").trim();
                        try {
                            Date date = new SimpleDateFormat(DATE_FMT).parse(dateStr);
                            entity.setDate(date);
                        } catch (ParseException e) {
                            log.error("Date parsing error", e);
                        }
                    }

                    if (!shortInfo) {
                        String signatureType = element.elementText("signatureType").trim();
                        entity.setSignatureType(signatureType);

                        String signatureBase64 = element.elementText("signature").trim();

                        ServerSignatureSupport support = getUserSignatureType(signatureType);
                        CertPath certPath = decodeCertPath(element.elementText("cert").trim());
                        entity.setCertPath(support.getCertPath(certPath, signatureBase64, openSignedDataStream(attachment.getFile(), support)));

                        boolean valid = validateSignature(openSignedDataStream(attachment.getFile(), support), certPath, signatureBase64, support);
                        entity.setSignatureCorrect(valid);
                    }

                    list.add(entity);
                }
            }

            tx.commit();
        } finally {
            tx.end();
        }
        return list;
    }

    @Override
    public List<SignatureEntity> getSignatureEntities(Attachment attachment, boolean shortInfo) {
        return getSignatureEntities(attachment, shortInfo, true, true);
    }

    @Override
    public List<SignatureEntity> getInternalSignatureEntities(Attachment attachment, boolean shortInfo) {
        return getSignatureEntities(attachment, shortInfo, true, false);
    }

    @Override
    public List<SignatureEntity> getEdmInboundSignatureEntities(Attachment attachment, boolean shortInfo) {
        return getSignatureEntities(attachment, shortInfo, false, true);
    }

    @Override
    public <T extends Entity & HasSignatureOrderLoan> List<SignatureEntity> getSignatureEntities(T signedEntity) {
        String signatures = signedEntity.getSignatures();
        if (StringUtils.isBlank(signatures)) {
            return Collections.emptyList();
        }

        List<SignatureEntity> list = new ArrayList<>();
        Document document = Dom4j.readDocument(signatures);
        Element rootElem = document.getRootElement();

        Transaction tx = persistence.createTransaction();
        try {
            for (Element element : Dom4j.elements(rootElem, "signatureEntry")) {
                SignatureEntity entity = new SignatureEntity();

                Element userElem = element.element("user");
                UUID userId = UuidProvider.fromString(userElem.elementText("id").trim());
                EntityManager em = persistence.getEntityManager();
                User user = em.find(User.class, userId);
                entity.setUser(user);
                if (signedEntity instanceof Doc) {
                    signedEntity = (T) em.find(signedEntity.getClass(), signedEntity.getId());
                }

                String dateStr = element.elementText("date").trim();
                try {
                    Date date = new SimpleDateFormat(DATE_FMT).parse(dateStr);
                    entity.setDate(date);
                } catch (ParseException e) {
                    log.error("Date parsing error", e);
                }

                String signedProperties = element.elementText("signedProperties");
                if (StringUtils.isBlank(signedProperties)) {
                    log.warn("No signedProperties stored with signature for entity " + signedEntity.getId());
                } else {
                    MetaClass cardMetaClass = metadata.getSession().getClass(signedEntity.getClass());
                    MetaClass docOfficeDataMetaClass = metadata.getClass(DocOfficeData.class);

                    StringBuilder fieldNames = new StringBuilder("");
                    StringBuilder fieldValues = new StringBuilder("");
                    String[] props = signedProperties.split(";");
                    for (int i = 0; i < props.length; i++) {
                        String propName = props[i];

                        MetaProperty metaProperty = cardMetaClass.getProperty(propName);
                        boolean docProperty = true;
                        if (metaProperty == null && signedEntity instanceof Doc) {
                            metaProperty = docOfficeDataMetaClass.getProperty(propName);
                            docProperty = false;
                        }
                        if (metaProperty == null) {
                            throw new RuntimeException("Illegal property : " + propName);
                        }
                        fieldNames.append(messages.getTools().getPropertyCaption(metaProperty));
                        if (i != props.length - 1) {
                            fieldNames.append(", ");
                        }
                        Object value = docProperty ? signedEntity.getValue(propName) : ((Doc) signedEntity).getDocOfficeData().getValue(propName);

                        if (value instanceof Entity) {
                            fieldValues.append(((Entity) value).getId());
                        } else {
                            fieldValues.append(value);
                        }

                    }
                    String signatureType = element.elementText("signatureType").trim();
                    entity.setSignatureType(signatureType);

                    String signatureBase64 = element.elementText("signature").trim();

                    ServerSignatureSupport support = getUserSignatureType(signatureType);
                    CertPath certPath = decodeCertPath(element.elementText("cert").trim());
                    entity.setCertPath(support.getCertPath(certPath, signatureBase64, openSignedDataStream(fieldValues.toString(), support)));

                    boolean valid = validateSignature(openSignedDataStream(fieldValues.toString(), support), certPath, signatureBase64, support);
                    entity.setSignatureCorrect(valid);
                    entity.setName(fieldNames.toString());

                    list.add(entity);
                }
            }

            tx.commit();
        } finally {
            tx.end();
        }
        return list;
    }

    @Override
    public String generateAttachedSignatureWithAllSigners(Attachment attachment, String signatureType) throws FileStorageException {
        ServerSignatureSupport support = getUserSignatureType(signatureType);
        return support.generateAttachedSignatureWithAllSigners(attachment);
    }

    @Override
    public String filteredSignatures(String signatures, String filteredSignatureType) {
        if (StringUtils.isEmpty(signatures))
            return "";
        Document out = DocumentHelper.createDocument();
        Element outRoot = out.addElement("signatures");

        Document document = Dom4j.readDocument(signatures);
        Element rootElem = document.getRootElement();
        boolean hasSign = false;
        for (Element element : Dom4j.elements(rootElem, "signatureEntry")) {
            String signatureType = element.elementText("signatureType");
            if (filteredSignatureType.equals(signatureType)) {
                outRoot.add(element.createCopy());
                hasSign = true;
            }
        }

        if (!hasSign)
            return "";

        StringWriter writer = new StringWriter();
        Dom4j.writeDocument(out, true, writer);
        return writer.toString();
    }

    protected void addEdmInboundSignatureEntry(Element rootEl, SignatureInfo signatureInfo) {
        Element signatureEntryEl = rootEl.addElement("signatureEntry");
        Element edmInboundEl = signatureEntryEl.addElement("edmInbound");
        edmInboundEl.setText(String.valueOf(true));
        if (StringUtils.isNotEmpty(signatureInfo.getBoxId())) {
            Element edmBoxIdEl = signatureEntryEl.addElement("edmBoxId");
            edmBoxIdEl.setText(signatureInfo.getBoxId());
        }
        Element signatureDateEl = signatureEntryEl.addElement("date");
        if (signatureInfo.getCreationDate() != null) {
            signatureDateEl.setText(new SimpleDateFormat(DATE_FMT).format(signatureInfo.getCreationDate()));
        }
        signatureEntryEl.addElement("cert");
        Element signatureTypeEl = signatureEntryEl.addElement("signatureType");
        signatureTypeEl.setText("CryptoPro");
        Element signatureEl = signatureEntryEl.addElement("signature");
        signatureEl.setText(signatureInfo.getDataBase64());
    }

    @Override
    public String addEdmInboundSignature(String signatures, SignatureInfo signatureInfo) {
        org.dom4j.Document doc = Dom4j.readDocument(signatures);
        Element rootEl = doc.getRootElement();
        addEdmInboundSignatureEntry(rootEl, signatureInfo);
        StringWriter writer = new StringWriter();
        Dom4j.writeDocument(doc, true, writer);
        return writer.toString();
    }

    @Override
    public String createEdmInboundSignature(SignatureInfo signatureInfo) {
        org.dom4j.Document doc = DocumentHelper.createDocument();
        Element rootEl = doc.addElement("signatures");
        addEdmInboundSignatureEntry(rootEl, signatureInfo);
        StringWriter writer = new StringWriter();
        Dom4j.writeDocument(doc, true, writer);
        return writer.toString();
    }

    @Override
    public String getUserSignatureTypeSetting() {
        UserSetting userSetting = loadSignatureTypeUserSetting();
        return userSetting != null && StringUtils.isNotEmpty(userSetting.getValue())
                ? userSetting.getValue()
                : configuration.getConfig(ThesisConfig.class).getUserSignatureTypeDefault();
    }

    @Override
    public String getHash(String source, String algorithmValue) {
        return getHash(IOUtils.toInputStream(source), algorithmValue);
    }

    @Override
    public boolean isAlgorithmSupported(String algorithmValue) {
        return HashAlgorithm.fromValue(algorithmValue) != null;
    }

    protected String getHash(InputStream source, String algorithmValue) {
        ServerSignatureSupport support = getUserSignatureType();
        return support.getHash(source, algorithmValue);
    }

    protected UserSetting loadSignatureTypeUserSetting() {
        UserSetting userSetting;
        Transaction tx = persistence.createTransaction();
        try {
            userSetting = userSettingsWorker.loadSetting(ClientType.WEB, "signatureType", userSessionSource.getUserSession().getCurrentOrSubstitutedUser());
            tx.commit();
        } finally {
            tx.end();
        }
        return userSetting;
    }

    protected InputStream openSignedDataStream(FileDescriptor fileDescriptor, ServerSignatureSupport support) {
        InputStream signedData;
        try {
            signedData = fileStorage.openStream(fileDescriptor);
        } catch (FileStorageException e) {
            log.error("Error open signed data stream", e);
            return null;
        }
        return support.openSignedDataStream(signedData);
    }

    protected InputStream openSignedDataStream(String source, ServerSignatureSupport support) {
        return support.openSignedDataStream(IOUtils.toInputStream(source));
    }

    protected ServerSignatureSupport getUserSignatureType() {
        return getUserSignatureType(getUserSignatureTypeSetting());
    }

    protected ServerSignatureSupport getUserSignatureType(String signatureType) {
        if (StringUtils.isEmpty(signatureType))
            signatureType = configuration.getConfig(ThesisConfig.class).getUserSignatureTypeDefault();

        if (SignatureConstants.STANDARD_NAME.equals(signatureType))
            return new StandardServerSignatureSupport();
        else if (SignatureConstants.CRYPTO_PRO_NAME.equals(signatureType)
                && AppBeans.get(LicensingService.class).getCryptoProIntegrationEnabled())
            return new CryptoProServerSignatureSupport();
//        TODO licence
        else if (SignatureConstants.KAZAKHSTAN_DIGITAL_SIGNATURE.equals(signatureType))
            return new KazakhstanServerSignatureSupport();
        else
            throw new UnsupportedSignatureException("Unsupported SignatureSupport name");
    }

    protected boolean validateSignature(InputStream sourceBase64, CertPath certPath, String signatureBase64, ServerSignatureSupport support) {
        byte[] signature = org.apache.commons.codec.binary.Base64.decodeBase64(signatureBase64.getBytes());
        Base64InputStream source = new Base64InputStream(sourceBase64);

        return support.validateSignature(source, certPath, signature);
    }

    private CertPath decodeCertPath(String certPathBase64) {
        if (!StringUtils.isBlank(certPathBase64)) {
            byte[] bytes = Base64.decodeBase64(certPathBase64.getBytes());
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
                CertPath certPath = certFactory.generateCertPath(bis);
                return certPath;
            } catch (CertificateException e) {
                log.error("CertPath decoding error", e);
            }
        }

        return null;
    }

    @Nullable
    public String getLastSignatureByUser(Attachment attachment, User signer) {

        String lastSignSignature = null;
        Date lastSignDate = null;

        if (StringUtils.isNotBlank(attachment.getSignatures())) {
            Element attachmentSignatures = Dom4j.readDocument(attachment.getSignatures()).getRootElement();
            for (Element element : Dom4j.elements(attachmentSignatures, "signatureEntry")) {
                if (!isEdmInboundSignatureEntryElement(element)) {
                    try {
                        UUID userId = UuidProvider.fromString(element.element("user").elementText("id").trim());
                        Date date = new SimpleDateFormat(DATE_FMT).parse(element.elementText("date").trim());
                        if (date != null) {
                            if (userId.equals(signer.getId()) && (lastSignDate == null || lastSignDate.before(date))) {
                                lastSignDate = date;
                                lastSignSignature = element.elementText("signature").trim();
                            }
                        }
                    } catch (ParseException e) {
                        log.error("Date parsing error", e);
                        return null;
                    }
                }
            }
        }

        return lastSignSignature;
    }

    @Nullable
    public boolean isUserSignatureExists(Attachment attachment, User signer) {

        if (StringUtils.isNotBlank(attachment.getSignatures())) {
            Element attachmentSignatures = Dom4j.readDocument(attachment.getSignatures()).getRootElement();
            for (Element element : Dom4j.elements(attachmentSignatures, "signatureEntry")) {
                if (!isEdmInboundSignatureEntryElement(element)) {
                    try {
                        UUID userId = UuidProvider.fromString(element.element("user").elementText("id").trim());
                        Date date = new SimpleDateFormat(DATE_FMT).parse(element.elementText("date").trim());
                        if (date != null && userId.equals(signer.getId())) return true;
                    } catch (ParseException e) {
                        log.error("Date parsing error", e);
                        return false;
                    }
                }
            }
        }

        return false;
    }

}
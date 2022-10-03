/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.thesis.core.entity.SignatureEntity;
import com.haulmont.thesis.core.exception.HashCalculationException;
import com.haulmont.thesis.core.legaldocintegration.diadoc.common.SignatureInfo;
import com.haulmont.workflow.core.entity.Attachment;
import com.company.thesissummer.entity.HasSignatureOrderLoan;

import javax.annotation.Nullable;
import java.util.List;

public interface SignatureWorker {
    boolean getUseSignature();

    String getHash(FileDescriptor fileDescriptor, @Nullable String algorithmValue) throws HashCalculationException;

    <T extends Entity & HasSignatureOrderLoan> String getHash(T signedEntity, @Nullable String algorithmValue);

    <T extends Entity & HasSignatureOrderLoan> List<String> getSignedProperties(T signedEntity);

    List<SignatureEntity> getSignatureEntities(Attachment attachment);

    List<SignatureEntity> getInternalSignatureEntities(Attachment attachment);

    List<SignatureEntity> getEdmInboundSignatureEntities(Attachment attachment);

    List<SignatureEntity> getSignatureEntities(Attachment attachment, boolean shortInfo);

    List<SignatureEntity> getInternalSignatureEntities(Attachment attachment, boolean shortInfo);

    List<SignatureEntity> getEdmInboundSignatureEntities(Attachment attachment, boolean shortInfo);

    <T extends Entity & HasSignatureOrderLoan> List<SignatureEntity> getSignatureEntities(T signedEntity);

    String generateAttachedSignatureWithAllSigners(Attachment attachment, String signatureType) throws FileStorageException;

    String filteredSignatures(String signatures, String filteredSignatureType);

    String addEdmInboundSignature(String signatures, SignatureInfo signatureInfo);

    String createEdmInboundSignature(SignatureInfo signatureInfo);

    String getUserSignatureTypeSetting();

    String getHash(String source, String algorithmValue);

    boolean isAlgorithmSupported(String algorithmValue);
}

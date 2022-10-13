/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.ViewRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Hashtable;

@Service(QRCodeGeneratorService.NAME)
public class QRCodeGeneratorServiceBean implements QRCodeGeneratorService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(QRCodeGeneratorServiceBean.class);
    private final int size = 500;

    private final int white = 255 << 16 | 255 << 8 | 255;
    private final int black = 0;

    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Inject
    private ViewRepository viewRepository;

    @Inject
    private FileLoader fileLoader;

    public byte[] getQRCodeByteArray(String content) throws IOException {
        BitMatrix qrCode;
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BufferedImage image;
            qrCode = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);

            image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    image.setRGB(i, j, qrCode.get(i, j) ? black : white); // set pixel one by one
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public FileDescriptor getQRCode(String content) {

        try {
            byte[] bytes = getQRCodeByteArray(content);
            InputStream inputStream = new ByteArrayInputStream(bytes);

            FileDescriptor initial = metadata.create(FileDescriptor.class);
            initial.setName(content);
            initial.setCreateDate(new Date());

            FileDescriptor fileDescriptor = dataManager.commit(initial, viewRepository.getView(FileDescriptor.class, "browse"));

            fileDescriptor.setExtension("png");
            fileDescriptor.setCreateDate(new Date());

            fileLoader.saveStream(fileDescriptor, () -> inputStream);


            return fileDescriptor;
        }catch (Exception e){
            log.error("Error qr generation", e);
        }
        return null;
    }

}
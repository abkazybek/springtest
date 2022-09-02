/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.ordersubsidiy

import com.company.thesissummer.entity.OrderIns
import com.company.thesissummer.entity.OrderSubsidiy
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.haulmont.thesis.core.app.barcode.BarcodeAPI

import javax.imageio.ImageIO
import javax.inject.Inject
import java.awt.image.BufferedImage

@SuppressWarnings("GroovyUnusedDeclaration")
class SubsidiyQR {
    @Inject
    private BarcodeAPI barcodeAPI

    private final int size = 500;

    private final int white = 255 << 16 | 255 << 8 | 255;
    private final int black = 0;

    public Object getQR(Map<String, Object> params) {

        OrderSubsidiy orderSubsidiy = params['entity'] as OrderSubsidiy;

        return [['QRcodeimage': getPDF417Barcode(orderSubsidiy.getCreator().getLogin())]]

    }

    public byte[] getPDF417Barcode(String entity) throws IOException {
        BitMatrix qrCode;
        try {
            BufferedImage image;
            qrCode = new QRCodeWriter().encode(entity, BarcodeFormat.QR_CODE, size, size);

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
}

/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.doc

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.thesis.core.app.SignatureService
import com.haulmont.thesis.core.app.barcode.BarcodeAPI
import com.haulmont.thesis.core.entity.Doc
import com.haulmont.thesis.core.entity.SimpleDoc
import com.haulmont.workflow.core.entity.CardRole

import javax.imageio.ImageIO
import javax.inject.Inject
import java.awt.image.BufferedImage

@SuppressWarnings("GroovyUnusedDeclaration")
class DocСardRole {
    @Inject
    public DataManager dataManager;


    public List<Map<String, Object>> getJournalResult(Map<String, Object> params) {

        SimpleDoc doc = params['entity'] as SimpleDoc;

        LoadContext<CardRole> loadContext = LoadContext.create(CardRole.class)
                .setQuery(LoadContext.createQuery("select e from wf\$CardRole e where e.card.id = :cardId")
                        .setParameter("cardId", doc.getId()))
                .setView("report_card");

        loadContext.setLoadDynamicAttributes(true).da;

        List<CardRole> list = dataManager.loadList(loadContext)

        List<HashMap> soglasav = new ArrayList<>()

        for (CardRole cardRole : list) {
            Map<String, Object> map = new HashMap();
            map.put("position", cardRole.getUser().getPosition());
            map.put("name", cardRole.getUser().getName());
            soglasav.add(map);
        }

        return soglasav

    }
}

/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderreturnneispsub

import com.company.thesissummer.entity.OrderLoan
import com.company.thesissummer.entity.OrderReturnNeispSub
import com.company.thesissummer.entity.table1c.OrderReturnNeispSub1C
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.thesis.core.app.barcode.BarcodeAPI
import com.haulmont.thesis.core.entity.MeetingDoc
import com.haulmont.workflow.core.entity.CardRole

import javax.imageio.ImageIO
import javax.inject.Inject
import java.awt.image.BufferedImage

@SuppressWarnings("GroovyUnusedDeclaration")
class NaReturnNeispSubTable {
    @Inject
    public DataManager dataManager;  //ORDER_RETURN_NEISP_SUB_ID

    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderReturnNeispSub orderReturnNeispSub = params['entity'] as OrderReturnNeispSub;

        List<OrderReturnNeispSub1C> list = dataManager.load(OrderReturnNeispSub1C.class)
                .query("select e from thesissummer\$OrderReturnNeispSub1C e where e.orderReturnNeispSub.id = :orderReturnNeispSubId")
                .parameter("orderReturnNeispSubId", orderReturnNeispSub.getId())
                .view("table_returnneisp")
                .list();


        List<HashMap> table = new ArrayList<>()

        for (OrderReturnNeispSub1C orderReturnNeispSub1C : list) {
            Map<String, Object> map = new HashMap();
            map.put("nomer", orderReturnNeispSub1C.getNomer());
            map.put("naimenovanieZaemchika", orderReturnNeispSub1C.getNaimenovanieZaemchika());
            map.put("nomerDataKreditnogoDogovora", orderReturnNeispSub1C.getNomerDataKreditnogoDogovora());
            map.put("summaSubsidiyOtkloneniya", orderReturnNeispSub1C.getSummaSubsidiyOtkloneniya());
            map.put("dogovorSubsidirovaniya", orderReturnNeispSub1C.getDogovorSubsidirovaniya());
            map.put("poyasnenie", orderReturnNeispSub1C.getPoyasnenie());

            table.add(map);
        }

        return table

    }
}

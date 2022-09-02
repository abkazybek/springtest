/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.ordertranfer


import com.company.thesissummer.entity.OrderReturnNeispSub
import com.company.thesissummer.entity.OrderTranfer
import com.company.thesissummer.entity.table1c.OrderReturnNeispSub1C
import com.company.thesissummer.entity.table1c.OrderTranfer1C
import com.haulmont.cuba.core.global.DataManager

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class TransferTable {
    @Inject
    public DataManager dataManager;  //ORDER_RETURN_NEISP_SUB_ID

    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderTranfer orderTranfer = params['entity'] as OrderTranfer;

        List<OrderTranfer1C> list = dataManager.load(OrderTranfer1C.class)
                .query("select e from thesissummer\$OrderTranfer1C e where e.orderTranfer.id = :orderTranferId")
                .parameter("orderTranferId", orderTranfer.getId())
                .view("table_transfer")
                .list();


        List<HashMap> table = new ArrayList<>()

        for (OrderTranfer1C orderTranfer1C : list) {
            Map<String, Object> map = new HashMap();
            map.put("number", orderTranfer1C.getNumber());
            map.put("zaemchik", orderTranfer1C.getZaemchik());
            map.put("iin", orderTranfer1C.getIin());
            map.put("nomerandDataKreditnogoDogovora", orderTranfer1C.getNomerandDataKreditnogoDogovora());
            map.put("summaSubsidiiKreditnogoDogovora", orderTranfer1C.getSummaSubsidiiKreditnogoDogovora());

            table.add(map);
        }

        return table

    }
}

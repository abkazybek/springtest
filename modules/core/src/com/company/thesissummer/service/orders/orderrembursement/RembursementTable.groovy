/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderrembursement

import com.company.thesissummer.entity.OrderRembursement
import com.company.thesissummer.entity.OrderReturnNeispSub
import com.company.thesissummer.entity.table1c.OrderRembursement1c
import com.company.thesissummer.entity.table1c.OrderReturnNeispSub1C
import com.haulmont.cuba.core.global.DataManager

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class RembursementTable {
    @Inject
    public DataManager dataManager;  //ORDER_RETURN_NEISP_SUB_ID

    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderRembursement orderRembursement = params['entity'] as OrderRembursement;

        List<OrderRembursement1c> list = dataManager.load(OrderRembursement1c.class)
                .query("select e from thesissummer\$OrderRembursement1c e where e.orderRembursement.id = :orderRembursementId")
                .parameter("orderRembursementId", orderRembursement.getId())
                .view("table_orderRembursement")
                .list();


        List<HashMap> table = new ArrayList<>()

        for (OrderRembursement1c orderRembursement1c : list) {
            Map<String, Object> map = new HashMap();
            map.put("number", orderRembursement1c.getNumber());
            map.put("nameofborrower", orderRembursement1c.getNameofborrower());
            map.put("iin", orderRembursement1c.getIin());
            map.put("nomerAndDataKreditnogogDogovora", orderRembursement1c.getNomerAndDataKreditnogogDogovora());
            map.put("summaSub", orderRembursement1c.getSummaSub());
            map.put("iikZaem", orderRembursement1c.getIikZaem());
            map.put("bank", orderRembursement1c.getBank());

            table.add(map);
        }

        return table

    }
}

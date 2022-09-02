/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderrevaluation

import com.company.thesissummer.entity.OrderRembursement
import com.company.thesissummer.entity.OrderRevaluation
import com.company.thesissummer.entity.table1c.OrderRembursement1c
import com.company.thesissummer.entity.table1c.OrderRevaluation1C
import com.haulmont.cuba.core.global.DataManager

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class RevaluationTable {
    @Inject
    public DataManager dataManager;  //ORDER_RETURN_NEISP_SUB_ID

    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderRevaluation orderRevaluation = params['entity'] as OrderRevaluation;

        List<OrderRevaluation1C> list = dataManager.load(OrderRevaluation1C.class)
                .query("select e from thesissummer\$OrderRevaluation1C e where e.orderRevaluation.id = :orderRevaluationId")
                .parameter("orderRevaluationId", orderRevaluation.getId())
                .view("table_orderRevaluation")
                .list();


        List<HashMap> table = new ArrayList<>()

        for (OrderRevaluation1C orderRevaluation1C : list) {
            Map<String, Object> map = new HashMap();
            map.put("number", orderRevaluation1C.getNumber());
            map.put("zalogodatel", orderRevaluation1C.getZalogodatel());
            map.put("dogovorswithcollateral", orderRevaluation1C.getDogovorswithcollateral());
            map.put("predmet", orderRevaluation1C.getPredmet());
            map.put("tekushayaStoimost", orderRevaluation1C.getTekushayaStoimost());
            map.put("pereocenennyaStoimos", orderRevaluation1C.getPereocenennyaStoimost());
            map.put("itogovyaSumma", orderRevaluation1C.getItogovyaSumma());

            table.add(map);
        }

        return table

    }
}

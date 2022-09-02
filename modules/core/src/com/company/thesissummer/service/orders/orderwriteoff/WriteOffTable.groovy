/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderwriteoff


import com.company.thesissummer.entity.OrderWriteOff
import com.company.thesissummer.entity.table1c.OrderWriteOff1C
import com.haulmont.cuba.core.global.DataManager

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class WriteOffTable {
    @Inject
    public DataManager dataManager;  //ORDER_RETURN_NEISP_SUB_ID

    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderWriteOff orderWriteOff = params['entity'] as OrderWriteOff;

        List<OrderWriteOff1C> list = dataManager.load(OrderWriteOff1C.class)
                .query("select e from thesissummer\$OrderWriteOff1C e where e.orderWriteOff.id = :orderWriteOffId")
                .parameter("orderWriteOffId", orderWriteOff.getId())
                .view("table_orderwriteoff")
                .list();


        List<HashMap> table = new ArrayList<>()

        for (OrderWriteOff1C orderWriteOff1C : list) {
            Map<String, Object> map = new HashMap();
            map.put("number", orderWriteOff1C.getNumber());
            map.put("naimenovanieZalog", orderWriteOff1C.getNaimenovanieZalog());
            map.put("dogovorSvyazSObespech", orderWriteOff1C.getDogovorSvyazSObespech());
            map.put("zalogStoimost", orderWriteOff1C.getZalogStoimost());

            table.add(map);
        }

        return table

    }
}

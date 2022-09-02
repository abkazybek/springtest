/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderPosting

import com.company.thesissummer.entity.OrderPosting
import com.company.thesissummer.entity.OrderRembursement
import com.company.thesissummer.entity.table1c.OrderPosting1C
import com.company.thesissummer.entity.table1c.OrderRembursement1c
import com.company.thesissummer.entity.table1c.OrderReturnNeispSub1C
import com.haulmont.cuba.core.global.DataManager

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class PostingTable {
    @Inject
    public DataManager dataManager;  //ORDER_RETURN_NEISP_SUB_ID

    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderPosting orderPosting = params['entity'] as OrderPosting;

        List<OrderPosting1C> list = dataManager.load(OrderPosting1C.class)
                .query("select e from thesissummer\$OrderPosting1C e where e.orderPosting.id = :orderPostingId")
                .parameter("orderPostingId", orderPosting.getId())
                .view("table_orderPosting")
                .list();


        List<HashMap> table = new ArrayList<>()

        for (OrderPosting1C orderPosting1C : list) {
            Map<String, Object> map = new HashMap();
            map.put("number", orderPosting1C.getNumber());
            map.put("nameofpledger", orderPosting1C.getNameofpledger());
            map.put("dogovorwithcollateral", orderPosting1C.getDogovorwithcollateral());
            map.put("zalogStoimost", orderPosting1C.getZalogStoimost());

            table.add(map);
        }

        return table

    }
}

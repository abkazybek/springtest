/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderreturnneispsub


import com.company.thesissummer.entity.OrderReturnNeispSub
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.workflow.core.entity.CardRole

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class NaReturnNeispSubCardRole {
    @Inject
    public DataManager dataManager;


    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderReturnNeispSub orderReturnNeispSub = params['entity'] as OrderReturnNeispSub;

        LoadContext<CardRole> loadContext = LoadContext.create(CardRole.class)
                .setQuery(LoadContext.createQuery("select e from wf\$CardRole e where e.card.id = :cardId")
                        .setParameter("cardId", orderReturnNeispSub.getId()))
                .setView("report_card");

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

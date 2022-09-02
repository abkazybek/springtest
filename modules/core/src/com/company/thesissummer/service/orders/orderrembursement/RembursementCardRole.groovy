/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.service.orders.orderrembursement

import com.company.thesissummer.entity.OrderIns
import com.company.thesissummer.entity.OrderRembursement
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.workflow.core.entity.CardRole

import javax.inject.Inject

@SuppressWarnings("GroovyUnusedDeclaration")
class RembursementCardRole {
    @Inject
    public DataManager dataManager;


    public List<Map<String, Object>> getJournalResult(Map<String,Object> params) {

        OrderRembursement orderRembursement = params['entity'] as OrderRembursement;

        LoadContext<CardRole> loadContext = LoadContext.create(CardRole.class)
                .setQuery(LoadContext.createQuery("select e from wf\$CardRole e where e.card.id = :cardId")
                        .setParameter("cardId", orderRembursement.getId()))
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

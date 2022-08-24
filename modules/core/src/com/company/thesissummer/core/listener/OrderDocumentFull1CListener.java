/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.core.listener;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.inject.Inject;

import com.company.thesissummer.entity.OrderDocumentFull1C;
import org.springframework.stereotype.Component;

@Component(OrderDocumentFull1CListener.NAME)
public class OrderDocumentFull1CListener<T extends OrderDocumentFull1C> implements BeforeUpdateEntityListener<T>, BeforeInsertEntityListener<T> {
    public static final String NAME = "thesissummer_OrderDocumentFull1CListener";

    @Inject
    protected Persistence persistence;

    @Override
    public void onBeforeUpdate(T entity, EntityManager entityManager) {

        Set<String> fields = persistence.getTools().getDirtyFields(entity);

        if (fields.contains("number") || fields.contains("createTs")) {
            Messages messages = AppBeans.get(Messages.class);
            String number = StringUtils.trimToEmpty(entity.getValue("number"));
            Date createDate = entity.getValue("createTs");
            String createDateStr = createDate == null ? "" : new SimpleDateFormat("dd.MM.yyyy").format(createDate);
            String localizedName = messages.getMessage(entity.getClass(), entity.getClass().getSimpleName());

            StringBuilder description = new StringBuilder();
            description.append(localizedName);
            if (StringUtils.isNotBlank(number)) {
                description
                        .append(' ')
                        .append(messages.getMessage(entity.getClass(), "notification.number"))
                        .append(' ')
                        .append(number);
            }
            if (StringUtils.isNotBlank(createDateStr)) {
                description
                        .append(' ')
                        .append(messages.getMessage(entity.getClass(), "notification.from"))
                        .append(' ')
                        .append(createDateStr);
            }

            entity.setDescription(description.toString());
        }
    }

    @Override
    public void onBeforeInsert(T entity, EntityManager entityManager) {
        onBeforeUpdate(entity, entityManager);
    }
}
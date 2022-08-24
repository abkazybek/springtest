/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "THESISSUMMER_AAA")
@Entity(name = "thesissummer$Aaa")
@EnableRestore
@TrackEditScreenHistory
public class Aaa extends StandardEntity {

    private static final long serialVersionUID = 5235226466004690327L;
}
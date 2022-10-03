/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity.protocolkk;

import com.company.thesissummer.entity.ProtocolKK;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_CITY")
@Entity(name = "thesissummer$City")
@NamePattern("%s|name")
public class City extends StandardEntity {
    private static final long serialVersionUID = 5483507932842340244L;

    @Column(name = "CODE")
    protected String code;

    @Column(name = "NAME")
    protected String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "city")
    protected ProtocolKK protocolKK;

    public ProtocolKK getProtocolKK() {
        return protocolKK;
    }

    public void setProtocolKK(ProtocolKK protocolKK) {
        this.protocolKK = protocolKK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
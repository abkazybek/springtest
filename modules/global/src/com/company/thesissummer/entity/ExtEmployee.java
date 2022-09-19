/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.thesis.core.entity.Employee;

import javax.persistence.*;

@javax.persistence.DiscriminatorValue("X")
@Table(name = "DF_EMPLOYEE")
@Entity(name = "thesissummer$ExtEmployee")
@PrimaryKeyJoinColumn(name = "CORRESPONDENT_ID", referencedColumnName = "ID")
@Extends(Employee.class)
public class ExtEmployee extends Employee {
    private static final long serialVersionUID = -8686422661464186998L;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "poluchatel")
    protected SpravkaPoZadol spravkaPoZadol;

    public SpravkaPoZadol getSpravkaPoZadol() {
        return spravkaPoZadol;
    }

    public void setSpravkaPoZadol(SpravkaPoZadol spravkaPoZadol) {
        this.spravkaPoZadol = spravkaPoZadol;
    }
}
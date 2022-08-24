/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.thesis.core.entity.Department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@javax.persistence.DiscriminatorValue("A")
@Table(name = "DF_DEPARTMENT")
@Entity(name = "thesissummer$ExtDepartment")
@PrimaryKeyJoinColumn(name = "CORRESPONDENT_ID", referencedColumnName = "ID")
@Extends(Department.class)
public class ExtDepartment extends Department {
    private static final long serialVersionUID = -6810766108048525032L;

    @Column(name = "PODRAZDELENIE", length = 50)
    protected String podrazdelenie;

    @Column(name = "DESCRIBE_")
    protected String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPodrazdelenie() {
        return podrazdelenie;
    }

    public void setPodrazdelenie(String podrazdelenie) {
        this.podrazdelenie = podrazdelenie;
    }
}
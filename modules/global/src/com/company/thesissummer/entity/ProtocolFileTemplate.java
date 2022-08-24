/*
 * Copyright (c) 2022 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.thesissummer.entity;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "THESISSUMMER_PROTOCOL_FILE_TEMPLATE")
@Entity(name = "thesissummer$ProtocolFileTemplate")
public class ProtocolFileTemplate extends StandardEntity {
    private static final long serialVersionUID = 7922814213455432233L;

    @Column(name = "CODE")
    protected String code;

    @Column(name = "FILE_CONTENT")
    protected byte[] fileContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_DESCRIPTOR_ID")
    protected FileDescriptor fileDescriptor;

    public FileDescriptor getFileDescriptor() {
        return fileDescriptor;
    }

    public void setFileDescriptor(FileDescriptor fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
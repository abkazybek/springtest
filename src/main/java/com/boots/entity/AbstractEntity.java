package com.boots.entity;

import java.util.Date;

abstract class AbstractEntity {

    Long id;

    Date created_at;

    public AbstractEntity(Long id, Date created_at) {
        this.id = id;
        this.created_at = created_at;
    }

    public AbstractEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}

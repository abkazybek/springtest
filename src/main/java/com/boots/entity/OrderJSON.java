package com.boots.entity;

public class OrderJSON {
    private Long id;

    private String name;

    private String thing;

    private Integer amount;

    private String addres;

    private String telephone;

    public OrderJSON(Long id, String name, String thing, Integer amount, String addres, String telephone) {
        this.id = id;
        this.name = name;
        this.thing = thing;
        this.amount = amount;
        this.addres = addres;
        this.telephone = telephone;
    }

    public OrderJSON() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

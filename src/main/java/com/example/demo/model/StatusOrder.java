package com.example.demo.model;

public enum StatusOrder {
    CREATED("Złożone"),
    CONFIRM("Zatwierdzone"),
    CANCELED("Zakończone");

    String nazwa;
    StatusOrder(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
}

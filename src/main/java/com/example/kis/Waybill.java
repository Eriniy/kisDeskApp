package com.example.kis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Waybill {
    public static ObservableList<Waybill> waybillList=
            FXCollections.observableArrayList();

    public static Waybill waybillSelected = new Waybill();

    private int id;
    private int number;
    private int type;
    private int clientId;
    private String date;
    private String clientName;

    public Waybill(int id, int number, int type, int clientId, String date, String clientName) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.clientId = clientId;
        this.date = date;
        this.clientName = clientName;
    }

    public Waybill(){}

    public Waybill(int id, int number, int type, int clientId, String date) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.clientId = clientId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

package com.example.kis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Config {
    public static ObservableList<Config> configList=
            FXCollections.observableArrayList();

    public static Config configSelected = new Config();

    private int id;
    private int productId;
    private int waybillId;
    private int amount;
    private String productName;

    public Config(int id, int productId, int waybillId, int amount, String productName) {
        this.id = id;
        this.productId = productId;
        this.waybillId = waybillId;
        this.amount = amount;
        this.productName = productName;
    }

    public Config(){}

    public Config(int id, int productId, int waybillId, int amount) {
        this.id = id;
        this.productId = productId;
        this.waybillId = waybillId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(int waybillId) {
        this.waybillId = waybillId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

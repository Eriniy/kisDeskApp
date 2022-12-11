package com.example.kis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;

public class Content {

    public static ObservableList<Content> contentList=
            FXCollections.observableArrayList();

    public static Content contentSelected = new Content();
    private int id;
    private String name;
    private String units;
    private float price;
    private int balance;
    private String actualDate;


    public Content(int id, String name, String units, float price,
                   int balance, String actualDate ){
        this.id = id;
        this.name = name;
        this.units = units;
        this.price = price;
        this.balance = balance;
        this.actualDate = actualDate;

    }

    public Content(){

    }

//    public static void clearContentList(){
//        Content.contentList = null;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }
}

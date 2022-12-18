package com.example.kis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Organization {

    public static ObservableList<Organization> organizationList=
            FXCollections.observableArrayList();

    public static Organization organizationSelected = new Organization();
    private int id;
    private String name;
    private String jobTitle;
    private String address;
    private String inn;
    private String tel;

    public Organization(int id, String name, String jobTitle, String address,
                        String inn, String tel) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.address = address;
        this.inn = inn;
        this.tel = tel;
    }

    public Organization(){

    }

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

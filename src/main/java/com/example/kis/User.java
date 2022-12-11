package com.example.kis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static ArrayList<User> usersList = new ArrayList();

    public static ObservableList<User> allUsersList=
            FXCollections.observableArrayList();

    public static User userSelected = new User();
    private int id;
    private String name;
    private String login;
    private String password;
    private int law;

    public User(int id, String name, String login, String password, int law) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.law = law;
    }

    public User() {

    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLaw() {
        return law;
    }

    public void setLaw(int law) {
        this.law = law;
    }
}

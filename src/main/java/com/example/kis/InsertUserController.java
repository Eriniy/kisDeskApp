package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.SplittableRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.kis.ControllerFunc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InsertUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_insertDB;

    @FXML
    private Button btn_returnBack;

    @FXML
    private ComboBox<String> combox_laws;

    @FXML
    private TextField login_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField password_field;

    @FXML
    void select(ActionEvent event) {

    }

    @FXML
    void initialize() {

        ObservableList<String> lawsList =
                FXCollections.observableArrayList("admin", "employee",
                        "control", "deliveryman");
        combox_laws.setItems(lawsList);


        btn_returnBack.setOnAction(event -> {
            btn_returnBack.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("updateUser" +
                        ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        btn_insertDB.setOnAction(event -> {
            if (isNotNull()){
                if (isValidPassword(password_field.getText())){
                    insertUser();
                    clearFieldAll();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Пароль не соответствует " +
                            "требованиям!");
                    alert.showAndWait();

                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните обязательные поля!");
                alert.showAndWait();
            }
        });

        btn_clear.setOnAction(event -> {
            clearFieldAll();
        });

    }

    public void insertUser() {
        ConnectionDataBase connDB = new ConnectionDataBase();
        User user = new User();

        user.setName(name_field.getText());
        user.setLogin(login_field.getText());
        user.setPassword(password_field.getText());
        if (combox_laws.getSelectionModel().getSelectedItem() == "admin"){
            user.setLaw(1);
        }
        else if(combox_laws.getSelectionModel().getSelectedItem() ==
                "employee"){
            user.setLaw(2);
        }
        else if(combox_laws.getSelectionModel().getSelectedItem() ==
                "control"){
            user.setLaw(4);
        }
        else {
            user.setLaw(5);
        }

        connDB.insertUser(user);
        connDB.getUserList();


    }

    public void clearFieldAll() {
        name_field.clear();
        login_field.clear();
        password_field.clear();
        combox_laws.valueProperty().set(null);
    }

    public static boolean isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])(?=.*[ a-z])(?=" +
                ".*[!@#$%^])(?=\\S+$).{8,30}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public boolean isNotNull(){
        return !name_field.getText().equals("")
                && !login_field.getText().equals("")
                && !password_field.getText().equals("")
                && !combox_laws.getSelectionModel().isEmpty();
    }

}

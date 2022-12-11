package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InsertContentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField balance_field;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_insertDB;

    @FXML
    private Button btn_returnBack;

    @FXML
    private DatePicker date_panel;

    @FXML
    private TextField name_field;

    @FXML
    private TextField price_field;

    @FXML
    private ComboBox<String> combox_units;

    @FXML
    void select(ActionEvent event) {

    }

    @FXML
    void initialize() {

        ObservableList<String> unitsList =
                FXCollections.observableArrayList("кг", "л", "шт", "ед");
        combox_units.setItems(unitsList);


        btn_returnBack.setOnAction(event -> {
            btn_returnBack.getScene().getWindow().hide();

            Parent root = null;
            try {
                if (User.usersList.get(0).getLaw() == 1){
                    root = FXMLLoader.load(getClass().getResource("adminStart" +
                            ".fxml"));
                }
                else {
                    root = FXMLLoader.load(getClass().getResource("start.fxml"));
                }
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
                if (isValidNumber(price_field.getText()) && isValidNumber(balance_field.getText())){
                    insertContent();
                    clearFieldAll();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("В полях price и amount должны быть " +
                            "числа");
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

    public void insertContent() {
        ConnectionDataBase connDB = new ConnectionDataBase();
        Content content = new Content();

        content.setName(name_field.getText());
        content.setUnits(combox_units.getSelectionModel().getSelectedItem());
        content.setPrice(Float.parseFloat(price_field.getText()));
        content.setBalance(Integer.parseInt(balance_field.getText()));
        content.setActualDate(date_panel.getValue().toString());

        connDB.insertProduct(content);
        connDB.getContent();


    }

    public void clearFieldAll() {
        name_field.clear();
        combox_units.valueProperty().set(null);
        price_field.clear();
        balance_field.clear();
        date_panel.getEditor().clear();
    }

    public boolean isValidNumber(String str){
        return ControllerFunc.checkFieldForNumber(str);
    }

    public boolean isNotNull(){
        return (!name_field.getText().equals("")
                && !price_field.getText().equals("")
                && !combox_units.getSelectionModel().isEmpty()
                && !balance_field.getText().equals("")
                && date_panel.getValue() != null);
    }

}

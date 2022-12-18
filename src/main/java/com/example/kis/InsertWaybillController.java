package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InsertWaybillController {

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
    private TextField clientId_field;

    @FXML
    private ComboBox<Integer> combox_type;

    @FXML
    private DatePicker date_panel;

    @FXML
    private TextField number_field;

    @FXML
    void initialize() {
        ObservableList<Integer> typeList =
                FXCollections.observableArrayList(1,2,3);
        combox_type.setItems(typeList);

        btn_returnBack.setOnAction(event -> {
            btn_returnBack.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(
                        "viewWaybillScene" +
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
                if (isValidNumber(clientId_field.getText()) && isValidNumber(number_field.getText())){
                    insertContent();
                    clearFieldAll();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("В полях number и client_id должны " +
                            "быть" +
                            " " +
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
        Waybill waybill = new Waybill();

        waybill.setNumber(Integer.parseInt(number_field.getText()));
        waybill.setType(combox_type.getSelectionModel().getSelectedItem());
        waybill.setDate(date_panel.getValue().toString());
        waybill.setClientId(Integer.parseInt(clientId_field.getText()));

        connDB.insertWaybill(waybill);
        connDB.getWaybill();
    }

    public void clearFieldAll() {
        number_field.clear();
        combox_type.valueProperty().set(null);
        date_panel.getEditor().clear();
        clientId_field.clear();
    }

    public boolean isValidNumber(String str){
        return ControllerFunc.checkFieldForNumber(str);
    }

    public boolean isNotNull(){
        return (!number_field.getText().equals("")
                && !combox_type.getSelectionModel().isEmpty()
                && date_panel.getValue() != null
                && !clientId_field.getText().equals(""));
    }

}

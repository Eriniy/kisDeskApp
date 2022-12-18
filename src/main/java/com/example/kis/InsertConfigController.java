package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertConfigController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amount_field;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_insertDB;

    @FXML
    private Button btn_returnBack;

    @FXML
    private TextField productId_field;

    @FXML
    private TextField waybillId_field;

    @FXML
    void initialize() {

        btn_returnBack.setOnAction(event -> {
            btn_returnBack.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(
                        "viewConfig" +
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
                if (isValidNumber(productId_field.getText()) && isValidNumber(waybillId_field.getText()) && isValidNumber(amount_field.getText())){
                    insertContent();
                    clearFieldAll();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("В полях produc_id, waybill_id, " +
                            "amount должны быть числа");
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
        Config config = new Config();

        config.setProductId(Integer.parseInt(productId_field.getText()));
        config.setWaybillId(Integer.parseInt(waybillId_field.getText()));
        config.setAmount(Integer.parseInt(amount_field.getText()));

        connDB.insertConfig(config);
        connDB.getConfig();
    }

    public void clearFieldAll() {
        productId_field.clear();
        waybillId_field.clear();
        amount_field.clear();
    }

    public boolean isValidNumber(String str){
        return ControllerFunc.checkFieldForNumber(str);
    }

    public boolean isNotNull(){
        return (!productId_field.getText().equals("")
                && !waybillId_field.getText().equals("")
                && !amount_field.getText().equals(""));
    }

}

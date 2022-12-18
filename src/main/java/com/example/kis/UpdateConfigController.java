package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateConfigController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amount_field;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_updateDB;

    @FXML
    private Text idContent_label;

    @FXML
    private TextField productId_field;

    @FXML
    private TextField waybillId_field;

    @FXML
    void initialize() {

        idContent_label.setText(String.valueOf(Waybill.waybillSelected.getId()));
        productId_field.setText(String.valueOf(Config.configSelected.getProductId()));
        waybillId_field.setText(String.valueOf(Config.configSelected.getWaybillId()));
        amount_field.setText(String.valueOf(Config.configSelected.getAmount()));

        btn_updateDB.setOnAction(event ->{
            if (isNotNull()){
                if (isValidNumber(productId_field.getText()) && isValidNumber(waybillId_field.getText()) && isValidNumber(amount_field.getText())){
                    update();

                    btn_updateDB.getScene().getWindow().hide();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource(
                                "viewConfigScene" +
                                        ".fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("В полях price и amount должны быть " +
                            "числа");
                    alert.showAndWait();
                }

            }
            else{
                // предупреждение
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните обязательные поля!");
                alert.showAndWait();
            }

        });

        btn_returnBack.setOnAction(event ->{
            btn_returnBack.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(
                        "viewConfigScene" +
                                ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });

        btn_clear.setOnAction(event -> {
            clearFieldAll();
        });
    }

    public void update() {
        ConnectionDataBase connDB = new ConnectionDataBase();
        Config config = Config.configSelected;
        Config.configSelected = new Config();

        config.setProductId(Integer.parseInt(productId_field.getText()));
        config.setWaybillId(Integer.parseInt(waybillId_field.getText()));
        config.setAmount(Integer.parseInt(amount_field.getText()));

        connDB.updateConfig(config);
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

package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateContentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField balance_field;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_updateDB;

    @FXML
    private ComboBox<String> combox_units;

    @FXML
    private DatePicker date_panel;

    @FXML
    private Text idContent_label;

    @FXML
    private TextField name_field;

    @FXML
    private TextField price_field;

    @FXML
    void select(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<String> unitsList =
                FXCollections.observableArrayList("кг", "л", "шт", "ед");
        combox_units.setItems(unitsList);

        idContent_label.setText(String.valueOf(Content.contentSelected.getId()));
        name_field.setText(Content.contentSelected.getName());
        combox_units.setValue(Content.contentSelected.getUnits());
        price_field.setText(String.valueOf(Content.contentSelected.getPrice()));
        balance_field.setText(String.valueOf(Content.contentSelected.getBalance()));
        date_panel.setValue(LocalDate.parse(Content.contentSelected.getActualDate()));

        btn_returnBack.setOnAction(event ->{
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

        btn_clear.setOnAction(event ->{
            clearFieldAll();

        });

        btn_updateDB.setOnAction(event ->{
            if (isNotNull()){
                if (isValidNumber(price_field.getText()) && isValidNumber(balance_field.getText())){
                    update();

                    btn_updateDB.getScene().getWindow().hide();
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
    }

    public void clearFieldAll() {
        name_field.clear();
        combox_units.valueProperty().set(null);
        price_field.clear();
        balance_field.clear();
        date_panel.getEditor().clear();
    }

    public void update(){
        ConnectionDataBase connDB = new ConnectionDataBase();
        Content content = Content.contentSelected;
        Content.contentSelected = new Content();

        content.setName(name_field.getText());
        content.setUnits(combox_units.getSelectionModel().getSelectedItem());
        content.setPrice(Float.parseFloat(price_field.getText()));
        content.setBalance(Integer.parseInt(balance_field.getText()));
        content.setActualDate(date_panel.getValue().toString());

        connDB.updateProduct(content);
        connDB.getContent();
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

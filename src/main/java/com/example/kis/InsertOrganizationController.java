package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertOrganizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address_field;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_insertDB;

    @FXML
    private Button btn_returnBack;

    @FXML
    private TextField inn_field;

    @FXML
    private TextField jobTitle_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField tel_field;

    @FXML
    void initialize() {

        btn_returnBack.setOnAction(event -> {
            btn_returnBack.getScene().getWindow().hide();

            Parent root = null;
            try {
                if (User.usersList.get(0).getLaw() == 1){
                    root = FXMLLoader.load(getClass().getResource("viewOrganizationScene" +
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

        btn_clear.setOnAction(event -> {
            clearFieldAll();
        });

        btn_insertDB.setOnAction(event -> {
            if (isNotNull()){
                insertContent();
                clearFieldAll();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните обязательные поля!");
                alert.showAndWait();
            }
        });


    }

    public void insertContent() {
        ConnectionDataBase connDB = new ConnectionDataBase();
        Organization organization = new Organization();

        organization.setName(name_field.getText());
        organization.setJobTitle(jobTitle_field.getText());
        organization.setAddress(address_field.getText());
        organization.setInn(inn_field.getText());
        organization.setTel(tel_field.getText());

        connDB.insertOrganization(organization);
        connDB.getOrganisation();
    }

    public void clearFieldAll() {
        name_field.clear();
        jobTitle_field.clear();
        address_field.clear();
        inn_field.clear();
        tel_field.clear();
    }

    public boolean isNotNull(){
        return (!name_field.getText().equals("")
                && !jobTitle_field.getText().equals("")
                && !address_field.getText().equals("")
                && !inn_field.getText().equals("")
                && !tel_field.getText().equals("")
                );
    }

}

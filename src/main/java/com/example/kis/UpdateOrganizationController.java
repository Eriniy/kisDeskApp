package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateOrganizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address_field;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_updateDB;

    @FXML
    private Text idContent_label;

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
        idContent_label.setText(String.valueOf(Organization.organizationSelected.getId()));
        name_field.setText(Organization.organizationSelected.getName());
        jobTitle_field.setText(Organization.organizationSelected.getJobTitle());
        address_field.setText(Organization.organizationSelected.getAddress());
        inn_field.setText(Organization.organizationSelected.getInn());
        tel_field.setText(Organization.organizationSelected.getTel());

        btn_returnBack.setOnAction(event ->{
            btn_returnBack.getScene().getWindow().hide();
            Parent root = null;
            try {
                if (User.usersList.get(0).getLaw() == 1){
                    root = FXMLLoader.load(getClass().getResource(
                            "viewOrganizationScene" +
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
                update();

                btn_updateDB.getScene().getWindow().hide();
                Parent root = null;
                try {
                    if (User.usersList.get(0).getLaw() == 1){
                        root = FXMLLoader.load(getClass().getResource(
                                "viewOrganizationScene" +
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

    public void update(){
        ConnectionDataBase connDB = new ConnectionDataBase();
        Organization organization = Organization.organizationSelected;
        Organization.organizationSelected = new Organization();

        organization.setName(name_field.getText());
        organization.setJobTitle(jobTitle_field.getText());
        organization.setAddress(address_field.getText());
        organization.setInn(inn_field.getText());
        organization.setTel(tel_field.getText());

        connDB.updateOrganisation(organization);
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

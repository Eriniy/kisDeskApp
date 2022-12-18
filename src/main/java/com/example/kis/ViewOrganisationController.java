package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewOrganisationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_insert;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Organization, String> col_INN;

    @FXML
    private TableColumn<Organization, String> col_address;

    @FXML
    private TableColumn<Organization, Integer> col_id;

    @FXML
    private TableColumn<Organization, String> col_jobTitle;

    @FXML
    private TableColumn<Organization, String> col_name;

    @FXML
    private TableColumn<Organization, String> col_tel;

    @FXML
    private TableView<Organization> tableResources;

    @FXML
    private Label tablename_label;

    @FXML
    private Label username_label;

    @FXML
    void initialize() {
        username_label.setText(User.usersList.get(0).getName());

        ConnectionDataBase connDB = new ConnectionDataBase();
        Organization.organizationList = connDB.getOrganisation();

        showOrganisation();

        btn_insert.setOnAction(event ->{
            btn_insert.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(
                        "insertOrganization" +
                        ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });

        btn_update.setOnAction(event ->{
            if (nullSelectedContent()){
                btn_update.getScene().getWindow().hide();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource(
                            "updateOrganization" +
                                    ".fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите строку для редактирования");
                alert.showAndWait();
            }

        });

        btn_delete.setOnAction(event ->{
            if (getSelectedContent()!=null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Предупреждение");
                alert.setHeaderText("Вы точно хотите удалить эту строчку?");
                alert.setContentText("Выберите дальнейшее действие");
                ButtonType buttonTypeOne = new ButtonType("Yes");
                ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                System.out.println(result.get());
                if(result.get() == buttonTypeCancel) event.consume();
                else deleteSelectedContent(getSelectedContent());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите строку для удаления");
                alert.showAndWait();
            }

        });

        btn_exit.setOnAction(event ->{
            User.usersList.remove(0);
            btn_exit.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });

        btn_returnBack.setOnAction(event ->{
            btn_returnBack.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("adminStart" +
                        ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });


    }

    public void showOrganisation(){
        col_id.setCellValueFactory(new PropertyValueFactory<Organization,
                Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Organization,
                String>("name"));
        col_jobTitle.setCellValueFactory(new PropertyValueFactory<Organization,
                String>("jobTitle"));
        col_address.setCellValueFactory(new PropertyValueFactory<Organization,
                String>("name"));
        col_INN.setCellValueFactory(new PropertyValueFactory<Organization,
                String>("inn"));
        col_tel.setCellValueFactory(new PropertyValueFactory<Organization,
                String>("tel"));

        tableResources.setItems(Organization.organizationList);
    }

    public Organization getSelectedContent(){
        Organization contentSelected =
                tableResources.getSelectionModel().getSelectedItem();
        return contentSelected;
    }

    public boolean nullSelectedContent(){
        Organization contentSelected =
                tableResources.getSelectionModel().getSelectedItem();

        if (contentSelected!=null){
            Organization.organizationSelected = contentSelected;
            return true;
        }
        else {
            return false;
        }
    }

    public void deleteSelectedContent(Organization contentSelected){
        ConnectionDataBase connDB = new ConnectionDataBase();

        connDB.deleteOrganisation(contentSelected);

        connDB.getOrganisation();
    }

}

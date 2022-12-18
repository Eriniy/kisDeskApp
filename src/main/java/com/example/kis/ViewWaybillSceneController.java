package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewWaybillSceneController {

    @FXML
    private Label adminViewText;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_insert;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_viewConfig;

    @FXML
    private TableColumn<Waybill, Integer> col_clientId;

    @FXML
    private TableColumn<Waybill, String> col_clientName;

    @FXML
    private TableColumn<Waybill, Date> col_date;

    @FXML
    private TableColumn<Waybill, Integer> col_id;

    @FXML
    private TableColumn<Waybill, Integer> col_number;

    @FXML
    private TableColumn<Waybill, Integer> col_type;

    @FXML
    private TableView<Waybill> tableResources;

    @FXML
    private Label tablename_label;

    @FXML
    private Label username_label;

    @FXML
    void initialize() {

        if (User.usersList.get(0).getLaw() != 1){
            adminViewText.setVisible(false);
        }

        username_label.setText(User.usersList.get(0).getName());

        ConnectionDataBase connDB = new ConnectionDataBase();
        Waybill.waybillList = connDB.getWaybill();

        showContent();


        btn_insert.setOnAction(event ->{
            btn_insert.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("insertWaybill" +
                        ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

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

        btn_update.setOnAction(event ->{

            if (nullSelectedContent()){
                btn_update.getScene().getWindow().hide();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource(
                            "updateWaybill" +
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

        btn_viewConfig.setOnAction(event ->{
            btn_viewConfig.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("viewConfig" +
                        ".fxml"));
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
    }

    public void showContent(){
        col_id.setCellValueFactory(new PropertyValueFactory<Waybill, Integer>("id"));
        col_number.setCellValueFactory(new PropertyValueFactory<Waybill,
                Integer>("number"));
        col_type.setCellValueFactory(new PropertyValueFactory<Waybill, Integer>(
                "type"));
        col_date.setCellValueFactory(new PropertyValueFactory<Waybill,
                Date>("date"));
        col_clientId.setCellValueFactory(new PropertyValueFactory<Waybill,
                Integer>("clientId"));
        col_clientName.setCellValueFactory(new PropertyValueFactory<Waybill,
                String>("clientName"));


        tableResources.setItems(Waybill.waybillList);
    }

    public boolean nullSelectedContent(){
        Waybill contentSelected =
                tableResources.getSelectionModel().getSelectedItem();

        if (contentSelected!=null){
            Waybill.waybillSelected = contentSelected;
            return true;
        }
        else {
            return false;
        }
    }

    public Waybill getSelectedContent(){
        Waybill contentSelected =
                tableResources.getSelectionModel().getSelectedItem();
        return contentSelected;
    }

    public void deleteSelectedContent(Waybill contentSelected){
        ConnectionDataBase connDB = new ConnectionDataBase();

        connDB.deleteWaybill(contentSelected);

        connDB.getWaybill();
    }

}

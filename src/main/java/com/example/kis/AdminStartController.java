package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.kis.ConnectionDataBase;
import com.example.kis.Content;
import com.example.kis.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminStartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_updateEmployee;

    @FXML
    private Button btn_organization;

    @FXML
    private Button btn_waybill;

    @FXML
    private Button btn_insert;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_exit;

    @FXML
    private TableColumn<Content, Date> col_actualDate;

    @FXML
    private TableColumn<Content, Integer> col_balance;

    @FXML
    private TableColumn<Content, Integer> col_id;

    @FXML
    private TableColumn<Content, String> col_name;

    @FXML
    private TableColumn<Content, String> col_units;

    @FXML
    private TableColumn<Content, Float> col_price;

    @FXML
    private TableView<Content> tableResources;

    @FXML
    private Label username_label;

    @FXML
    void initialize() {
        username_label.setText(User.usersList.get(0).getName());


        ConnectionDataBase connDB = new ConnectionDataBase();
        Content.contentList = connDB.getContent();

        showContent();

        btn_updateEmployee.setOnAction(event ->{
            btn_insert.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("viewUserScene" +
                        ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        btn_organization.setOnAction(event ->{
            btn_organization.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(
                        "viewOrganizationScene" +
                        ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        btn_waybill.setOnAction(event ->{
            btn_waybill.getScene().getWindow().hide();

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

        btn_insert.setOnAction(event ->{
            btn_insert.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("insertContent" +
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
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Не " +
//                        "выбрана строка для удаления", ButtonType.CANCEL);
//                alert.showAndWait();
            }


        });

        btn_update.setOnAction(event ->{

            if (nullSelectedContent()){
                btn_update.getScene().getWindow().hide();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("updateContent" +
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

//        btn_item1.setOnAction(event ->{
//            btn_selectType.getScene().getWindow().hide();
//
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("select.fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        });
//
//        btn_item2.setOnAction(event ->{
//            btn_selectType.getScene().getWindow().hide();
//
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("selectUnits" +
//                        ".fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        });
//
//        btn_item3.setOnAction(event ->{
//            btn_selectType.getScene().getWindow().hide();
//
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("selectAmount" +
//                        ".fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        });
//
//        btn_item4.setOnAction(event ->{
//            btn_selectType.getScene().getWindow().hide();
//
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("selectDate" +
//                        ".fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        });
//
//        btn_item5.setOnAction(event ->{
//            btn_selectType.getScene().getWindow().hide();
//
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getResource("selectPrice" +
//                        ".fxml"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        });

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


    }

    public void showContent(){
        col_id.setCellValueFactory(new PropertyValueFactory<Content,
                Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Content,
                String>("name"));
        col_units.setCellValueFactory(new PropertyValueFactory<Content,
                String>("units"));
        col_price.setCellValueFactory(new PropertyValueFactory<Content,
                Float>("price"));
        col_balance.setCellValueFactory(new PropertyValueFactory<Content,
                Integer>("balance"));
        col_actualDate.setCellValueFactory(new PropertyValueFactory<Content,
                Date>("actualDate"));

        tableResources.setItems(Content.contentList);
    }

    public boolean nullSelectedContent(){
        Content contentSelected =
                tableResources.getSelectionModel().getSelectedItem();

        if (contentSelected!=null){
            Content.contentSelected = contentSelected;
            return true;
        }
        else {
            return false;
        }


    }

    public Content getSelectedContent(){
        Content contentSelected =
                tableResources.getSelectionModel().getSelectedItem();
        return contentSelected;
    }

    public void deleteSelectedContent(Content contentSelected){
        ConnectionDataBase connDB = new ConnectionDataBase();

        connDB.deleteProduct(contentSelected);

        connDB.getContent();
    }



}

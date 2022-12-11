package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VisitGuestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Label tablename_label;

    @FXML
    private Button btn_goToAuth;

    @FXML
    void initialize() {
        ConnectionDataBase connDB = new ConnectionDataBase();
        Content.contentList = connDB.getContent();

        showContent();

        btn_goToAuth.setOnAction(event ->{
            btn_goToAuth.getScene().getWindow().hide();

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
}

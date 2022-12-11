package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SelectForUnitsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_showSelect;

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
    private TextField selectPar_field;

    @FXML
    void initialize() {
        btn_showSelect.setOnAction(event ->{
            if (selectPar_field.getText()!=null){
                createSelectContent();
            }

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

    private void createSelectContent() {
        ConnectionDataBase connDB = new ConnectionDataBase();

        ObservableList<Content> selectList =
                connDB.getSelectForUnits(selectPar_field.getText());

        showContent(selectList);
    }

    private void showContent(ObservableList<Content> contentList) {
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

        tableResources.setItems(contentList);
    }

}

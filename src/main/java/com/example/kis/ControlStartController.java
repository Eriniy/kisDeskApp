package com.example.kis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControlStartController {

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
    private Button btn_update;

    @FXML
    private Button btn_wayproduct;

    @FXML
    private TableColumn<?, ?> col_actualDate;

    @FXML
    private TableColumn<?, ?> col_balance;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_price;

    @FXML
    private TableColumn<?, ?> col_units;

    @FXML
    private TableView<?> tableResources;

    @FXML
    private Label username_label;

    @FXML
    void initialize() {
    }

}

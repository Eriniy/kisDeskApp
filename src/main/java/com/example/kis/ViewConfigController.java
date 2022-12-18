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
import org.w3c.dom.Text;

public class ViewConfigController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminViewText;

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
    private TableColumn<Config, Integer> col_amount;

    @FXML
    private TableColumn<Config, Integer> col_id;

    @FXML
    private TableColumn<Config, Integer> col_productId;

    @FXML
    private TableColumn<Config, String> col_productName;

    @FXML
    private TableColumn<Config, Integer> col_waybillId;

    @FXML
    private TableView<Config> tableResources;

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
        Config.configList = connDB.getConfig();

        showContent();

        btn_insert.setOnAction(event ->{
            btn_insert.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("insertConfig" +
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
                            "updateConfig" +
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

        btn_returnBack.setOnAction(event ->{
            btn_returnBack.getScene().getWindow().hide();

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
    }

    public void showContent(){
        col_id.setCellValueFactory(new PropertyValueFactory<Config, Integer>("id"));
        col_productId.setCellValueFactory(new PropertyValueFactory<Config,
                Integer>("productId"));
        col_waybillId.setCellValueFactory(new PropertyValueFactory<Config,
                Integer>(
                "waybillId"));
        col_amount.setCellValueFactory(new PropertyValueFactory<Config,
                Integer>("amount"));
        col_productName.setCellValueFactory(new PropertyValueFactory<Config,
                String>("productName"));


        tableResources.setItems(Config.configList);
    }

    public boolean nullSelectedContent(){
        Config contentSelected =
                tableResources.getSelectionModel().getSelectedItem();

        if (contentSelected!=null){
            Config.configSelected = contentSelected;
            return true;
        }
        else {
            return false;
        }
    }

    public Config getSelectedContent(){
        Config contentSelected =
                tableResources.getSelectionModel().getSelectedItem();
        return contentSelected;
    }

    public void deleteSelectedContent(Config contentSelected){
        ConnectionDataBase connDB = new ConnectionDataBase();

        connDB.deleteConfig(contentSelected);

        connDB.getConfig();
    }

}

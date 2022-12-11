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

public class UpdateUserController {

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
    private Button btn_returnBack;

    @FXML
    private TableColumn<User, Integer> col_id;

    @FXML
    private TableColumn<User, Integer> col_law;

    @FXML
    private TableColumn<User, String> col_login;

    @FXML
    private TableColumn<User, String> col_name;

    @FXML
    private TableColumn<User, String> col_password;

    @FXML
    private TableView<User> tableResources;

    @FXML
    private Label username_label;

    @FXML
    void initialize() {
        username_label.setText(User.usersList.get(0).getName());

        ConnectionDataBase connDB = new ConnectionDataBase();
        User.allUsersList = connDB.getUserList();

        showUsers();

        btn_insert.setOnAction(event ->{
            btn_insert.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("insertUser" +
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
                            "updateUserSelect" +
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

    public void showUsers(){
        col_id.setCellValueFactory(new PropertyValueFactory<User,
                Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<User,
                String>("name"));
        col_login.setCellValueFactory(new PropertyValueFactory<User,
                String>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<User,
                String>("password"));
        col_law.setCellValueFactory(new PropertyValueFactory<User,
                Integer>("law"));

        tableResources.setItems(User.allUsersList);
    }

    public User getSelectedContent(){
        User contentSelected =
                tableResources.getSelectionModel().getSelectedItem();
        return contentSelected;
    }

    public boolean nullSelectedContent(){
        User contentSelected =
                tableResources.getSelectionModel().getSelectedItem();

        if (contentSelected!=null){
            User.userSelected = contentSelected;
            return true;
        }
        else {
            return false;
        }
    }

    public void deleteSelectedContent(User contentSelected){
        ConnectionDataBase connDB = new ConnectionDataBase();

        connDB.deleteUser(contentSelected);

        connDB.getUserList();
    }

}

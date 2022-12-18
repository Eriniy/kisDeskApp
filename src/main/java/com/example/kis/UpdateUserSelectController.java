package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateUserSelectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_returnBack;

    @FXML
    private Button btn_updateDB;

    @FXML
    private ComboBox<String> combox_laws;

    @FXML
    private Text idContent_label;

    @FXML
    private TextField login_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField password_field;

    @FXML
    void select(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<String> lawsList =
                FXCollections.observableArrayList("admin", "employee",
                        "control", "deliveryman");
        combox_laws.setItems(lawsList);

        idContent_label.setText(String.valueOf(User.userSelected.getId()));
        name_field.setText(User.userSelected.getName());
        login_field.setText(User.userSelected.getLogin());
        password_field.setText(User.userSelected.getPassword());
        if (User.userSelected.getLaw() == 1){
            combox_laws.setValue("admin");
        }
        else if (User.userSelected.getLaw() == 2){
            combox_laws.setValue("employee");
        }
        else if (User.userSelected.getLaw() == 4){
            combox_laws.setValue("control");
        }
        else {
            combox_laws.setValue("deliveryman");
        }

        btn_returnBack.setOnAction(event ->{
            btn_returnBack.getScene().getWindow().hide();
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

        btn_clear.setOnAction(event ->{
            clearFieldAll();
        });

        btn_updateDB.setOnAction(event ->{
            if (isNotNull()){
                if (isValidPassword(password_field.getText())){
                    update();

                    btn_updateDB.getScene().getWindow().hide();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource(
                                "updateUser" +
                                ".fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Пароль не удовлетворяет " +
                            "требованиям");
                    alert.showAndWait();
                }

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

    public void clearFieldAll() {
        name_field.clear();
        login_field.clear();
        password_field.clear();
        combox_laws.valueProperty().set(null);
    }

    public void update(){
        ConnectionDataBase connDB = new ConnectionDataBase();
        User user = User.userSelected;
        User.userSelected = new User();

        user.setName(name_field.getText());
        user.setLogin(login_field.getText());
        user.setPassword(password_field.getText());
        if (combox_laws.getSelectionModel().getSelectedItem() == "admin"){
            user.setLaw(1);
        }
        else if(combox_laws.getSelectionModel().getSelectedItem() ==
                "employee"){
            user.setLaw(2);
        }
        else if(combox_laws.getSelectionModel().getSelectedItem() == "control"){
            user.setLaw(4);
        }
        else {
            user.setLaw(5);
        }

        connDB.updateUser(user);
        connDB.getUserList();
    }

    public boolean isNotNull(){
        return !name_field.getText().equals("")
                && !login_field.getText().equals("")
                && !password_field.getText().equals("")
                && !combox_laws.getSelectionModel().isEmpty();
    }

    public static boolean isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])(?=.*[ a-z])(?=" +
                ".*[!@#$%^])(?=\\S+$).{8,30}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

}

package com.example.kis;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_auth;

    @FXML
    private Button btn_visitGuest;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        setTooltip();

        btn_auth.setOnAction(event ->{
            String login = login_field.getText().trim();
            String password = password_field.getText().trim();

//            ConnectionDataBase connDB = new ConnectionDataBase();

            if (!login.equals("") && !password.equals("")) {
                loginUser();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните обязательные поля!");
                alert.showAndWait();
            }
        });

        btn_visitGuest.setOnAction(event ->{
            btn_visitGuest.getScene().getWindow().hide(); // убирает прошлое окно

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("visitGuest" +
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

    private void loginUser() {
        ConnectionDataBase connDB = new ConnectionDataBase();
        User user = new User();
        user.setLogin(login_field.getText());
        user.setPassword(password_field.getText());

        ResultSet resultSet =  connDB.getUser(user);


        int count = 0;
        try{
            while (resultSet.next()){
                count++;
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLaw(resultSet.getInt(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (count >= 1){
            System.out.println("Готовчик!");
            User.usersList.add(user);

            btn_auth.getScene().getWindow().hide();

            if (User.usersList.get(0).getLaw() == 1){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("adminStart.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            }
            else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("start.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }



        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Неправильный логин или пароль");
            alert.showAndWait();
        }
    }

    private void setTooltip(){
        password_field.setTooltip(new Tooltip("Введите логин"));
        login_field.setTooltip(new Tooltip("Введите пароль"));
    }



}

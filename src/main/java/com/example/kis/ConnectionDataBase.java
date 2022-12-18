package com.example.kis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.AbstractQueue;
import java.util.SplittableRandom;

public class ConnectionDataBase {

    public Connection dbLink;
    public Driver driver;

        public Connection getConnection() throws ClassNotFoundException {
        String db_name = "kis_db";
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/" + db_name;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            dbLink = DriverManager.getConnection(url, username, password);

        } catch (Exception e){
            e.printStackTrace();
        }

        return dbLink;
    }

    public void signUpUser(String name, String login, String password){
        String insert = "INSERT INTO `Users` (name, login, password) VALUES " +
                "(?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1,name);
            prSt.setString(2,login);
            prSt.setString(3,password);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertProduct(Content content){
        String insert = "INSERT INTO Product (name, units, price, " +
                    "balance, actualDate) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1, content.getName());
            prSt.setString(2, content.getUnits());
            prSt.setFloat(3, content.getPrice());
            prSt.setInt(4, content.getBalance());
            prSt.setDate(5, Date.valueOf(content.getActualDate()));

            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertWaybill(Waybill waybill){
        String insert = "INSERT INTO Waybill (number, type, date, " +
                "client_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setInt(1, waybill.getNumber());
            prSt.setInt(2, waybill.getType());
            prSt.setDate(3, Date.valueOf(waybill.getDate()));
            prSt.setInt(4, waybill.getClientId());


            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertConfig(Config config){
        String insert = "INSERT INTO ProductWay (product_id, waybill_id, " +
                "amount) VALUES (?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setInt(1, config.getProductId());
            prSt.setInt(2, config.getWaybillId());
            prSt.setInt(3, config.getAmount());


            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertOrganization(Organization organization){
        String insert = "INSERT INTO Organization (name, jobTitle, address, " +
                "INN, tel) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1, organization.getName());
            prSt.setString(2, organization.getJobTitle());
            prSt.setString(3, organization.getAddress());
            prSt.setString(4, organization.getInn());
            prSt.setString(5, organization.getTel());

            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User user){
        String insert = "INSERT INTO Users (name, login, password, " +
                "law_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1, user.getName());
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getPassword());
            prSt.setInt(4, user.getLaw());

            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteProduct(Content content){
        String delete = "DELETE FROM Product WHERE id=?";
        try{
            PreparedStatement prSt = getConnection().prepareStatement(delete);
            prSt.setInt(1,content.getId());

            prSt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWaybill(Waybill waybill){
        String delete = "DELETE FROM Waybill WHERE id=?";
        try{
            PreparedStatement prSt = getConnection().prepareStatement(delete);
            prSt.setInt(1, waybill.getId());

            prSt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteConfig(Config config){
        String delete = "DELETE FROM ProductWay WHERE id=?";
        try{
            PreparedStatement prSt = getConnection().prepareStatement(delete);
            prSt.setInt(1, config.getId());

            prSt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Content content){
            String update = "UPDATE Product set name=?, units=?, price=?, " +
                    "balance=?, actualDate=? where id=?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(update);
            prSt.setString(1, content.getName());
            prSt.setString(2, content.getUnits());
            prSt.setFloat(3, content.getPrice());
            prSt.setInt(4, content.getBalance());
            prSt.setDate(5, Date.valueOf(content.getActualDate()));
            prSt.setInt(6, content.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWaybill(Waybill waybill){
        String update = "UPDATE Waybill set number=?, type=?, date=?, " +
                "client_id=? where id=?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(update);
            prSt.setInt(1, waybill.getNumber());
            prSt.setInt(2, waybill.getType());
            prSt.setDate(3, Date.valueOf(waybill.getDate()));
            prSt.setInt(4, waybill.getClientId());

            prSt.setInt(5, waybill.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateConfig(Config config){
        String update = "UPDATE ProductWay set product_id=?, waybill_id=?, " +
                "amount=? where id=?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(update);
            prSt.setInt(1, config.getProductId());
            prSt.setInt(2, config.getWaybillId());
            prSt.setInt(3, config.getAmount());

            prSt.setInt(4, config.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(User user){
        String delete = "DELETE FROM Users WHERE id=?";
        try{
            PreparedStatement prSt = getConnection().prepareStatement(delete);
            prSt.setInt(1,user.getId());

            prSt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrganisation(Organization organization){
        String delete = "DELETE FROM Organization WHERE id=?";
        try{
            PreparedStatement prSt = getConnection().prepareStatement(delete);
            prSt.setInt(1,organization.getId());

            prSt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user){
        String update = "UPDATE Users set name=?, login=?, password=?, " +
                "law_id=? where id=?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(update);
            prSt.setString(1, user.getName());
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getPassword());
            prSt.setInt(4, user.getLaw());
            prSt.setInt(5, user.getId());


            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrganisation(Organization organization){
        String update = "UPDATE Organization set name=?, jobTitle=?, " +
                "address=?, INN=?, tel=? where id=?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(update);
            prSt.setString(1, organization.getName());
            prSt.setString(2, organization.getJobTitle());
            prSt.setString(3, organization.getAddress());
            prSt.setString(4, organization.getInn());
            prSt.setString(5, organization.getTel());
            prSt.setInt(6, organization.getId());


            prSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public ObservableList<Content> getSelectForName(String name){
        ObservableList<Content> list = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * from Product WHERE name=?";
        try {
            prSt = getConnection().prepareStatement(select);
            prSt.setString(1, name);


            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Content content = new Content(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "units"), resultSet.getFloat("price"),
                        resultSet.getInt("balance"), resultSet.getString(
                        "actualDate"));

                list.add(content);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Content> getSelectForUnits(String unit){
        ObservableList<Content> list = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * from Product WHERE units=?";
        try {
            prSt = getConnection().prepareStatement(select);
            prSt.setString(1, unit);


            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Content content = new Content(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "units"), resultSet.getFloat("price"),
                        resultSet.getInt("balance"), resultSet.getString(
                        "actualDate"));

                list.add(content);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Content> getSelectForAmount(String amount){
        ObservableList<Content> list = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * from Product WHERE balance < ?";
        try {
            prSt = getConnection().prepareStatement(select);
            prSt.setString(1, amount);


            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Content content = new Content(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "units"), resultSet.getFloat("price"),
                        resultSet.getInt("balance"), resultSet.getString(
                        "actualDate"));

                list.add(content);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Content> getSelectForPrice(String price){
        ObservableList<Content> list = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * from Product WHERE price < ?";
        try {
            prSt = getConnection().prepareStatement(select);
            prSt.setString(1, price);


            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Content content = new Content(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "units"), resultSet.getFloat("price"),
                        resultSet.getInt("balance"), resultSet.getString(
                        "actualDate"));

                list.add(content);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Content> getSelectForDate(String date){
        ObservableList<Content> list = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * FROM Product WHERE actualDate BETWEEN ? " +
                "AND CURRENT_DATE";
        try {
            prSt = getConnection().prepareStatement(select);
            prSt.setString(1, date);


            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Content content = new Content(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "units"), resultSet.getFloat("price"),
                        resultSet.getInt("balance"), resultSet.getString(
                        "actualDate"));

                list.add(content);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        String select = "SELECT * FROM Users WHERE login=? AND password=?";
        PreparedStatement prSt = null;
        try {
            prSt = getConnection().prepareStatement(select);
            prSt.setString(1,user.getLogin());
            prSt.setString(2,user.getPassword());

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resultSet;

    }

    public ObservableList<Content> getContent(){
        Content.contentList.clear();
        ObservableList<Content> list = Content.contentList;
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * FROM Product";

        try {
            prSt = getConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Content content = new Content(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                                "units"), resultSet.getFloat("price"),
                        resultSet.getInt("balance"), resultSet.getString(
                                "actualDate"));

                list.add(content);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Waybill> getWaybill(){
        Waybill.waybillList.clear();
        ObservableList<Waybill> list = Waybill.waybillList;

        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT Waybill.*, Organization.name as client_name from Waybill JOIN Organization WHERE Organization.id = Waybill.client_id";

        try {
            prSt = getConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Waybill waybill = new Waybill(resultSet.getInt("id"),
                        resultSet.getInt("number"), resultSet.getInt(
                        "type"), resultSet.getInt("client_id"),
                        resultSet.getString("date"), resultSet.getString(
                        "client_name"));

                list.add(waybill);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Config> getConfig(){
        Config.configList.clear();
        ObservableList<Config> list = Config.configList;

        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT ProductWay.*, Product.name FROM ProductWay JOIN Product ON ProductWay.product_id = Product.id";

        try {
            prSt = getConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Config config = new Config(resultSet.getInt("id"),
                        resultSet.getInt("product_id"), resultSet.getInt(
                        "waybill_id"), resultSet.getInt("amount"), resultSet.getString(
                        "name"));

                list.add(config);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<Organization> getOrganisation(){
        Organization.organizationList.clear();
        ObservableList<Organization> list = Organization.organizationList;
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * FROM Organization";

        try {
            prSt = getConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                Organization organisation =
                        new Organization(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "jobTitle"), resultSet.getString("address"),
                        resultSet.getString("INN"), resultSet.getString(
                        "tel"));

                list.add(organisation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public ObservableList<User> getUserList(){
        User.allUsersList.clear();
        ObservableList<User> list = User.allUsersList;
        ResultSet resultSet = null;
        PreparedStatement prSt = null;

        String select = "SELECT * FROM Users";

        try {
            prSt = getConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString(
                        "login"),
                        resultSet.getString("password"), resultSet.getInt(
                                "law_id"));

                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


}

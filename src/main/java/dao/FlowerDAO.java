package dao;

import entity.Flower;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAO extends AbstractDAO {

    private static FlowerDAO instance;

    private FlowerDAO() {

    }

    public static FlowerDAO getInstance() {
        if (instance == null) {
            instance = new FlowerDAO();
        }
        return instance;
    }


    public List<Flower> getAll() {
        Connection connection = null;
        Statement statement = null;
        List<Flower> flowers = new ArrayList<Flower>();

        try {
            connection = getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT f.id, fl.mark, f.price, " +
                    " f.ice_level, f.length_steak " +
                    "FROM flowers_exists f INNER JOIN flowers fl ON f.mark_id = fl.id WHERE f.bunch_id iS NULL ");

            while (rs.next()) {
                int id = rs.getInt(1);
                String mark = rs.getString(2);
                double price = rs.getDouble(3);
                int lengthSteack = rs.getInt(5);
                int iceLevel = rs.getInt(4);
                Flower flower = new Flower();
                flower.setId(id);
                flower.setName(mark);
                flower.setPrice(price);
                flower.setIceLevel(iceLevel);
                flower.setLengthSteack(lengthSteack);
                flowers.add(flower);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException  e) {
                e.printStackTrace();
            }
        }

        return flowers;
    }

    public void removeFlower(Flower flower) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM flowers_exists WHERE id = ?");

            statement.setInt(1, flower.getId());

            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addFlower(Flower flower){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO flowers_exists(mark_id, price, length_steak, ice_level) VALUES (?,?,?,?)");

            statement.setInt(1, getFlowerMarkIdByName(flower));
            statement.setDouble(2, flower.getPrice());
            statement.setInt(3,flower.getLengthSteack());
            statement.setInt(4, flower.getIceLevel());

            resultAdded = statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM user");
            rs.next();
            int userId = rs.getInt(1);
            flower.setId(userId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAdded;
    }

    public int getFlowerMarkIdByName(Flower flower){
        Connection connection = null;
        PreparedStatement statement = null;
        int id = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT id FROM flowers WHERE mark = ?");

            statement.setString(1,flower.getName());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException  e) {
                e.printStackTrace();
            }
        }

        return id;

    }

}
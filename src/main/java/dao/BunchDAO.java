package dao;

import entity.Bunch;
import entity.Flower;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BunchDAO extends AbstractDAO {

    private static BunchDAO instance;

    private BunchDAO() {

    }

    public static BunchDAO getInstance() {
        if (instance == null) {
            instance = new BunchDAO();
        }
        return instance;
    }

    public void addBunch(Bunch bunch, User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO bunch(id) VALUE (@id)");

            int result = statement.executeUpdate();
            System.out.println(result);

            statement = connection.prepareStatement("SELECT MAX(id) FROM bunch");

            ResultSet rs = statement.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            bunch.setId(id);

            statement = connection.prepareStatement("INSERT INTO user_bunch(user_id, bunch_id) VALUES (?, ?)");

            statement.setInt(1, user.getId());
            statement.setInt(2, bunch.getId());

            result = statement.executeUpdate();
            System.out.println(result);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Bunch bunchById(int id, User user) {
        Bunch bunch = new Bunch();
        bunch.setId(id);
        return bunch;
    }

    public List<Flower> getFlowersToBunch(Bunch bunch) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Flower> flowers = new ArrayList<Flower>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(" SELECT f.id, fl.mark, f.price, " +
                    " f.ice_level, f.length_steak " +
                    "FROM flowers_exists f INNER JOIN flowers fl ON f.mark_id = fl.id WHERE f.bunch_id = ?  ");

            System.out.println("BunchId : " + bunch.getId());
            statement.setInt(1, bunch.getId());

            ResultSet rs = statement.executeQuery();

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

            bunch.setFlowers(flowers);

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
        return bunch.getFlowers();
    }

    public void addFlowerToBunch(Bunch bunch, Flower flower) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE flowers_exists SET bunch_id = ? WHERE id = ?");

            statement.setInt(1, bunch.getId());
            statement.setInt(2, flower.getId());

            int result = statement.executeUpdate();
            System.out.println(result);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Bunch> getAllBunches(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Bunch> bunches = new ArrayList<Bunch>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(" SELECT bunch_id FROM user_bunch WHERE user_id = ? ");

            statement.setInt(1, user.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                Bunch bunch = new Bunch();
                bunch.setId(id);
                bunches.add(bunch);
            }

            user.setBunch(bunches);

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
        return bunches;
    }

    public void removeFlower(Bunch bunch, Flower flower) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE flowers_exists SET bunch_id = NULL WHERE id = ? AND bunch_id = ?");

            statement.setInt(1, flower.getId());
            statement.setInt(2, bunch.getId());

            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

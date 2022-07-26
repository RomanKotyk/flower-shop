package dao;

public class DAOFactory {

    private static DAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    public FlowerDAO getFlowerDAO() {
        return FlowerDAO.getInstance();
    }

    public BunchDAO getBunchDAO() {
        return BunchDAO.getInstance();
    }

    public UserDAO getUserDAO() {
        return UserDAO.getInstance();
    }

}

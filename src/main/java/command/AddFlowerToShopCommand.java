package command;

import dao.DAOFactory;
import dao.FlowerDAO;
import dao.UserDAO;
import entity.Flower;

import javax.servlet.http.HttpServletRequest;

public class AddFlowerToShopCommand implements Command{

    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        double price = Double.valueOf(request.getParameter("price"));
        int length_steak = Integer.parseInt(request.getParameter("steak"));
        int ice_level = Integer.parseInt(request.getParameter("ice"));

        Flower flower = new Flower(name, price, length_steak, ice_level);
        DAOFactory factory = DAOFactory.getInstance();
        FlowerDAO flowerDAO = factory.getFlowerDAO();
        flowerDAO.addFlower(flower);

        return "controller?action=main";

    }
}

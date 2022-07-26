package command;

import dao.BunchDAO;
import dao.DAOFactory;
import entity.Bunch;
import entity.Flower;
import entity.User;

import javax.servlet.http.HttpServletRequest;

public class AddFlowerToBunchCommand implements Command {

    public String execute(HttpServletRequest request) {
        int bunchId = Integer.valueOf(request.getParameter("bunch_id"));
        int flowerId = Integer.parseInt(request.getParameter("flower_id"));
        String name = request.getParameter("name");
        double price = Double.valueOf(request.getParameter("price"));
        int iceLevel = Integer.parseInt(request.getParameter("iceLevel"));
        int lengthSteack = Integer.parseInt(request.getParameter("lengthSteack"));
        Flower flower = new Flower(name, price, lengthSteack, iceLevel);
        flower.setId(flowerId);
        User currentUser = (User) request.getSession().getAttribute("user");
        DAOFactory daoFactory = DAOFactory.getInstance();
        BunchDAO bunchDAO = daoFactory.getBunchDAO();
        Bunch currentBunch = bunchDAO.bunchById(bunchId, currentUser);
        bunchDAO.addFlowerToBunch(currentBunch, flower);
//        FlowerDAO flowerDAO = daoFactory.getFlowerDAO();
//        flowerDAO.removeFlower(flower);
        return "controller?action=main";
    }

}

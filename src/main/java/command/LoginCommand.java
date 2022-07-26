package command;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUser(name, password);
        String resultPage = (user == null) ? "login.jsp" : "controller?action=main";

        if (user == null) {
            request.setAttribute("notExists", "This user not exists");
        } else {
            request.getSession().setAttribute("user", user);
        }

        return resultPage;
    }
}

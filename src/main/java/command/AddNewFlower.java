package command;

import javax.servlet.http.HttpServletRequest;

public class AddNewFlower implements Command{


    public String execute(HttpServletRequest request) {
        return "add_flower_shop.jsp";
    }
}

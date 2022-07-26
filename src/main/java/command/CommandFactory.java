package command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory factory = new CommandFactory();

    private Map<String, Command> comands = new HashMap<String, Command>();

    private CommandFactory() {

    }

    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    {
        comands.put("register", new RegisterCommand());
        comands.put("logout", new LogoutCommand());
        comands.put("main", new MainCommand());
        comands.put("create_new_bunch", new CreateBunchCommand());
        comands.put("login", new LoginCommand());
        comands.put("add_to_bunch", new AddToBunchCommand());
        comands.put("add_flower", new AddFlowerToBunchCommand());
        comands.put("bunch_flowers", new BunchFlowersCommand());
        comands.put("remove_flower", new RemoveFlowerCommand());
        comands.put("add_new_flower", new AddNewFlower());
        comands.put("add_flower_to_shop", new AddFlowerToShopCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        System.out.println(action);
        Command command = comands.get(action);
        return command;
    }

}
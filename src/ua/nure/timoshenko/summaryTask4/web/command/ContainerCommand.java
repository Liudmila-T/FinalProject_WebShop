package ua.nure.timoshenko.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.web.command.admin.*;
import ua.nure.timoshenko.summaryTask4.web.command.all.*;
import ua.nure.timoshenko.summaryTask4.web.command.client.*;



import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 *
 * @author L.Timoshenko
 *
 */
public class ContainerCommand {

	private static final Logger LOG = Logger.getLogger(ContainerCommand.class);

	private static Map<String, Command> commands = new TreeMap<>();

	static {

		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("sortProducts", new SortProductsCommand());
		commands.put("addToCart", new AddToCartCommand());
		commands.put("viewRegistration", new ViewRegistrationFormCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("listProducts", new ListProductsCommand());
		commands.put("noCommand", new NoCommand());

		// client commands
		commands.put("makeOrder", new MakeOrderCommand());
		commands.put("personalAccount", new PersonalAccountCommand());

		// admin commands
		commands.put("listOrders", new ListOrdersCommand());
		commands.put("sortOrders", new SortOrdersCommand());
		commands.put("changeStatus", new ChangeStatusCommand());
		commands.put("viewProducts", new AdminListProductsCommand());
		commands.put("removeProduct", new RemoveProductCommand());
		commands.put("addProduct", new AddProductCommand());
		commands.put("listUsers", new ListUsersCommand());
		commands.put("changeRole", new ChangeRoleUserCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 *
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}

}

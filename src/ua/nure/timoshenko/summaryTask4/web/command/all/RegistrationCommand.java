package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Registration command.
 *
 * @author L.Timoshenko
 *
 */
public class RegistrationCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger
			.getLogger(RegistrationCommand.class);
	public static final int LENGTH = 15;
	public static final int LENGTH_INT = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		LOG.debug("Command starts.");

		DBManager manager = DBManager.getInstance();
		String name = request.getParameter("name");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		String email = request.getParameter("email");

		// error handler
		String errorMessage;
		String forward = Path.PAGE_ERROR_PAGE;



		if (name.length() > LENGTH || login.length() > LENGTH || password.length() > LENGTH_INT
				|| email.length() > LENGTH) {
			errorMessage = "Fields must contain a maximum of 15 characters, password - 10";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		if (password.equals(repeatPassword)) {
			if (manager.hasLoginInDB(login)) {
				errorMessage = "A user with this login already exists. Please re-enter.";
				request.setAttribute("errorMessage", errorMessage);
				LOG.error("errorMessage --> " + errorMessage);
				return forward;
			} else {
				manager.createUser(name, login, password, email);
			}
		} else {
			errorMessage = "Passwords do not match. Please re-enter.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}
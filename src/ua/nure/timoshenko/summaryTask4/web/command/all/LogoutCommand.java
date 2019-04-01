package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Logout command.
 *
 * @author L.Timoshenko
 *
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -5834484888825715763L;

	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) {
		LOG.debug("Command starts");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}

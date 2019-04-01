package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * No command.
 *
 * @author L.Timoshenko
 *
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = 2464754644728781535L;

	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		LOG.debug("Command starts");

		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

		LOG.debug("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}

}

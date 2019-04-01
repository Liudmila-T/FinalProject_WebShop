package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.entity.User;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Update settings command.
 *
 * @author L.Timoshenko
 *
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger
			.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {

		LOG.debug("Command starts");

		// UPDATE USER

		User user = (User) request.getSession().getAttribute("user");
		boolean updateUser = false;

		// update name
		String name = request.getParameter("name");
		if (name != null && !name.isEmpty()) {
			user.setName(name);
			LOG.trace("User first name was set: name --> " + name);
			updateUser = true;
		}

		String localeToSet = request.getParameter("localeToSet");
		if (localeToSet != null && !localeToSet.isEmpty()) {
			HttpSession session = request.getSession();
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale",
					localeToSet);
			session.setAttribute("defaultLocale", localeToSet);
			LOG.trace("User locale was set: localeToSet --> " + localeToSet);
			if (name != null && !name.isEmpty()) {
				user.setLocaleName(localeToSet);
				updateUser = true;
			}
		}

		if (updateUser) {
			DBManager.getInstance().updateUser(user);
		}

		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}
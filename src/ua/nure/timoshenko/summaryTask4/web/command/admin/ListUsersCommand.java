package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.UserBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

/**
 * List users command.
 *
 * @author L.Timoshenko
 *
 */
public class ListUsersCommand extends Command {

	private static final long serialVersionUID = 1094133346815712353L;

	private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		List<UserBean> userBeans = DBManager.getInstance().getAllUserBeans();
		LOG.trace("Found in DB: userBeans --> " + userBeans);

		userBeans.sort(Comparator.comparingInt(UserBean::getUserId));

		session.setAttribute("userBeans", userBeans);
		LOG.trace("Set the request attribute: userBeans --> " + userBeans);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_USERS;
	}
}


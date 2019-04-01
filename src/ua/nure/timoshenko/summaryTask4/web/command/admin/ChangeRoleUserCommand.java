package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.UserBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Block user command.
 *
 * @author L.Timoshenko
 *
 */
public class ChangeRoleUserCommand extends Command {

	private static final long serialVersionUID = -778535254076631303L;

	private static final Logger LOG = Logger.getLogger(ChangeRoleUserCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		LOG.debug(" ChangeRoleUserCommand starts");

		HttpSession session = request.getSession();
		String[] roleNames = request.getParameterValues("roleName");

		@SuppressWarnings("unchecked")
		List<UserBean> list = (List<UserBean>) session
				.getAttribute("userBeans");

		list = changeRoles(list, roleNames);

		session.setAttribute("userBeanList", list);

		LOG.debug("Command finished");
		return Path.COMMAND_LIST_USERS;
	}

	private static List<UserBean> changeRoles(List<UserBean> list,
                                              String[] roleNames) {
		if (list.size() == roleNames.length) {
			for (int i = 0; (i < list.size()); i++) {
				if (!list.get(i).getRoleName().equals(roleNames[i])) {
					list.get(i).setRoleName(roleNames[i]);
					DBManager.getInstance().changeUserRole(
							list.get(i).getRoleName(), list.get(i).getUserId());
				}
			}
		}
		return list;
	}
}

package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.UserOrderBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Change status command.
 *
 * @author L.Timoshenko
 *
 */
public class ChangeStatusCommand extends Command {

	private static final long serialVersionUID = 1319342976240501165L;

	private static final Logger LOG = Logger
			.getLogger(ChangeStatusCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		String[] statusesNames = request.getParameterValues("statusName");

		@SuppressWarnings("unchecked")
		List<UserOrderBean> list = (List<UserOrderBean>) session
				.getAttribute("userOrderBeanList");
		LOG.trace("Get attribute userOrderBeanList--> "+ list);
		list = changeStatuses(list, statusesNames);

		session.setAttribute("userOrderBeanList", list);

		LOG.debug("Command finished");
		return Path.COMMAND_LIST_ORDERS;
	}

	private static List<UserOrderBean> changeStatuses(List<UserOrderBean> list,
			String[] statuses) {
		for (int i = 0; (i < list.size() && i < statuses.length); i++) {
			if (!list.get(i).getStatusName().equals(statuses[i])) {
				list.get(i).setStatusName(statuses[i]);
				DBManager.getInstance().changeOrderStatus(
						list.get(i).getStatusName(), list.get(i).getOrderId());
			}
		}
		LOG.trace("Method changeStatus, list --> "+list);
		return list;
	}
}

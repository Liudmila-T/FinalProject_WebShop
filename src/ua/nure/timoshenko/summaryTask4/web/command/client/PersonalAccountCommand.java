package ua.nure.timoshenko.summaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.OrderBean;
import ua.nure.timoshenko.summaryTask4.db.entity.User;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Personal account command.
 *
 * @author L.Timoshenko
 *
 */
public class PersonalAccountCommand extends Command {

	private static final long serialVersionUID = 5288269143051697851L;

	private static final Logger LOG = Logger
			.getLogger(PersonalAccountCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if (!(user == null)) {
			List<OrderBean> orderBeans = DBManager.getInstance()
					.getAllOrderBeansByUser(user);

			request.setAttribute("orderBeans", orderBeans);

			LOG.trace("Set the request attribute: orderBeans --> " + orderBeans);
		}

		LOG.debug("Command finished");
		return Path.PAGE_PERSONAL_ACCOUNT;
	}

}

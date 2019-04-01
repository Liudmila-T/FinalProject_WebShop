package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.ProductBean;
import ua.nure.timoshenko.summaryTask4.db.bean.UserOrderBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List orders command.
 *
 * @author L.Timoshenko
 *
 */
public class ListOrdersCommand extends Command {

	private static final long serialVersionUID = -7805212957706778647L;

	private static final Logger LOG = Logger.getLogger(ListOrdersCommand.class);

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response)  {
		LOG.debug("Commands starts");

		HttpSession session = request.getSession();
		List<UserOrderBean> userOrderBeanList = DBManager.getInstance()
				.getAllUserOrderBean();
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);
		//sort
		userOrderBeanList.sort(Comparator.comparing(UserOrderBean::getOrderId));
		// put user order beans list to request
		session.setAttribute("userOrderBeanList", userOrderBeanList);
		LOG.trace("Set the request attribute: userOrderBeanList --> "
				+ userOrderBeanList);

		LOG.debug("Commands finished");
		return Path.PAGE_LIST_ORDERS;
	}



}

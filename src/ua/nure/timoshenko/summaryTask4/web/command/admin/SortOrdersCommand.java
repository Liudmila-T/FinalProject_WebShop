package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.UserOrderBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sort orders command.
 *
 * @author L.Timoshenko
 */
public class SortOrdersCommand extends Command {

    private static final long serialVersionUID = -8813595304520713819L;

    private static final Logger LOG = Logger.getLogger(SortOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("Commands starts");
        HttpSession session = request.getSession();

        List<UserOrderBean> userOrderBeanList = DBManager.getInstance()
                .getAllUserOrderBean();
        //sort user order bean by order Id
        userOrderBeanList.sort(Comparator.comparing(UserOrderBean::getOrderId));

        LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

        // get user order beans by status
        String status = request.getParameter("status");
        userOrderBeanList = getUserOrderBeansByStatus(userOrderBeanList, status);

        // put user order beans list to request
        session.setAttribute("userOrderBeanList", userOrderBeanList);
        LOG.trace("Set the request attribute: userOrderBeanList --> "
                + userOrderBeanList);

        LOG.debug("Commands finished");
        return Path.PAGE_LIST_ORDERS;
    }

    /**
     * @param userOrderBeanList
     * @param status
     * @return List of User Order Beans
     */
    private static List<UserOrderBean> getUserOrderBeansByStatus(
            List<UserOrderBean> userOrderBeanList, String status) {
        if (status.equals("all")) {
            return userOrderBeanList;
        } else {
            List<UserOrderBean> userOrderBeans = new ArrayList<>();
            for (UserOrderBean userOrderBean : userOrderBeanList) {
                if (status.equals(userOrderBean.getStatusName())) {
                    userOrderBeans.add(userOrderBean);
                }
            }
            return userOrderBeans;
        }
    }
}



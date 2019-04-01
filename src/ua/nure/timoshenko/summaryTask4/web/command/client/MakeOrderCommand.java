package ua.nure.timoshenko.summaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.CartProductBean;
import ua.nure.timoshenko.summaryTask4.db.bean.OrderBean;
import ua.nure.timoshenko.summaryTask4.db.entity.User;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.timoshenko.summaryTask4.db.DBManager.extractAllAmount;

/**
 * Make order command.
 *
 * @author L.Timoshenko
 */
public class MakeOrderCommand extends Command {

    private static final long serialVersionUID = -2081693120949720265L;

    private static final Logger LOG = Logger.getLogger(MakeOrderCommand.class);
    private static final String REGEX = "&\\w{16}=(\\d+)&\\w{6}=(\\d+)";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOG.debug("Commands starts");
        DBManager manager = DBManager.getInstance();

        //get the query string and select only pairs of values
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(request.getQueryString());

        // map to store pairs of values - idProduct and quantityForOrder
        Map<Integer, Integer> map = new HashMap<>();
        while (matcher.find()) {
            map.put(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(1)));
        }

        HttpSession session = request.getSession();
        List<CartProductBean> cartProductBeans = (List<CartProductBean>) session
                .getAttribute("cartList");
        LOG.trace("session.getAttribute - cartList--> " + cartProductBeans);

        List<CartProductBean> newCartProductBeans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (CartProductBean bean : cartProductBeans) {
                if (manager.getIdByProductName(bean.getProductName()) == entry.getKey()) {
                    newCartProductBeans.add(bean);
                }
            }
        }
        LOG.trace("newCartProductBeans--> " + newCartProductBeans);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < newCartProductBeans.size(); i++) {
                if (newCartProductBeans.get(i).getId() == entry.getKey()) {
                    newCartProductBeans.get(i).setQuantityForOrder(entry.getValue());
                }
            }
            DBManager.getInstance().changeQuantityForOrders(
                    entry.getKey(), entry.getValue());
        }
        LOG.trace("newCartProductBeans--> add Quantity " + newCartProductBeans);

        User user = (User) session.getAttribute("user");
        int amount = 0;
        if (!(user == null)) {
            manager.makeOrder(user, newCartProductBeans);
            amount = extractAllAmount(user);
        }
        session.setAttribute("amount", amount);


        // cleaning the cart list
        newCartProductBeans = null;

        session.setAttribute("cartList", newCartProductBeans);
        LOG.trace("Set the request attribute(null): products --> "
                + newCartProductBeans);

        LOG.debug("Command finished");
        response.sendRedirect("controller?command=personalAccount");
        return null;
    }
}




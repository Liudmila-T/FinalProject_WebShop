package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.OrderBean;
import ua.nure.timoshenko.summaryTask4.db.bean.ProductBean;
import ua.nure.timoshenko.summaryTask4.db.entity.User;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static ua.nure.timoshenko.summaryTask4.db.DBManager.extractAllAmount;

/**
 * List products command.
 *
 * @author L.Timoshenko
 */
public class ListProductsCommand extends Command {

    private static final long serialVersionUID = 4414370460824078383L;

    private static final Logger LOG = Logger
            .getLogger(ListProductsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        List<ProductBean> productBeans = DBManager.getInstance()
                .getAllProductBeans();
        LOG.trace("Found in DB: productsList --> " + productBeans);

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        int amount = 0;
        if (user != null) {
            amount = extractAllAmount(user);
        }
        session.setAttribute("amount", amount);

        request.setAttribute("productBeans", productBeans);

        LOG.trace("Set the request attribute: products --> " + productBeans);


        LOG.debug("Command finished");
        return Path.PAGE_LIST_PRODUCTS;
    }
}

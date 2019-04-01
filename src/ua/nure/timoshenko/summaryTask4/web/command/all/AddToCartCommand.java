package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.CartProductBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;
/**
 * Add to cart command.
 *
 * @author L.Timoshenko
 */
public class AddToCartCommand extends Command {

    private static final long serialVersionUID = -3379162513526017164L;

    private static final Logger LOG = Logger.getLogger(AddToCartCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        LOG.debug("Commands starts");

        DBManager manager = DBManager.getInstance();
        boolean flag = false;

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<CartProductBean> list = (List<CartProductBean>) session
                .getAttribute("cartList");

        // get all id
        String[] itemId = request.getParameterValues("itemId");

        // get cart product bean
        if (itemId != null) {
            int[] itemIds = new int[itemId.length];
            for (int i = 0; i < itemIds.length; i++) {
                itemIds[i] = Integer.parseInt(itemId[i]);
            }
            List<CartProductBean> cartProductBeans = manager
                    .getCartBeanByProductId(itemIds);

              if (!(list == null)) {
                cartProductBeans.addAll(list);
            }

            LOG.trace("Found in DB: cartList --> " + cartProductBeans);
            session.setAttribute("cartList", cartProductBeans);
            LOG.trace("Set the request attribute: products --> "
                    + cartProductBeans);
        }

        // error handler
        String errorMessage;
        String forward = Path.PAGE_ERROR_PAGE;
        if (flag) {
            errorMessage = "Selected item is not available.";
            request.setAttribute("errorMessage", errorMessage);
            LOG.error("errorMessage --> " + errorMessage);
            return forward;
        }

        LOG.debug("Command finished");
        response.sendRedirect(Path.PAGE_CART);
        return null;
    }
}

package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.bean.ProductBean;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

/**
 * Admin list products command.
 *
 * @author L.Timoshenko
 *
 */
public class AdminListProductsCommand extends Command {

	private static final long serialVersionUID = 3312397409862494710L;

	private static final Logger LOG = Logger
			.getLogger(AdminListProductsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		LOG.debug("Command starts");

		List<ProductBean> productBeans = DBManager.getInstance().getAllProductBeans();
		LOG.trace("Found in DB: productsList --> " + productBeans);
		//sort list product by id
		productBeans.sort(Comparator.comparing(ProductBean::getId));
		request.setAttribute("productBeans", productBeans);
		LOG.trace("Set the request attribute: products --> " + productBeans);

		LOG.debug("Command finished");
		return Path.PAGE_ADMIN_LIST_PRODUCTS;
	}
}

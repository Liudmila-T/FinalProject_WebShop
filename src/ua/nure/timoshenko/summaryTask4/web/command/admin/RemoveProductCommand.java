package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Remove product command.
 *
 * @author L.Timoshenko
 *
 */
public class RemoveProductCommand extends Command {

	private static final long serialVersionUID = -2659054867831739213L;

	private static final Logger LOG = Logger
			.getLogger(RemoveProductCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response){

		LOG.debug("Commands starts");

		DBManager manager = DBManager.getInstance();

		// get all id
		String[] itemId = request.getParameterValues("itemId");

		// error handler
		String errorMessage;
		String forward = Path.PAGE_ERROR_PAGE;

		// get cart product bean
		if (!(itemId == null)) {
			int[] itemIds = new int[itemId.length];
			for (int i = 0; i < itemIds.length; i++) {
				itemIds[i] = Integer.parseInt(itemId[i]);
			}
			for(int id: itemIds){
				if(!manager.hasProductInOrders(id)){
					manager.removeProductsById(id);
				} else{
					errorMessage = "Delete the selected product does not work, because it is in order.";
					request.setAttribute("errorMessage", errorMessage);
					LOG.error("errorMessage --> " + errorMessage);
					return forward;
				}
			}


			LOG.trace("Remove products in DB: itemIds --> "
					+ Arrays.toString(itemIds));
		}

		LOG.debug("Command finished");
		return Path.COMMAND_ADMIN_LIST_PRODUCTS;
	}

}

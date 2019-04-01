package ua.nure.timoshenko.summaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Add a new product command.
 *
 * @author L.Timoshenko
 */
public class AddProductCommand extends Command {

    private static final long serialVersionUID = -1670392730590214605L;

    private static final Logger LOG = Logger.getLogger(AddProductCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException{

        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();

        String idStr = request.getParameter("id");
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String color = request.getParameter("color");
        String weightStr = request.getParameter("weight");
        String dateManufacturerStr = request.getParameter("dateManufacturer");
        String quantityInStockStr = request.getParameter("quantityInStock");

        // error handler
        String errorMessage;
        String forward = Path.PAGE_ERROR_PAGE;

       // try {
            int id = Integer.parseInt(idStr);
            int price = Integer.parseInt(priceStr);
            int weight = Integer.parseInt(weightStr);
            int quantityInStock = Integer.parseInt(quantityInStockStr);
            Date dateManufacturer = Date.valueOf(dateManufacturerStr);

            if (manager.hasProductInDB(id)) {
                manager.updateProduct(id, category, name, price, color,
                        weight, dateManufacturer, quantityInStock);
            } else {
                manager.addProduct(id, category, name, price, color, weight, dateManufacturer, quantityInStock);
            }

        LOG.debug("Command finished");
        response.sendRedirect("controller?command=viewProducts");
        return null;
    }

}
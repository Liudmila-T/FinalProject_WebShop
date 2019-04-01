package ua.nure.timoshenko.summaryTask4.web.command.all;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.Path;
import ua.nure.timoshenko.summaryTask4.db.DBManager;
import ua.nure.timoshenko.summaryTask4.db.Role;
import ua.nure.timoshenko.summaryTask4.db.bean.ProductBean;
import ua.nure.timoshenko.summaryTask4.db.entity.Entity;
import ua.nure.timoshenko.summaryTask4.db.entity.User;
import ua.nure.timoshenko.summaryTask4.web.command.Command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sort products command.
 *
 * @author L.Timoshenko
 */
public class SortProductsCommand extends Command {

    private static final long serialVersionUID = -4088773403446786429L;

    private static final Logger LOG = Logger
            .getLogger(ListProductsCommand.class);

  @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        LOG.debug("Command starts");

        String errorMessage;
        String forward = Path.PAGE_ERROR_PAGE;

        // get products list
        List<ProductBean> productBeans = DBManager.getInstance().getAllProductBeans();
        LOG.trace("Found in DB: productsList --> " + productBeans);

        // sort
        String sort = request.getParameter("sort");
        LOG.trace("Sorting by --> " + sort);
        sortProduct(productBeans, sort);


        // get products by price
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        if (!minPrice.isEmpty() || !maxPrice.isEmpty()) {
            try {
                int min = Integer.MIN_VALUE;
                int max = Integer.MAX_VALUE;
                if (!minPrice.isEmpty()) {
                    min = Integer.parseInt(minPrice);
                }
                if (!maxPrice.isEmpty()) {
                    max = Integer.parseInt(maxPrice);
                }
                productBeans = getProductsByPrice(productBeans, min, max);
            } catch (NumberFormatException e) {
                errorMessage = "Price must be entered in numeric format.";
                request.setAttribute("errorMessage", errorMessage);
                LOG.error("errorMessage --> " + errorMessage);
                return forward;
            }
        }

        // get products by color
        String color = request.getParameter("color");
        productBeans = getProductsByColor(productBeans, color);

        // get products by category
        String category = request.getParameter("category");
        productBeans = getProductsByCategory(productBeans, category);

        // get products by weight
        String minWeight = request.getParameter("minWeight");
        String maxWeight = request.getParameter("maxWeight");

        if (!minWeight.isEmpty() || !maxWeight.isEmpty()) {
            try {
                int min = Integer.MIN_VALUE;
                int max = Integer.MAX_VALUE;
                if (!minWeight.isEmpty()) {
                    min = Integer.parseInt(minWeight);
                }
                if (!maxWeight.isEmpty()) {
                    max = Integer.parseInt(maxWeight);
                }
                productBeans = getProductsByWeight(productBeans, min, max);
            } catch (NumberFormatException e) {
                errorMessage = "Weight must be entered in numeric format.";
                request.setAttribute("errorMessage", errorMessage);
                LOG.error("errorMessage --> " + errorMessage);
                return forward;
            }
        }

        request.setAttribute("productBeans", productBeans);
        LOG.trace("Set the request attribute: products --> " + productBeans);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        LOG.debug("Command finished");

        if (!(user == null)) {
            Role userRole = Role.getRole(user);
            if (userRole == Role.ADMIN) {
                return Path.PAGE_ADMIN_LIST_PRODUCTS;
            }
        }
        return Path.PAGE_LIST_PRODUCTS;

    }

    private void sortProduct(List<ProductBean> productBeans, String sort) {
        switch (sort) {
            case "byNameAz":
                productBeans.sort(Comparator.comparing(ProductBean::getName));
                break;
            case "byNameZa":
                productBeans.sort((product1, product2) -> product2.getName().compareTo(product1.getName()));
                break;
            case "byPriceUp":
                productBeans.sort((product1, product2) -> product2.getPrice() - (product1.getPrice()));
                break;
            case "byPriceDown":
                productBeans.sort(Comparator.comparingInt(ProductBean::getPrice));
                break;
            case "byNovelty":
                productBeans.sort(Comparator.comparing(ProductBean::getDateManufacturer));
                break;
            case "byCategory":
                productBeans.sort(Comparator.comparing(ProductBean::getCategory));
                break;
            default:
                productBeans.sort(Comparator.comparingInt(Entity::getId));
                break;
        }
    }

    /**
     * @param productBeans
     * @param minPrice
     * @param maxPrice
     * @return List of Product Beans
     */
    private static List<ProductBean> getProductsByPrice(
            List<ProductBean> productBeans, int minPrice, int maxPrice) {
        List<ProductBean> result = new ArrayList<>();
        for (ProductBean product : productBeans) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * @param productBeans
     * @param color
     * @return List of Product Beans
     */
    private static List<ProductBean> getProductsByColor(
            List<ProductBean> productBeans, String color) {
        if (color.equals("all")) {
            return productBeans;
        } else {
            List<ProductBean> result = new ArrayList<>();
            for (ProductBean productBean : productBeans) {
                if (color.equals(productBean.getColor())) {
                    result.add(productBean);
                }
            }
            return result;
        }

    }

    /**
     * @param productBeans
     * @param category
     * @return List of Product Beans
     */
    private static List<ProductBean> getProductsByCategory(
            List<ProductBean> productBeans, String category) {
        if (category.equals("all")) {
            return productBeans;
        } else {
            List<ProductBean> result = new ArrayList<>();
            for (ProductBean productBean : productBeans) {
                if (category.equals(productBean.getCategory())) {
                    result.add(productBean);
                }
            }
            return result;
        }
    }

    /**
     * @param productBeans
     * @param minWeight
     * @param maxWeight
     * @return List of Product Beans
     */
    private static List<ProductBean> getProductsByWeight(
            List<ProductBean> productBeans, int minWeight, int maxWeight) {
        List<ProductBean> result = new ArrayList<>();
        for (ProductBean product : productBeans) {
            if (product.getWeight() >= minWeight && product.getWeight() <= maxWeight) {
                result.add(product);
            }
        }
        return result;
    }
}



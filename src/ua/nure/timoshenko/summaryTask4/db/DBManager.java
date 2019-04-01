package ua.nure.timoshenko.summaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.timoshenko.summaryTask4.db.bean.*;
import ua.nure.timoshenko.summaryTask4.db.entity.Product;
import ua.nure.timoshenko.summaryTask4.db.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.timoshenko.summaryTask4.db.SqlForDB.*;

/**
 * DB manager.
 *
 * @author L.Timoshenko
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    private DBManager() {

    }

    /**
     * @return object of DBManager
     */
    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method  must configure the Date Source and the Connections Pool in
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            // KIDS_TOYS - the name of data source
            DataSource ds = (DataSource) envContext.lookup("jdbc/KIDS_TOYS");
            con = ds.getConnection();
        } catch (NamingException ex) {
            LOG.error("Cannot obtain a connection from the pool", ex);
        }
        return con;
    }

    /**
     * Returns all product beans.
     *
     * @return List of product beans.
     */
    public List<ProductBean> getAllProductBeans() {
        List<ProductBean> productBeans = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt
                    .executeQuery(SQL_GET_ALL_PRODUCT_BEAN);
            while (rs.next()) {
                productBeans.add(extractProductBean(rs));
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain product beans", ex);
        } finally {
            close(rs);
            close(stmt);
            commitAndClose(con);
        }
        return productBeans;
    }


    /**
     * Returns list of order beans.
     *
     * @param user .
     * @return List of order beans
     */
    public List<OrderBean> getAllOrderBeansByUser(User user) {
        List<OrderBean> orderBeans = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_PRODUCT_BEAN_BY_USER);
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orderBeans.add(extractOrderBean(rs));
            }
            LOG.trace("orderBeans-->" + orderBeans);

        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain order bean items", ex);
        } finally {
            close(rs);
            close(pstmt);
            commitAndClose(con);
        }
        return orderBeans;
    }

    /**
     * Returns all user beans.
     *
     * @return List of user beans.
     */
    public List<UserBean> getAllUserBeans() {
        List<UserBean> userBeans = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt
                    .executeQuery(SQL_GET_ALL_USER_BEAN);
            while (rs.next()) {
                userBeans.add(extractUserBean(rs));
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain product beans", ex);
        } finally {
            close(rs);
            close(stmt);
            commitAndClose(con);
        }
        return userBeans;
    }

    /**
     * Returns all user beans.
     *
     * @return List of user beans.
     */
    public List<UserOrderBean> getAllUserOrderBean() {
        List<UserOrderBean> userOrderBeans = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt
                    .executeQuery(SQL_GET_ALL_USER_ORDER_BEAN);

            while (rs.next()) {
                userOrderBeans.add(extractUserOrderBean(rs));
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain user order beans", ex);
        } finally {
            close(rs);
            close(stmt);
            commitAndClose(con);
        }
        return userOrderBeans;
    }


    /**
     * Returns cart product beans with given identifiers.
     *
     * @param productId Identifiers of product items.
     * @return List of cart product beans.
     */
    public List<CartProductBean> getCartBeanByProductId(int... productId) {
        List<CartProductBean> productBeans = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            for (int i : productId) {
                pstmt = con.prepareStatement(SQL_GET_CART_BEAN_BY_PRODUCT_ID);
                pstmt.setInt(1, i);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    productBeans.add(extractCartProductBean(rs));
                }
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain cart product beans", ex);
        } finally {
            close(rs);
            close(pstmt);
            commitAndClose(con);
        }
        return productBeans;
    }

    /**
     * Returns user with given login.
     *
     * @param login
     * @return User object.
     */
    public User getUserByLogin(String login) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN);
            pstmt.setString(1, login);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return extractUser(resultSet);
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain user by login", ex);
        } finally {
            close(resultSet);
            close(pstmt);
            commitAndClose(con);
        }
        return null;
    }

    /**
     * Returns id by name product
     *
     * @param nameProduct
     * @return User object.
     */
    public int getIdByProductName(String nameProduct) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SELECT_ID_FROM_PRODUCTS_WHERE_NAME);
            pstmt.setString(1, nameProduct);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return extractProduct(resultSet).getId();
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain user by login", ex);
        } finally {
            close(resultSet);
            close(pstmt);
            commitAndClose(con);
        }
        return -1;
    }

    /**
     * Update user.
     *
     * @param user user to update.
     */
    public void updateUser(User user) {
        Connection con = null;
        try {
            con = getConnection();
            updateUser(con, user);
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot update a user", ex);
        } finally {
            commitAndClose(con);
        }
    }

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws SQLException
     */
    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con
                    .prepareStatement(SQL_UPDATE_USERS);
            int k = 1;
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getName());
            pstmt.setString(k++, user.getLocaleName());
            pstmt.setInt(k, user.getId());
            pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    /**
     * Make order.
     *
     * @param user , id products
     * @return true if order made
     */
    public boolean makeOrder(User user, List<CartProductBean> list) {
        PreparedStatement pstmt1 = null, pstmt2 = null;
        Connection con = null;
        boolean result = false;
        Date date = new java.sql.Date(System.currentTimeMillis());
        int orderId = 0;
        int amount = 0;
        for (CartProductBean cartProductBean : list) {
            amount += cartProductBean.getPrice() * cartProductBean.getQuantityForOrder();
        }

        try {
            con = getConnection();
            pstmt1 = con.prepareStatement(INSERT_INTO_ORDERS_VALUES,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt1.setInt(1, user.getId());
            pstmt1.setDate(2, date);
            pstmt1.setInt(3, amount);

            result = pstmt1.executeUpdate() > 0;

            ResultSet rs = pstmt1.getGeneratedKeys();
            try {
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            } finally {
                close(rs);
            }
            pstmt2 = con.prepareStatement(INSERT_INTO_CARTS_VALUES);
            for (CartProductBean cpb : list) {
                int i = 1;
                pstmt2.setInt(i++, orderId);
                pstmt2.setInt(i++, cpb.getId());
                pstmt2.setInt(i++, cpb.getQuantityForOrder());
                pstmt2.setInt(i, cpb.getPrice());
                pstmt2.executeUpdate();

            }

            updateQuantity(list, con);
            result = true;
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot make the order", ex);
        } finally {
            close(pstmt1);
            close(pstmt2);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Update quantity of products.
     *
     * @param list list of product beans.
     * @throws SQLException
     */
    private void updateQuantity(List<CartProductBean> list, Connection con)
            throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con
                    .prepareStatement(SQL_UPDATE_QUANTITY);

            for (CartProductBean cpb : list) {
                pstmt.setInt(1, cpb.getQuantityForOrder());
                pstmt.setInt(2, cpb.getId());
                pstmt.executeUpdate();
            }

        } finally {
            close(pstmt);
        }

    }

    /**
     * Remove product by id.
     *
     * @param productId
     * @return true if products removed
     */
    public boolean removeProductsById(int productId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            pstmt = con
                    .prepareStatement(DELETE_FROM_PRODUCTS_WHERE_PRODUCTS_ID);
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot make the order", ex);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Create a new user.
     *
     * @param name , login, password
     * @return true if user created
     */
    public boolean createUser(String name, String login, String password, String email) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            pstmt = con
                    .prepareStatement(INSERT_INTO_USERS);
            int k = 1;
            pstmt.setString(k++, name);
            pstmt.setString(k++, login);
            pstmt.setString(k++, password);
            pstmt.setString(k, email);

            if (!hasLoginInDB(con, login)) {
                pstmt.executeUpdate();
            } else {
                LOG.error("A user with this login already exists");
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot add the user", ex);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Change order's status.
     *
     * @param status , orderId
     * @return true if status changed
     */
    public boolean changeOrderStatus(String status, int orderId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            int statusIdByName = getStatusIdByName(con, status);
            pstmt = con
                    .prepareStatement(UPDATE_ORDERS_SET_ORDERS_STATUS);
            pstmt.setInt(1, statusIdByName);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error("Can't update the role", e);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;

    }

    /**
     * Change quantity for order
     *
     * @param quantityForOrder , productName
     * @return true if role changed
     */
    public boolean changeQuantityForOrders(int productId, int quantityForOrder) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            pstmt = con
                    .prepareStatement(UPDATE_CARTS_SET_QUANTITY);
            pstmt.setInt(1, quantityForOrder);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error("Can't update the role", e);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;

    }

    /**
     * Add the product.
     *
     * @param category, name, price, color, weight, dateManufacturer
     * @return true if product added
     */
    public boolean addProduct(int id, String category, String name, int price,
                              String color, int weight, Date dateManufacturer, int quantityOfStock) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            int categoryId = getCategoryIdByName(con, category);
            pstmt = con
                    .prepareStatement(INSERT_INTO_PRODUCTS);
            int k = 1;
            pstmt.setInt(k++, id);
            pstmt.setString(k++, name);
            pstmt.setInt(k++, price);
            pstmt.setString(k++, color);
            pstmt.setInt(k++, weight);
            pstmt.setDate(k++, dateManufacturer);
            pstmt.setInt(k++, quantityOfStock);
            pstmt.setInt(k, categoryId);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error("Can't add the product", e);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Update the product.
     *
     * @param category name  Category
     * @param name     name Product
     * @return true if product updated
     */
    public boolean updateProduct(int id, String category, String name,
                                 int price, String color, int weight, Date dateManufacturer, int quantityInStock) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            int categoryId = getCategoryIdByName(con, category);
            pstmt = con
                    .prepareStatement(SQL_UPDATE_PRODUCTS);
            int k = 1;
            pstmt.setString(k++, name);
            pstmt.setInt(k++, price);
            pstmt.setString(k++, color);
            pstmt.setInt(k++, weight);
            pstmt.setDate(k++, dateManufacturer);
            pstmt.setInt(k++, quantityInStock);
            pstmt.setInt(k++, categoryId);
            pstmt.setInt(k, id);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error("Can't update the product", e);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Get category id by category name.
     *
     * @param connection
     * @param categoryName
     * @return int category id.
     * @throws SQLException
     */
    private int getCategoryIdByName(Connection connection, String categoryName)
            throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement(SELECT_FROM_CATEGORIES_WHERE_NAME);
        pstmt.setString(1, categoryName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Category with that name does not exist");
        }
    }

    /**
     * Get status id by status name.
     *
     * @param connection
     * @param status
     * @return int status id.
     * @throws SQLException
     */
    private int getStatusIdByName(Connection connection, String status)
            throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement(SELECT_FROM_STATUSES_WHERE_NAME);
        pstmt.setString(1, status);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Status with that name does not exist");
        }

    }

    /**
     * Get role by name.
     *
     * @param connection
     * @param roleName
     * @return int role id.
     * @throws SQLException
     */
    private int getRoleIdByName(Connection connection, String roleName)
            throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement(SELECT_FROM_ROLES_WHERE_NAME);
        pstmt.setString(1, roleName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Status with that name does not exist");
        }
    }

    /**
     * Checks if there is a login in DB.
     *
     * @param login
     * @return true if login is in DB
     */
    public boolean hasLoginInDB(String login) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("A user with this login already exists", e);
        } finally {
            close(rs);
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Change user's role.
     *
     * @param roleName , userId
     * @return true if role changed
     */
    public boolean changeUserRole(String roleName, int userId) {
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            int roleId = getRoleIdByName(con, roleName);
            pstmt = con
                    .prepareStatement(UPDATE_USERS);
            pstmt.setInt(1, roleId);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error("Can't update the role", e);
        } finally {
            close(pstmt);
            commitAndClose(con);
        }
        return result;

    }

    /**
     * Checks if there is a login in DB.
     *
     * @param connection
     * @param login
     * @return true if login is in DB
     * @throws SQLException
     */
    private static boolean hasLoginInDB(Connection connection, String login)
            throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN);
        pstmt.setString(1, login);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    /**
     * Extracts a order bean from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return OrderBean object
     * @throws SQLException
     */
    /**
     * Checks if there is a product in DB.
     *
     * @param idProduct
     * @return true if product is in DB
     */
    public boolean hasProductInDB(int idProduct) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SELECT_FROM_PRODUCTS_WHERE_ID);
            pstmt.setInt(1, idProduct);
            rs = pstmt.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            LOG.error("A product with this id doesn't exists", e);
        } finally {
            close(rs);
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    /**
     * Checks if there is a product in orders.
     *
     * @param idProduct
     * @return true if product is in DB
     */
    public boolean hasProductInOrders(int idProduct) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            pstmt = con
                    .prepareStatement(SELECT_FROM_CARTS_WHERE_PRODUCT_ID);
            pstmt.setInt(1, idProduct);
            rs = pstmt.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            LOG.error("A product with this id doesn't exists", e);
        } finally {
            close(rs);
            close(pstmt);
            commitAndClose(con);
        }
        return result;
    }

    private int getCountOfOrders(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SELECT_COUNT_FROM_ORDERS_WHERE_USER_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain product beans", ex);
        } finally {
            close(rs);
            close(stmt);
            commitAndClose(con);
        }
        return count;
    }

    private OrderBean extractOrderBean(ResultSet rs) throws SQLException {
        OrderBean orderBean = new OrderBean();
        orderBean.setId(rs.getInt("id"));
        orderBean.setStatusOrder(rs.getString("name"));
        orderBean.setDate(rs.getDate("date"));
        orderBean.setAmount(rs.getInt("amount"));

        return orderBean;
    }


    /**
     * Extracts a user from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return User object
     * @throws SQLException
     */
    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("e_mail"));
        user.setLocaleName(rs.getString("local_name"));
        user.setRoleId(rs.getInt("role_id"));
        return user;
    }

    /**
     * Extracts a product from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return Product object
     * @throws SQLException
     */
    private Product extractProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getInt("price"));
        product.setColor(rs.getString("color"));
        product.setWeight(rs.getInt("weight"));
        product.setDateManufacturer(rs.getDate("dateManufacturer"));
        product.setQuantityInStock(rs.getInt("quantityInStock"));
        product.setCategoryId(rs.getInt("category_id"));
        return product;
    }

    /**
     * Extracts a product bean from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return ProductBean object
     * @throws SQLException
     */
    private ProductBean extractProductBean(ResultSet rs) throws SQLException {
        ProductBean productBean = new ProductBean();
        productBean.setId(rs.getInt("id"));
        productBean.setCategory(rs.getString(2));
        productBean.setName(rs.getString(3));
        productBean.setPrice(rs.getInt("price"));
        productBean.setColor(rs.getString("color"));
        productBean.setWeight(rs.getInt("weight"));
        productBean.setDateManufacturer(rs.getDate("dateManufacturer"));
        productBean.setQuantityInStock(rs.getInt("quantityInStock"));
        return productBean;
    }

    /**
     * Extracts a user order bean from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return UserOrderBean object
     * @throws SQLException
     */
    private UserOrderBean extractUserOrderBean(ResultSet rs)
            throws SQLException {

        UserOrderBean userOrderBean = new UserOrderBean();
        userOrderBean.setUserId(rs.getInt("user_id"));
        userOrderBean.setOrderId(rs.getInt("id"));
        userOrderBean.setUserLogin(rs.getString("login"));
        userOrderBean.setUserName(rs.getString("name"));
        userOrderBean.setStatusName(Status.getStatus(rs.getInt("status_id")));
        userOrderBean.setCountOfOrders(getCountOfOrders(rs.getInt("user_id")));
        userOrderBean.setDate(rs.getDate("date"));
        userOrderBean.setAmount(rs.getInt("amount"));

        return userOrderBean;
    }

    /**
     * Extracts a user bean from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return UserBean object
     * @throws SQLException
     */
    private static UserBean extractUserBean(ResultSet rs) throws SQLException {
        UserBean userBean = new UserBean();
        userBean.setUserId(rs.getInt("id"));
        userBean.setUserLogin(rs.getString("login"));
        userBean.setUserName(rs.getString("name"));
        userBean.setUserEmail(rs.getString("e_mail"));
        userBean.setRoleName(rs.getString(5));
        return userBean;
    }

    /**
     * Extracts a cart product bean from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return CartProductBean object
     * @throws SQLException
     */
    private CartProductBean extractCartProductBean(ResultSet rs)
            throws SQLException {
        int k = 1;
        CartProductBean cartProductBean = new CartProductBean();
        cartProductBean.setId(rs.getInt("id"));
        cartProductBean.setProductName(rs.getString("name"));
        cartProductBean.setPrice(rs.getInt("price"));
        cartProductBean.setQuantityInStock(rs.getInt("quantityInStock"));
        cartProductBean.setQuantityForOrder(k);
        return cartProductBean;
    }

    public static int extractAllAmount(User user) {
        List<OrderBean> orderBeans = DBManager.getInstance()
                .getAllOrderBeansByUser(user);
        int amount = 0;
        for (OrderBean orderBean : orderBeans) {
            amount += orderBean.getAmount();

        }
        return amount;
    }

    /**
     * Commits and close the given connection.
     *
     * @param con Connection to be committed and closed.
     */
    private void commitAndClose(Connection con) {
        if (con != null) {
            try {
                con.commit();
                con.close();
            } catch (SQLException ex) {
                LOG.error("Cannot commit transaction and close connection", ex);
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close a result set", ex);
            }
        }
    }

    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close a statement", ex);
            }
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con Connection to be rollbacked and closed.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

}
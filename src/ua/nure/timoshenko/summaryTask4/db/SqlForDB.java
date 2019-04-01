package ua.nure.timoshenko.summaryTask4.db;


public class SqlForDB {

    public static final String SQL_GET_ALL_PRODUCT_BEAN = "SELECT p.id, c.name, p.name, p.price, p.color, p.weight," +
            " p.dateManufacturer, p.quantityInStock "
            + "FROM products p, categories c "
            + "WHERE p.category_id=c.id";
    public static final String SQL_GET_ALL_PRODUCT_BEAN_BY_USER = "SELECT o.id, o.date, o.amount, s.name "
            + "FROM statuses s, orders o "
            + "WHERE o.status_id=s.id and o.user_id=? ";
    public static final String SQL_GET_ALL_USER_BEAN = "SELECT u.id, u.login, u.name, u.e_mail, r.name "
            + "FROM users u, roles r " + "WHERE u.role_id=r.id";
    public static final String SQL_GET_ALL_USER_ORDER_BEAN = "SELECT * "
            + "FROM orders o, users u, statuses s "
            + "WHERE o.user_id=u.id and o.status_id=s.id";
    public static final String SQL_GET_CART_BEAN_BY_PRODUCT_ID = "SELECT p.id, p.name, p.price, p.quantityInStock "
            + "FROM products p "
            + "WHERE p.id=?";
    public static final String SELECT_ID_FROM_PRODUCTS_WHERE_NAME = "SELECT * FROM products WHERE name = ?";
    public static final String SQL_UPDATE_USERS = "UPDATE users SET password=?, name=?, locale_name=? "
            + "WHERE id=?";
    public static final String INSERT_INTO_ORDERS_VALUES = "INSERT INTO orders VALUES (DEFAULT, ?, 0, ?, ?)";
    public static final String INSERT_INTO_CARTS_VALUES = "INSERT INTO carts VALUES (?, ?, ?, ?)";
    public static final String SQL_UPDATE_QUANTITY = "UPDATE products " +
            "SET products.quantityInStock=products.quantityInStock-? " +
            "WHERE products.id=?";
    public static final String DELETE_FROM_PRODUCTS_WHERE_PRODUCTS_ID = "DELETE FROM products WHERE products.id=?";
    public static final String INSERT_INTO_USERS = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, NULL, 1)";
    public static final String SELECT_FROM_USERS_WHERE_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String UPDATE_USERS = "UPDATE users SET users.role_id=? WHERE users.id=?";
    public static final String UPDATE_ORDERS_SET_ORDERS_STATUS = "UPDATE orders SET orders.status_id=? WHERE orders.id=?";
    public static final String UPDATE_CARTS_SET_QUANTITY = "UPDATE carts SET quantity=? WHERE products_Id=?";
    public static final String INSERT_INTO_PRODUCTS = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_PRODUCTS = "UPDATE products " +
            "SET name=?, price=?, color=?, weight=?," +
            " dateManufacturer=?, quantityInStock=?, category_id=? " +
            "WHERE id=?";
    public static final String SELECT_FROM_CATEGORIES_WHERE_NAME = "SELECT * FROM categories WHERE name = ?";
    public static final String SELECT_FROM_STATUSES_WHERE_NAME = "SELECT * FROM statuses WHERE name = ?";
    public static final String SELECT_FROM_ROLES_WHERE_NAME = "SELECT * FROM roles WHERE name = ?";
    public static final String SELECT_FROM_PRODUCTS_WHERE_ID = "SELECT * FROM products WHERE id = ?";
    public static final String SELECT_FROM_CARTS_WHERE_PRODUCT_ID = "SELECT * FROM carts WHERE product_id = ?";
    public static final String SELECT_COUNT_FROM_ORDERS_WHERE_USER_ID = "SELECT count(*) FROM orders WHERE user_id=?";
}

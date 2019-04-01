package ua.nure.timoshenko.summaryTask4.db.entity;

/**
 * Cart entity.
 *
 * @author L.Timoshenko
 */
public class Cart extends Entity {

    private static final long serialVersionUID = 7070089120814402160L;

    private int orderId;

    private int productId;

    private int quantity;

    private int price;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

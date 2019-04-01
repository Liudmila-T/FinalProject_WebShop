package ua.nure.timoshenko.summaryTask4.db.bean;

import ua.nure.timoshenko.summaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 *
 * <pre>
 * |product.name|product.price|product.name|
 * </pre>
 *
 * @author L.Timoshenko
 */
public class CartProductBean extends Entity {

    private static final long serialVersionUID = 856013419141560445L;

    private String productName;

    private int price;

    private int quantityInStock;

    private int quantityForOrder;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getQuantityForOrder() { return quantityForOrder;}

    public void setQuantityForOrder(int quantityForOrder) {this.quantityForOrder = quantityForOrder;}

    @Override
    public String toString() {
        return "CartProductBean{" + "productName=" + productName + ", price=" + price +", quantityInStock=" + quantityInStock + ", quantityForOrder=" + quantityForOrder + '}';
    }
}

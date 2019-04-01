package ua.nure.timoshenko.summaryTask4.db.bean;

import ua.nure.timoshenko.summaryTask4.db.entity.Entity;

import java.sql.Date;

/**
 * Provide records for virtual table:
 *
 * <pre>
 * |category.name|product.name|product.price|product.color|product.weight|product.dateManufacturer|product.quantityInStock|
 * </pre>
 *
 * @author L.Timoshenko
 */
public class ProductBean extends Entity {

    private static final long serialVersionUID = -1876686566433048447L;

    private String category;

    private String name;

    private int price;

    private String color;

    private int weight;

    private Date dateManufacturer;

    private int quantityInStock;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDateManufacturer(Date dateManufacturer) {
        this.dateManufacturer = dateManufacturer;
    }

    public Date getDateManufacturer() {
        return dateManufacturer;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", dateManufacturer=" + dateManufacturer +
                ", quantityInStock=" + quantityInStock +
                            '}';
    }
}

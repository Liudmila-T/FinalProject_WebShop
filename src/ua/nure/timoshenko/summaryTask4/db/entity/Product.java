package ua.nure.timoshenko.summaryTask4.db.entity;

import java.sql.Date;

/**
 * Product entity.
 *
 * @author L.Timoshenko
 *
 */
public class Product extends Entity {

	private static final long serialVersionUID = 4959794966827904335L;

	private String name;

	private int price;

	private String color;

	private int weight;

	private Date dateManufacturer;

	private int quantityInStock;

	private int categoryId;

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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
		return "Product{" +
				"name='" + name + '\'' +
				", price=" + price +
				", color='" + color + '\'' +
				", weight=" + weight +
				", dateManufacturer=" + dateManufacturer +
				", quantityInStock=" + quantityInStock +
				", categoryId=" + categoryId +
				'}';
	}
}

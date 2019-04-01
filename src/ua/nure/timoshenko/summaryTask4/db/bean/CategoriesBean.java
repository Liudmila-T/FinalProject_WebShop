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
 *
 */
public class CategoriesBean extends Entity {

	private static final long serialVersionUID = 856013419141560445L;

	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "CartProductBean [productName=" + name;
	}

}
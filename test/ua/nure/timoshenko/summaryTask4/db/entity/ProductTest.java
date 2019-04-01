package ua.nure.timoshenko.summaryTask4.db.entity;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private Product pr;

	@Before
	public void setUp()  {
		pr = new Product();
		pr.setCategoryId(2);
		pr.setColor("red");
		pr.setId(1);
		pr.setName("name");
		pr.setDateManufacturer(new Date(123));
		pr.setPrice(10);
		pr.setQuantityInStock(5);
		pr.setWeight(100);
	}

	@After
	public void tearDown() {
		pr = null;
	}

	@Test
	public void testGetName() {
		assertEquals("name",pr.getName());
	}

	@Test
	public void testGetPrice() {
		assertEquals(10, pr.getPrice());
	}

	@Test
	public void testGetColor() {
		assertEquals(pr.getColor(), "red");
	}

	@Test
	public void testGetWeight() {
		assertEquals(pr.getWeight(), 100);
	}

	@Test
	public void testGetCategoryId() {
		assertEquals(2, pr.getCategoryId());
	}

	@Test
	public void testGetNovelty() {
		assertEquals(pr.getDateManufacturer(), new Date(123));
	}

	@Test
	public void testGetQuantity() {
		assertEquals(5, pr.getQuantityInStock());
	}

	@Test
	public void testToString() {
		String product = "Product{name='name', price=10, color='red', weight=100, dateManufacturer=1970-01-01, quantityInStock=5, categoryId=2}";
		assertEquals(pr.toString(), product);
	}

	@Test
	public void testGetId() {
		assertEquals(1, pr.getId());
	}

}

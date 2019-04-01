package ua.nure.timoshenko.summaryTask4.db.bean;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductBeanTest {

	ProductBean pb;

	@Before
	public void setUp() {
		pb = new ProductBean();
		pb.setCategory("A");
		pb.setColor("blue");
		pb.setId(1);
		pb.setName("Aaa");
		pb.setDateManufacturer(new Date(123));
		pb.setPrice(10);
		pb.setQuantityInStock(2);
		pb.setWeight(300);
	}

	@After
	public void tearDown() throws Exception {
		pb = null;
	}

	@Test
	public void testGetCategory() {
		assertEquals("A",pb.getCategory());
	}

	@Test
	public void testGetName() {
		assertEquals("Aaa", pb.getName());
	}

	@Test
	public void testGetPrice() {
		assertEquals(10,pb.getPrice());
	}

	@Test
	public void testGetColor() {
		assertEquals("blue",pb.getColor());
	}

	@Test
	public void testGetWeight() {
		assertEquals(300, pb.getWeight());
	}

	@Test
	public void testGetDateManufacturer() {
		assertEquals(new Date(123), pb.getDateManufacturer());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(2, pb.getQuantityInStock());
	}

	@Test
	public void testToString() {
		String productBean ="ProductBean{" +
				"category='A', name='Aaa', price=10, color='blue', weight=300, dateManufacturer=1970-01-01, quantityInStock=2}";
		assertEquals(pb.toString(), productBean);
	}

	@Test
	public void testGetId() {
		assertTrue(pb.getId() == 1);
	}

}

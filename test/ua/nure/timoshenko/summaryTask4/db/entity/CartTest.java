package ua.nure.timoshenko.summaryTask4.db.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

	Cart cart;

	@Before
	public void setUp() {
		cart = new Cart();
		cart.setId(1);
		cart.setOrderId(2);
		cart.setPrice(10);
		cart.setProductId(3);
		cart.setQuantity(5);
	}

	@After
	public void tearDown() {
		cart = null;
	}

	@Test
	public void testGetProductId() {
		assertEquals(3, cart.getProductId());
	}

	@Test
	public void testGetPrice() {
		assertEquals(10, cart.getPrice());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(5, cart.getQuantity());
	}

	@Test
	public void testGetOrderId() {
		assertEquals(2, cart.getOrderId());
	}

	@Test
	public void testToString() {
		String c = "Cart{orderId=2, productId=3, quantity=5, price=10}";
		assertEquals(cart.toString(), c);
	}

	@Test
	public void testGetId() {
		assertTrue(cart.getId() == 1);
	}

}

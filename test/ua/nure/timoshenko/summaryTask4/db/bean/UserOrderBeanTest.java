package ua.nure.timoshenko.summaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserOrderBeanTest {

	UserOrderBean uob;

	@Before
	public void setUp()  {
		uob = new UserOrderBean();
		uob.setId(1);
		uob.setOrderId(2);
		uob.setStatusName("paid");
		uob.setUserLogin("login");
		uob.setUserName("name");
		uob.setDate(new Date(123));
	}

	@After
	public void tearDown() throws Exception {
		uob = null;
	}

	@Test
	public void testGetOrderId() {
		assertTrue(uob.getOrderId() == 2);
	}

	@Test
	public void testGetUserLogin() {
		assertEquals(uob.getUserLogin(), "login");
	}

	@Test
	public void testGetUserName() {
		assertEquals(uob.getUserName(), "name");
	}

	@Test
	public void testGetStatusName() {
		assertEquals(uob.getStatusName(), "paid");
	}


	@Test
	public void testGetDate() {assertEquals(uob.getDate(),new java.sql.Date(123));	}

	@Test
	public void testToString() {
		String userOrderBean = "UserOrderBean{orderId=2, userLogin='login', userName='name', statusName='paid', countOfOrders=0, userId=0, date=Thu Jan 01 02:00:00 EET 1970, amount=0}";
		assertEquals(uob.toString(), userOrderBean);
	}

	@Test
	public void testGetId() {
		assertTrue(uob.getId() == 1);
	}

}

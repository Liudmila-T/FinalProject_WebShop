package ua.nure.timoshenko.summaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class OrderBeanTest {

	OrderBean ob;


	@Before
	public void setUp()  {
		ob = new OrderBean();
		ob.setId(1);
		ob.setStatusOrder("paid");
		ob.setDate(new Date(123));
		ob.setAmount(600);
	}

	@After
	public void tearDown(){
		ob = null;
	}

	@Test
	public void testGetStatusOrder() {
		assertEquals(ob.getStatusOrder(), "paid");
	}

	@Test
	public void testGetId() {assertEquals(ob.getId(),1);	}
	@Test
	public void testGetDate() {assertEquals(ob.getDate(),new Date(123));	}
	@Test
	public void testGetAmount() {assertEquals(ob.getAmount(),600);	}
	@Test
	public void testToString() {
		String orderBean = "OrderBean{statusOrder='paid', date=Thu Jan 01 02:00:00 EET 1970, amount=600}";
		assertEquals(ob.toString(), orderBean);
	}

}

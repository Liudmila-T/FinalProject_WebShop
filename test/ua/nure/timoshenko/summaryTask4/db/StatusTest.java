package ua.nure.timoshenko.summaryTask4.db;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.timoshenko.summaryTask4.db.entity.Order;

public class StatusTest {

	@Test
	public void testGetStatus() {
		Order order = new Order();
		order.setStatusID(1);
		assertEquals(Status.PAID, Status.getStatus(order));
	}

	@Test
	public void testGetName() {
		String status = "paid";
		assertEquals(status, Status.values()[1].getName());
	}

	@Test
	public void coverEnum() {
		Status.valueOf(Status.values()[0].name());
	}

}

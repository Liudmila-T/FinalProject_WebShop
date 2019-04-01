package ua.nure.timoshenko.summaryTask4.db.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category cat;

	@Before
	public void setUp()  {
		cat = new Category();
		cat.setId(1);
		cat.setName("Dev");
	}

	@After
	public void tearDown()  {
		cat = null;
	}

	@Test
	public void testGetName() {
		assertEquals("Dev",cat.getName());
	}

	@Test
	public void testToString() {
		String c = "Category{name='Dev'}";
		assertEquals(cat.toString(), c);
	}

	@Test
	public void testGetId() {
		assertEquals(1, cat.getId());
	}

}

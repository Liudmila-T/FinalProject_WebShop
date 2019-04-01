package ua.nure.timoshenko.summaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartProductBeanTest {

    CartProductBean cpb;

    @Before
    public void setUp()  {
        cpb = new CartProductBean();
        cpb.setId(1);
        cpb.setPrice(10);
        cpb.setProductName("Kitchen super");
        cpb.setQuantityInStock(50);
        cpb.setQuantityForOrder(1);
    }

    @After
    public void tearDown()  { cpb = null;    }


    @Test
    public void testGetId() {
        assertEquals( cpb.getId(),1);
    }
    @Test
    public void testGetProductName() {
        assertEquals(cpb.getProductName(), "Kitchen super");
    }

    @Test
    public void testGetPrice() {
        assertEquals(cpb.getPrice(), 10);
    }

    @Test
    public void testGetQuantity() {
        assertEquals(cpb.getQuantityInStock(), 50);
    }

    @Test
    public void testGetQuantityForOrder() {assertEquals(cpb.getQuantityForOrder(),1); }

    @Test
    public void testToString() {
        String cartProductBean = "CartProductBean{productName=Kitchen super, price=10, quantityInStock=50, quantityForOrder=1}";
        assertEquals(cpb.toString(), cartProductBean);
    }



}

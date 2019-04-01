package ua.nure.timoshenko.summaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ua.nure.timoshenko.summaryTask4.db.RoleTest.class,
		ua.nure.timoshenko.summaryTask4.db.StatusTest.class,
		ua.nure.timoshenko.summaryTask4.db.bean.CartProductBeanTest.class,
		ua.nure.timoshenko.summaryTask4.db.bean.OrderBeanTest.class,
		ua.nure.timoshenko.summaryTask4.db.bean.ProductBeanTest.class,
		ua.nure.timoshenko.summaryTask4.db.bean.UserBeanTest.class,
		ua.nure.timoshenko.summaryTask4.db.bean.UserOrderBeanTest.class,
		ua.nure.timoshenko.summaryTask4.db.entity.CartTest.class,
		ua.nure.timoshenko.summaryTask4.db.entity.CategoryTest.class,
		ua.nure.timoshenko.summaryTask4.db.entity.OrderTest.class,
		ua.nure.timoshenko.summaryTask4.db.entity.ProductTest.class,
		ua.nure.timoshenko.summaryTask4.db.entity.UserTest.class})
public class AllTests {

}

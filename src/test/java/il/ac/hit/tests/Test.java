package il.ac.hit.tests;

import java.util.Calendar;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import il.ac.hit.model.*;
import static org.junit.Assert.*;

public class Test {
	private static Logger log = LogManager.getRootLogger();

	@org.junit.Test
	public void testCouponCreationValidation()
	{
		log.info("TEST1 | Testing coupon creation");
		CouponTable couponTable = CouponTable.getInstance();
		couponTable.addCoupon(new Coupon(100, "Test", "image", "details", "category", Calendar.getInstance().getTime(), 32.048598, 34.809966));
		Coupon coupon = couponTable.getCoupon(100);
		assertNotNull("A test coupon was created", coupon);
	}
	
	
	@org.junit.Test
	public void testUserCreationValidation()
	{
		log.info("TEST1 | Testing User creation");
		UserTable userTable = UserTable.getInstance();
		userTable.addUser("Test", "test", "Test");
		
		User user = null;
		
		try {
			
			user = userTable.getUser("Test");
		} catch (CouponException e) {
			e.printStackTrace();
		}
		
		assertNotNull("A test user was created", user);
	}
}

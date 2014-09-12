package il.ac.hit.model;

import il.ac.hit.util.Location;

import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Class that generates singleton object of coupons table.
 * @author  agcoupons
 *
 */
public class CouponTable implements ICouponsDAO {

	//Singleton
	private static CouponTable myCouponTable;
	private List couponsList;
	private Set<String> categorySet;
	private static Logger log = LogManager.getRootLogger();
	
	/**
	 * A method that gives the instance of CouponTable's singleton.
	 * @return the singleton object of CounponTable.
	 */
	public static CouponTable getInstance()
	{
		if (myCouponTable == null)
		{
			myCouponTable = new CouponTable();
		}
		
		return myCouponTable;
	}
	
	private SessionFactory factory;

	/**
	 * CouponTable default constructor.
	 */
	public CouponTable()
	{
		//creating factory for getting sessions
		factory = new AnnotationConfiguration().configure().buildSessionFactory();

		categorySet = new TreeSet<String>();
	}
	
	@Override
	public Coupon getCoupon(int id)
	{
		Session session = factory.openSession();
		Coupon myCoupon = null;
		
		try
		{
			myCoupon = (Coupon)session.get(Coupon.class, id);
		}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		
		return myCoupon; 
	}
		
	@Override
	public boolean updateCoupon(Coupon coupon)
	{
	
		Session session = factory.openSession();
		
		try {
			
			//creating a new session for updating products
			session.beginTransaction();
			session.update(coupon);
			session.getTransaction().commit();
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
	
		return true;
	}
	
	@Override
	public Iterator getCoupons()

	{
		log.info("CouponTable | getCoupons");
		
		Session session = factory.openSession();
		
		if (couponsList != null)
		{
			couponsList.clear();
		}
		
		couponsList = session.createQuery("from Coupon").list();
		
		return couponsList.iterator();
		
	}
	
	/**
	 * A method for getting coupons by their category
	 * @param category A string value of the coupons category
	 * @return An iterator that iterates over a list of all
	 * coupons that belong to the category that was passed
	 * to the method
	 */
	public Iterator getCouponsByCategory(String category)
	{
		Session session = factory.openSession();
		
		if (couponsList != null)
		{
			couponsList.clear();
		}
		
		couponsList = session.createQuery("from Coupon where category=\'" + category +"\'").list();
	
		return couponsList.iterator();
	}
	
	/**
	 * A method for getting coupons by their business creator
	 * @param business A string value of the coupon's creator
	 * @return An iterator that iterates over a list of all
	 * coupons that was created by the business that was passed
	 * to the method
	 */
	public Iterator getCouponsByBusiness(String business)
	{
		Session session = factory.openSession();
		
		if (couponsList != null)
		{
			couponsList.clear();
		}
		
		couponsList = session.createQuery("from Coupon where business=\'" + business +"\'").list();
	
		return couponsList.iterator();
	}
	
	/**
	 * A method for getting coupons by the location of the
	 * web browser entering the site
	 * @param latitude A double that represents the geolocation latitude value of
	 * the user's location
	 * @param longitude A double that represents the geolocation longitude value of
	 * the user's location
	 * @return An iterator that iterates over a list of all
	 * coupons that was created near the location of the user
	 */
	public Iterator getCouponsByLocation(double latitude, double longitude)
	{
		
		//clear the list to avoid duplicates
		if (couponsList != null)
		{
			couponsList.clear();
		}
		
		Session session = factory.openSession();
		
		List tempCouponsList = session.createQuery("from Coupon").list();
		
		Coupon curCoupon = null;
		double range = 2.0; //Proximity to the user's location of 2 KM
		Iterator iter = tempCouponsList.iterator();
		
		while (iter.hasNext())
		{
			curCoupon = (Coupon)iter.next();
			
			if (Location.isCoordinateInRange(curCoupon.getLatitude(), curCoupon.getLongitude(), latitude, longitude, range))
			{
				couponsList.add(curCoupon);
			}
		}
		
		return couponsList.iterator();
	}
	
	@Override
	public boolean deleteCoupon(int id) {
		Session session = factory.openSession();
		
		try {
			session.beginTransaction();
	        Coupon myCoupon = 
	                  (Coupon)session.get(Coupon.class, id);
	        session.delete(myCoupon);
	        session.getTransaction().commit();
	        log.info("CouponTable | Coupon deleted!");
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		return true;
	}
	
	@Override
	public boolean addCoupon(Coupon ob)
	{
		
		Session session = factory.openSession();
		
		try {
		
			if (getCoupon(ob.getId()) == null)
			{
					session.beginTransaction();
					session.save(ob);
					session.getTransaction().commit();
					  log.info("CouponTable | Coupon added");
			}
			else
			{
				return false;
			}
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		finally 
		{
			session.close();
			  log.info("CouponTable | session closed.");
		}
		
		
		return true;
	}
	/**
	 * A method that removes the expired coupons.
	 */
	public void removeExpiredCoupons()
	{
		
		Iterator iter = getCoupons();

		Coupon curCoupon = null;
		
		while (iter.hasNext())
		{
			curCoupon = (Coupon)iter.next();

			if (isExpired(curCoupon.getDate()))
			{
				deleteCoupon(curCoupon.getId());
				  log.info("CouponTable | Coupon expired & deleted!");
			}
		}
	}
	
	/**
	 * A method that checks if coupon is exists.
	 * @param id An int value that represent the id of the coupon (primary key)
	 * @return A boolean value of the check's result.
	 */
	public boolean isExist(int id)
	{
		if (getCoupon(id) == null)
		{
			return false;
		}
		  log.info("CouponTable | Coupon exist");
		return true;
	}
	
	/**
	 * A method that checks if coupon is expired.
	 * @param date A Date value that represent the date of the expiry date.
	 * @return A boolean value of the check's result. 
	 */
	private boolean isExpired(Date date)
	{
		
		Calendar expiry = Calendar.getInstance();
		expiry.setTime(date);
		log.info("CouponTable | Checking if Coupon expired");
		return expiry.before(Calendar.getInstance());
	}
	
	/**
    * A method for getting an iterator object to iterate over the list of all
    * categories of the coupons in the database
    * @return An iterator over the list of categories in the database
    */
	public Iterator<String> getCategories()
	{
		Iterator iter = getCoupons();
		
		while (iter.hasNext())
		{
			categorySet.add(((Coupon)iter.next()).getCategory());
		}
		
		return categorySet.iterator();
	}
}

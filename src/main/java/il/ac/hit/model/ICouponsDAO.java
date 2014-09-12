package il.ac.hit.model;

import java.util.Iterator;

public interface ICouponsDAO
{
	/**
	 * A method to get an instance of a coupon object by its id from the database
	 * using Hibernate
	 * @param id An int representing the unique id number of the coupon to get
	 * @return a coupon object with the same id that was passed to the function
	 * @throws CouponException if the coupon was not found
	 */
   public abstract Coupon getCoupon(int id) throws CouponException;
   
   /**
    * A method that updates a specific coupon in the database using Hibernate
    * @param coupon a coupon object containing the updated data
    * @return A boolean value saying if the update was successfull
    */
   public abstract boolean updateCoupon(Coupon coupon);
   
   /**
    * A method for getting an iterator object to iterate over the list of all
    * coupons in the database
    * @return An iterator over the list of coupons in the database
    */
   public abstract Iterator<Coupon> getCoupons();
   
   /**
    * A method that deletes a certain coupon by its id from the databse
    * using Hibernate
    * @param id An int representing the unique id number of the coupon to delete
    * @return A boolean value representing whether the deletion was successful
    */
   public abstract boolean deleteCoupon(int id);
   
   /**
    * A method for adding a coupon to the database using Hibernate
    * @param coupon A coupon object to be added to the database
    * @return A boolean value representing whether the addition was successful
    */
   public abstract boolean addCoupon(Coupon coupon);
  
}

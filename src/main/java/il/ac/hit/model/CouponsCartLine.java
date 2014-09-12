package il.ac.hit.model;

import javax.persistence.Id;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class that generate a line at the coupon's cart.
 * @author agcoupons
 *
 */
public class CouponsCartLine {

	private Coupon coupon;
	private int quantity;
	private static Logger log = LogManager.getRootLogger();

	/**
	 * 
	 * @param coupon A coupon object
	 * @param quantity An int value that represents the amount of instances of a specific coupon.
	 */
	public CouponsCartLine(Coupon coupon, int quantity) {
		super();
		setCoupon(coupon);
		setQuantity(quantity);
		log.info("InventoryController | new line created!");
	}
	
	
	/**
	 * A method for comparing between coupons
	 * @return A boolean value that represent the result of the compared coupons.
	 */
	@Override
	public boolean equals(Object ob)
	{
		return this.coupon.equals(((CouponsCartLine)ob).getCoupon());
	}

	
	/**
	 * A getter for the coupon object.
	 * @return An Coupon object that represents the coupon.
	 */
	public Coupon getCoupon() {
		return coupon;
	}

	/**
	 * A setter for the coupon object.
	 * @param coupon an Coupon object representing the coupon object
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}


	/**
	 * A getter for the quantity value.
	 * @return An int value of specific coupon's amount.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * A setter for the quantity value of the coupon.
	 * @param quantity an int representing the quantity of specific coupon.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CouponsCartLine [coupon=" + coupon + ", quantity=" + quantity
				+ "]";
	}
	
	

}

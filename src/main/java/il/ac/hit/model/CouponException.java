package il.ac.hit.model;

/**
 * A class that describes exceptions in the coupons project
 * @author AGCoupons
 *
 */
public class CouponException extends Exception {

	/**
	 * Default constructor
	 */
	public CouponException(){}
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with parameters
	 * @param msg A String value representing the message the exception threw
	 */
	public CouponException(String msg)
	{
		super(msg);
	}

}

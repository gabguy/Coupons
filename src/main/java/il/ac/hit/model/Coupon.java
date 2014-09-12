package il.ac.hit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *	A class that defines a single coupon
 */
@Entity
@Table(name="coupons")
public class Coupon {
	
	/**
	 * An int value that represents the id of the Coupon, also the primary key of the object
	 */
	private int id;
	/**
	 * A string that represents the business that issued the coupon
	 */
	private String business;
	/**
	 * A string that represents the url of the image on the ftp storage server
	 */
	private String image;
	/**
	 * A string the defines additional details about the coupon
	 */
	private String details;
	/**
	 * A string that represents the category of products the coupon is related to
	 */
	private String category;
	/**
	 * A Date object that represents the timestamp of the expiry date of the coupon (date + time)
	 */
	private Date date;
	
	/**
	 * A double value that represents the latitude coordinate of the coupon
	 */
	private double latitude;
	
	/**
	 * A double value that represents the longitude coordinate of the coupon
	 */
	private double longitude;
	
	private static Logger log = LogManager.getRootLogger();

	/**
	 * Class constructor
	 */
	public Coupon() {
		super();
	}

	/**
	 * Class constructor with parameters
	 * @param id An int value that represents the id of the Coupon, also the primary key of the object
	 * @param business A string that represents the business that issued the coupon
	 * @param image A string that represents the url of the image on the ftp storage server
	 * @param details A string the defines additional details about the coupon
	 * @param category A string that represents the category of products the coupon is related to
	 * @param date A Date object that represents the timestamp of the expiry date of the coupon (date + time)
	 * @param latitude A double value that represents the latitude coordinate of the coupon
	 * @param longitude	A double value that represents the longitude coordinate of the coupon
	 */
	public Coupon(int id, String business, String image, String details,
			String category, Date date, double latitude, double longitude)
	{
		super();
		setId(id);
		setBusiness(business);
		setImage(image);
		setDetails(details);
		setCategory(category);
		setDate(date);
		setLatitude(latitude);
		setLongitude(longitude);
		log.info("Coupon | Coupon object created.");
	}
	
	/**
	 * A getter for the id value of the coupon
	 * @return An int value that represents the id of the Coupon, also the primary key of the object
	 */
	@Id
	public int getId() {
		return id;
	}

	/**
	 * A setter for the id value of the coupon
	 * @param id an int representing the unique id key of the coupon
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * A getter for the business name associated with the coupon
	 * @return A string that represents the business that issued the coupon
	 */
	public String getBusiness() {
		return business;
	}

	/**
	 * A setter for the business name associated with the coupon
	 * @param business A string that represents the business that issued the coupon
	 */
	public void setBusiness(String business) {
		this.business = business;
	}

	/**
	 * A getter for the image url on herobo ftp server of the image of the coupon
	 * @return A string representing the url on herobo ftp server of the image of the coupon
	 */
	public String getImage() {
		return image;
	}

	/**
	 * A setter for the image url on herobo ftp server of the image of the coupon
	 * @param image A string representing the url on herobo ftp server of the image of the coupon
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * A getter for the details of the coupon
	 * @return A string representing the details of the coupon
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * A setter for the details of the coupon
	 * @param details A string representing the details of the coupon
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * A getter for the category the coupon belongs to
	 * @return A string representing the category the coupon belongs to
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * A setter for the category the coupon belongs to
	 * @param category A string representing the category the coupon belongs to
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * A getter for the expiry date of the coupon
	 * @return A Date object representing the expiry date of the coupon
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * A setter for the expiry date of the coupon
	 * @param date A Date object representing the expiry date of the coupon
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * A getter for the latitude geolocation value of the coupon
	 * @return A double value representing the latitude geolocation value of the coupon
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * A setter for the latitude geolocation value of the coupon
	 * @param latitude A double value representing the latitude geolocation value of the coupon
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * A getter for the longitude geolocation value of the coupon
	 * @return A double value representing the longitude geolocation value of the coupon
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * A setter for the longitude Geo-location value of the coupon
	 * @param longitude A double value representing the longitude Geo-location value of the coupon
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object ob) {

		return (this.getId() == ((Coupon)ob).getId());
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", business=" + business + ", image="
				+ image + ", details=" + details + ", category=" + category
				+ ", date=" + date + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}

package il.ac.hit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *	A class that defines a single User
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * A string value that represents the username of the User
	 */
	private String username;
	/**
	 * A string value that represents the password (before md5 encryption) of the User
	 */
	private String password;
	/**
	 * A boolean value that represents if the User is admin or not
	 */
	private boolean admin;
	/**
	 * A string value that represents the business name
	 */
	private String businessName;
	
	private static Logger log = LogManager.getRootLogger();

	/** 
	 * Class constructor.
	 */
	public User(){}
	
	/**
	 * Class constructor with parameters.
	 * @param username A string that represents the user's username
	 * @param password A string that represents the user's encrypted password
	 * @param admin A boolean value that specifies whether the user is an admin or not
	 * @param businessName A string that represents the business name that is associated with this user
	 */
	public User(String username, String password, boolean admin, String businessName) {
		super();
		setUsername(username);
		setPassword(password);
		setAdmin(admin);
		setBusinessName(businessName);
		log.info("CouponTable | User object created!");
	}

	/**
	 * 
	 * @return A string that represents the username of the user object
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * A getter 
	 * @return A string that represents the encrypted password of the user object
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * A setter for the password of the user.
	 * @param password A string that represents the encrypted password of the user object
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * A method that checks if user is admin.
	 * @return A boolean value that specifies whether the user is an admin or not
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * A setter for admin parameter.
	 * @param admin A boolean value that specifies whether user is an admin or not.
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * A getter for businessName parameter.
	 * @return A string that represents the business name associated with this user
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * A setter for businessName parameter.
	 * @param businessName A string value that represent the business name.
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", admin=" + admin + "]";
	}
	
	
}

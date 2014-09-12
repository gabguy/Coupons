package il.ac.hit.model;

public interface IUsers {
	
	/**
	 * A method that adds user into the Mongodb database.
	 * @param username - A string value that represent the username of the user.
	 * @param password -  A string value that represent the password of the user.
	 * @param businessName - A string value that represent the business name.
	 * @return A Boolean value stating whether the user was added successfully
	 */
	public abstract boolean addUser(String username, String password, String businessName);
	
	/**
	 * A method to get a User object by username [primary key].
	 * @param username -  A string value that represent the username.
	 * @return An User object.
	 */
	public abstract User getUser(String username) throws CouponException;
	
	/**
	 * A method to remove a User object by username [primary key].
	 * @param username  - A string value that represent the username.
	 * @return A boolean value that represent the result.
	 */
	public abstract boolean removeUser(String username);
	
	/**
	 * A method to update a User object's fields.
	 * @param username - A string value that represent the username of the user.
	 * @param password -  A string value that represent the password of the user.
	 * @param businessName - A string value that represent the business name.
	 * @return A boolean value that represent the result.
	 */
	public abstract boolean updateUser(String username, String password, String businessName);

}

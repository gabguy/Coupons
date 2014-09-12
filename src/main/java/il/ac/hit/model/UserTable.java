package il.ac.hit.model;

import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * A class 
 * @author AGCoupons
 *
 */
public class UserTable implements IUsers{
	
	//Singleton
	private static UserTable myUserTable;
	private MongoClient mongoClient = null;
	private DB db = null;
	private DBCollection collection = null;
	private static Logger log = LogManager.getRootLogger();

	/**
	 * A getter for userTable instance.
	 * @return an UserTable singleTon object.
	 */
	public static UserTable getInstance() {
		
		if (myUserTable == null)
		{
			myUserTable = new UserTable();
		}
		
		return myUserTable;
	}

	/**
	 * default constructor
	 */
	public UserTable()
	{
		
		try {
			mongoClient = new MongoClient("kahana.mongohq.com" , 10057);
			
			db = mongoClient.getDB("XiL7YW3kuhlM8m25augrDg");
			db.authenticate("cloudbees", "k0mtQUvPhpSTgzMctVa85JOqoULocvoJtPEVSpA0lUKzz5AYsnnI_GSZjSIivOiYKWRRz0KaN53WMZSdmcjkug".toCharArray());
			collection = db.getCollection("users");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean addUser(String username, String password, String businessName)
	{
		//checks if user already exists
		BasicDBObject query = new BasicDBObject("username", username);
		
		DBCursor cursor = collection.find(query);

		try
		{
		   
		   if (!cursor.hasNext())
		   {
			   System.out.println("Creates new user");
			   
				//password encryption
				String passwordMD5 = null;
		        
				passwordMD5 = parseMD5(password);
					
				//creating a new document (AKA a new business)
				BasicDBObject myUser = new BasicDBObject("username", username)
				.append("password", passwordMD5)
				.append("admin", "false")
				.append("businessName", businessName);
				
				log.info("UserTable | User = " + myUser);
				
				//adding the new document to the collection
				collection.insert(myUser);
				
				return true;
		   	}
		   else
		   {
			   return false; //user already exists
		   }
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public User getUser(String username) throws CouponException
	{
		BasicDBObject query = new BasicDBObject("username", username);
		
		DBCursor cursor = collection.find(query);

		User myUser = new User();
		
		//if user exists
		if (cursor.hasNext())
		{
			try {
				
			   while(cursor.hasNext()) {
				   
				   DBObject ob = cursor.next();
				   
				   myUser.setUsername((String)ob.get("username"));
				   myUser.setPassword((String)ob.get("password"));
				   myUser.setAdmin(Boolean.parseBoolean((String)ob.get("admin")));
				   myUser.setBusinessName((String)ob.get("businessName"));
			   }
			   
			   return myUser;
			}
			   catch (MongoException e)
				{
				   e.printStackTrace();
			   	}
				finally {
				   
					cursor.close();
				}
		}
		else
		{
			throw new CouponException("User does not exist!");
		}
		
		return null;
	}
	
	@Override
	public boolean removeUser(String username)
	{
		BasicDBObject query = new BasicDBObject("username", username);
		
		DBObject ob = collection.findOne(query);
		
		User user = new User((String)ob.get("username"),
				(String)ob.get("password"),
				Boolean.parseBoolean((String)ob.get("admin")),
				(String)ob.get("businessName"));
		
		//removing all coupons associated with user
		CouponTable couponTable = CouponTable.getInstance();
		//CouponsCart cart = (CouponsCart)request.getSession().getAttribute("cart");
		
		Iterator iter = couponTable.getCouponsByBusiness(user.getBusinessName());
		
		while (iter.hasNext())
		{
			couponTable.deleteCoupon(((Coupon)iter.next()).getId());
			//cart.removeLine((Coupon)iter.next());
		}
		log.info("UserTable | User's coupons deleted");
		//Remove the user
		collection.remove(query);
		log.info("UserTable | User deleted");
		return true;
	}
	
	@Override
	public boolean updateUser(String username, String password, String businessName)
	{
		BasicDBObject query = new BasicDBObject("username", username);
		
		try {
			
			//password encryption
			String passwordMD5 = parseMD5(password);
			
			BasicDBObject update = new BasicDBObject("username", username)
			.append("password", passwordMD5)
			.append("businessName", businessName);
		
			collection.findAndModify(query, update);
			log.info("UserTable | User updated");
			return true;
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}

		return false;
		
	}
	
	/**
	 * A method that encrypting the password to MD5.
	 * @param password A string value that represent the user's password. 
	 * @return A string encrypted password by MD5 protocol.
	 * @throws NoSuchAlgorithmException if something went wrong with encrypting.
	 */
	public static String parseMD5(String password) throws NoSuchAlgorithmException
	{
		String passwordMD5 = null;
		
		try {
            // Create MessageDigest instance for MD5
			log.info("UserTable | parsing MD5 password");
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            passwordMD5 = sb.toString();
        	log.info("UserTable | parsing MD5 password - Done.");
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
		
		return passwordMD5; 
	}
}

package il.ac.hit.controller;

import il.ac.hit.model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
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
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = LogManager.getRootLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        BasicConfigurator.configure();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getHeader("Referer");
		PrintWriter out = response.getWriter();
		
		
		if (path.endsWith("addUser.jsp"))  //OK
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String businessName = request.getParameter("businessName");
			
			boolean success = UserTable.getInstance().addUser(username, password, businessName);
			
			if (success)
			{
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
				request.setAttribute("successmsg", "User added successfuly!");
				dispatcher.forward(request, response);
				log.info("UserController | User has been added successfully!");
			}
		   else
		   {
			   	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				request.setAttribute("errmsg", "User already exists!");
				dispatcher.forward(request, response);
				log.info("UserController | User already exists!");
		   } 
		}
		else if (path.endsWith("getUser.html"))
		{
			String username = request.getParameter("username");
			
			User myUser = null;
			
			try {
				
				myUser = UserTable.getInstance().getUser(username);
			
				if(myUser != null)
				{
				    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getUser.jsp");
					
					request.setAttribute("user", myUser);
					
					dispatcher.forward(request, response);
				}
				else
				{
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					
					request.setAttribute("errmsg", "No such user!");
					
					log.info("UserController | No such user!");
					
					dispatcher.forward(request, response);
					
					
				}
			} catch (CouponException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				
				request.setAttribute("errmsg", e.getMessage());
				
				dispatcher.forward(request, response);
			}
			
		}
			else if (path.endsWith("removeUser.jsp"))
			{
				String username = request.getParameter("username");
				
				boolean success = UserTable.getInstance().removeUser(username);
				
				if(success)
				{
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
					request.setAttribute("successmsg", "User removed successfully!");
					dispatcher.forward(request, response);
					log.info("UserController | User removed successfully!");
				}
			}
			else if (path.endsWith("updateUser.jsp"))
			{
				String username = request.getParameter("username");
				
				User myUser = null;
				
				try {
					
					myUser = UserTable.getInstance().getUser(username);
				
					if (myUser != null)
					{
					    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateUserInterface.jsp");
						
						request.setAttribute("user", myUser);
						
						log.info("UserController | User's fields updated successfully!");
	
						dispatcher.forward(request, response);
					}
					else
					{
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
						
						request.setAttribute("errmsg", "No such user!");
						
						log.info("UserController | User not exists!");
						
						dispatcher.forward(request, response);
						
					}
				} catch (CouponException e) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					
					request.setAttribute("errmsg", e.getMessage());
					
					dispatcher.forward(request, response);
				}
				
			}
			else if (path.endsWith("UserController"))
			{
				if (request.getParameter("username") != null)
				{	
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String businessName = request.getParameter("businessName");
					
					boolean success = UserTable.getInstance().updateUser(username, password, businessName);
					
					if(success)
					{
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
						
						request.setAttribute("successmsg", "User updated successfully!");
						
						log.info("UserController | User update completed!");
						
						dispatcher.forward(request, response);
					}
				}
			}
			else if (path.endsWith("login.jsp"))
			{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				User user = null;
				
				try {
					
					user = UserTable.getInstance().getUser(username);
				
					String savedPassword = user.getPassword();
					
					String passwordMD5 = null;
			
					log.info("UserController | password ecryption started");
					
					passwordMD5 = UserTable.parseMD5(password);
					
					if (passwordMD5.equals(savedPassword))
					{
						log.info("UserController | password matched!");
						RequestDispatcher dispatcher = null;
						
						if(user.isAdmin())//Business or Admin
						{
							log.info("UserController | user is Admin.");
							dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp");	
						}
						else
						{
							log.info("UserController | user is Business.");
							//Forward to business management panel
							dispatcher = getServletContext().getRequestDispatcher("/businessPanel.jsp");
						}
						
						request.getSession().setAttribute("user", user);
						
						dispatcher.forward(request, response);
					}
					else
					{
						log.info("UserController | Access Denied!");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
						
						request.setAttribute("errmsg", "Access Denied!<br><br><br>The username or password is incorrect!");
						
						dispatcher.forward(request, response);
					}
				
				} catch (NoSuchAlgorithmException | CouponException e) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					
					request.setAttribute("errmsg", e.getMessage());
					
					dispatcher.forward(request, response);
				}
				
			}
	}
}

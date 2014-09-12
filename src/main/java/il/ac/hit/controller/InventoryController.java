package il.ac.hit.controller;

import il.ac.hit.model.*;
import il.ac.hit.util.UploadFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import javax.servlet.http.Part;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class InventoryController
 */
@WebServlet("/InventoryController")
@MultipartConfig
public class InventoryController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Logger log = LogManager.getRootLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryController()
    {
        super();
        BasicConfigurator.configure();
		log.info("InventoryController | Object Created.");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		PrintWriter out = response.getWriter();
		String path = request.getHeader("Referer");
		log.debug("InventoryController | doPost |  path=" + path);
		
		if (path.endsWith("addCoupon.jsp"))
		{
			//Uploading the image
			log.info("InventoryController | endsWith addCoupon");

			UploadFile uploadFile = new UploadFile();
			
			String image = uploadFile.upload(request);
			
			log.info("InventoryController | end of uploading coupon image");
			
			int id = Integer.parseInt(request.getParameter("id"));
			String business = request.getParameter("business");
			String details = request.getParameter("details");
			String category = request.getParameter("category");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			log.info("InventoryController | end of saving coupon details.");
			
			log.info("InventoryController | Starting Time parsing");
			String dateStr = request.getParameter("date");
			String dateNewStr = dateStr.replace('T', ' ');
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = null;
			
			try
			{
				date = dateFormat.parse(dateNewStr);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			
			Coupon myCoupon = new Coupon(id, business, image, details, category, date, latitude, longitude);
			
			RequestDispatcher dispatcher = null;
			
			if(CouponTable.getInstance().addCoupon(myCoupon))
			{
				log.info("InventoryController | addCoupon | fileUploadedSuccessfuly");
				dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
				request.setAttribute("successmsg", "Coupon added successfuly!");
			}
			else
			{
				log.info("InventoryController | Coupon already exist");
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				request.setAttribute("errmsg", "Coupon id already exists!");
			}
			
			dispatcher.forward(request, response);
		}
		else if (path.endsWith("removeCoupon.html"))
		{
			CouponTable.getInstance().deleteCoupon(Integer.parseInt(request.getParameter("id")));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
			request.setAttribute("successmsg", "Coupon removed successfuly!");
			dispatcher.forward(request, response);
		}
		else if (path.endsWith("InventoryController"))//category browsing
		{
			String category = request.getParameter("category");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/browseCoupons.jsp");
			
			if (category.equals("All"))
			{
				request.setAttribute("category", null);
			}
			else
			{
				request.setAttribute("category", category);
			}
			
			dispatcher.forward(request, response);
			
		}
		else if (path.endsWith("getCoupon.html"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getCoupon.jsp");
			
			request.setAttribute("coupon", CouponTable.getInstance().getCoupon(id));
			
			dispatcher.forward(request, response);
		}
		else if (path.endsWith("browseCoupons.jsp"))
		{
			
			String category = request.getParameter("category");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/browseCoupons.jsp");
			
			request.setAttribute("category", category);
			
			dispatcher.forward(request, response);
			
		}
		else //update + remove + category browsing
		{
			log.debug("-----ELSE-----");
			
			if (request.getParameter("remove") != null) //remove coupon
			{
				int id = Integer.parseInt(request.getParameter("remove"));
				
				HttpSession session = request.getSession();
				
				((CouponsCart)session.getAttribute("cart")).removeLine(CouponTable.getInstance().getCoupon(id));
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/couponsCart.jsp");
				
				log.info("InventoryController | coupon removed!");
				
				dispatcher.forward(request, response);
			}
			else if (request.getParameter("id") != null) //update coupon
			{
				//Uploading the image
				
				UploadFile uploadFile = new UploadFile();
				
				String image = uploadFile.upload(request);
				
				log.info("InventoryController | Picture uploaded.");
				
				//updating the rest of the details
				int id = Integer.parseInt(request.getParameter("id"));
				String business = request.getParameter("business");
				String details = request.getParameter("details");
				String category = request.getParameter("category");
				
				//keep original location data which cannot be altered after coupon creation
				double latitude = Double.parseDouble(request.getParameter("latitude"));
				double longitude = Double.parseDouble(request.getParameter("longitude"));
				
				if (image == null) // if no new image was uploaded, keep old one
				{
					image = request.getParameter("originalImage");
				}
				
				log.info("InventoryController | starting the time parsing");
				String dateStr = request.getParameter("date");
				String dateNewStr = dateStr.replace('T', ' ');
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				
				Date date = null;
				
				try 
				{
					date = dateFormat.parse(dateNewStr);
					log.debug("InventoryController | Dateformat:" + date);
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
			
				//--- End of time parsing
				
				Coupon myCoupon = new Coupon(id, business, image, details, category, date, latitude, longitude);
				CouponTable.getInstance().updateCoupon(myCoupon);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
				request.setAttribute("successmsg", "Coupon updated successfuly!");
				dispatcher.forward(request, response);
			}
			else // browse by category
			{
				String category = request.getParameter("category");
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/browseCoupons.jsp");
				
				if (category.equals("All"))
				{
					request.setAttribute("category", null);
				}
				else
				{
					request.setAttribute("category", category);
				}
				
				dispatcher.forward(request, response);
			}
		}
	}
}

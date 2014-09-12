package il.ac.hit.tags;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * A class for generating Error massages using jsp tags.
 */
public class ErrorMessage extends SimpleTagSupport {
	
	private String msg;
	
	/**
	 * A tag class for usage of custom tag
	 */
	public void doTag() throws JspException, IOException
	{
		if (msg != null)
		{
			JspFragment body = getJspBody();
			StringBuffer sb = new StringBuffer();
			sb.append("<h4>" + msg + "</h4>");
			JspWriter out = getJspContext().getOut();
			out.write(sb.toString());
		}			
	}
	
	/**
	 * A setter of msg parameter.
	 * @param msg A string value that represent the massage
	 */
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	/**
	 * A getter of msg parameter.
	 * @return A string value that represent the massage. 
	 */
	public String getMsg()
	{
		return msg;
	}
}

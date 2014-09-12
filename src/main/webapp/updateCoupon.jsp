<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isErrorPage="false" errorPage="error.jsp" %>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Update Coupon</title>
<link href="css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style>
.borderless tbody tr td, .borderless thead tr th {
    border: none;
}

body

{
	font-family: "trebuchet ms", sans-serif;
}

h1{
	color: #229022;
}


h5
{
font-size: 20px !important;
line-height: 30% !important;    

}

input{
	font-size: 20px !important;
}
</style>

</head>
<body>

<%
	User user = (User)session.getAttribute("user");

	if (user == null)
	{

		throw new CouponException("You need to login to access that page!");
	}
%>

<div class="container">
<h1><i class="fa fa-refresh fa-lg"></i>&nbsp;Update Coupon</h1>

<div class="row"><div class="col-md-7">
</div></div>
<br>
<table class="table borderless">
<tr>
<th><h4>ID</h4></th>
<th><h4>Business ID</h4></th>
<th><h4>Image</h4></th>
<th><h4>Details</h4></th>
<th><h4>Category</h4></th>
<th><h4>Expiry Date</h4></th>
<th><h4>Update</h4></th>
</tr>

<% 
	if(application.getAttribute("couponTable") == null)
	{
		application.setAttribute("couponTable",CouponTable.getInstance());
	}

	CouponTable couponTable = (CouponTable)application.getAttribute("couponTable");

	couponTable.removeExpiredCoupons();

	String businessAttribute = ((User)session.getAttribute("user")).getBusinessName();

	Iterator iter = couponTable.getCouponsByBusiness(businessAttribute);

	Coupon curCoupon = null;
	int id;
	String business, image, details, category, date;

	while (iter.hasNext())
	{
		curCoupon = (Coupon)(iter.next());
		id = curCoupon.getId();
		business = curCoupon.getBusiness();
		image = "http://mahshev.herobo.com/agcoupons/" + curCoupon.getImage();
		details = curCoupon.getDetails();
		category = curCoupon.getCategory();
		date = curCoupon.getDate().toString();

%>

<tr>
	<td><h5><%=String.valueOf(id)%></h5></td><td><h5><%=business%></h5></td>
	<td><img src=<%=image%> width="80" height="80" border="0"></img></td>
	<td><h5><%=details%></h5></td><td><h5><%=category%></h5></td>
	<td><h5><%=date%></h5></td>
	<td><a class="btn btn-primary" href=updateCouponInterface.jsp?id=<%=String.valueOf(id)%>>Update</a></td>
</tr>

<%
}

%>

</table>
</form>
</table>
</body>
</html>
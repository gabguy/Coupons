<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Coupons Cart</title>
<link href="css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style>
.borderless tbody tr td, .borderless thead tr th {
    border: none;
    vertical-align: middle;
}

body

{
	font-family: "trebuchet ms", sans-serif;
}

h1{
	color: #229022;
}

h4
{
	color: #301580;
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

<div class="container">

<h1><i class="fa fa-shopping-cart fa-lg"></i>&nbsp;Coupons Cart</h1>

<div class="row"><div class="col-md-7">
</div>
</div>

<%
	String disabled = "";
	String msg = "";

	int id = 0;

	if(request.getParameter("id") != null)
	{
		id = Integer.parseInt(request.getParameter("id"));
	}


	CouponTable couponTable = (CouponTable)application.getAttribute("couponTable");


	

	if (session.getAttribute("cart") == null)
	{
		session.setAttribute("cart", new CouponsCart());
	}

	CouponsCart cart = (CouponsCart)(session.getAttribute("cart"));

	
	if (cart.isEmpty())
	{

		msg = "<br><h5>Your coupons cart is empty...</h5>";
		disabled = "disabled=\"disabled\"";

	}

	if (id > 0)
	{

		couponTable.removeExpiredCoupons();
		

		Coupon coupon = couponTable.getCoupon(id);

		if (coupon != null)
		{
			cart.addCouponToCart(coupon);
			msg = "";
			disabled = "";
		}
	}

	cart.removeExpiredCouponsFromCart();
	
	out.println(cart.getXMLTable());
	out.println(msg);

%>


<br>

<a href=browseCoupons.jsp class="btn btn-default">Back</a>
<a href=thanks.jsp class="btn btn-warning" <%=disabled%>><i class="fa fa-shopping-cart"></i>&nbsp;Checkout</a>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.text.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    vertical-align: middle;
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
<div class="container">
<h1><i class="fa fa-refresh fa-lg"></i>&nbsp;Update Coupon</h1>
<%
	if(application.getAttribute("couponTable") == null)
	{
		application.setAttribute("couponTable",CouponTable.getInstance());
	}
	CouponTable couponTable = (CouponTable)application.getAttribute("couponTable");

	System.out.println("updateCoupon | couponTable = " + couponTable);
	
	System.out.println("updateCoupon | id = " + request.getParameter("id"));
	int id = Integer.parseInt((String)request.getParameter("id"));

	Coupon coupon = couponTable.getCoupon(id);

	String image = "http://mahshev.herobo.com/agcoupons/" + coupon.getImage();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	String date = dateFormat.format(coupon.getDate());
	date = date.replace(' ', 'T');

	String latitude = String.valueOf(coupon.getLatitude());
	String longitude = String.valueOf(coupon.getLongitude());
%>

<div class="row"><div class="col-md-7">

<table class="table borderless">

<form action="InventoryController" enctype="multipart/form-data" method="post">
<tr>
	<td><h5>Coupon ID:</h5></td>
	<td><input class="form-control" name="id" value=<%=coupon.getId() %> readonly></td></tr>
<tr>
	<td><h5>Coupon Business:</h5></td>
	<td><input class="form-control" type="text" name="business" autofocus tabindex="1" required readonly value=<%= coupon.getBusiness() %>> </td>
</tr>
<tr>
	<td><h5>Coupon Image:</h5></td>
	<td><img src=<%=image%> width="80" height="80" border="0"/></td>
	<input type="hidden" name="originalImage" value=<%=image%>>
</tr>
<tr>
	<td><h5>Change Image:</h5></td>
	<td><input type="file" name="image"></td>
</tr>
<tr>
	<td><h5>Coupon Details:</h5></td>
	<td><textarea rows="3" class="form-control" type="text" name="details"><%=coupon.getDetails() %></textarea></td>
</tr>
<tr>
	<td><h5>Coupon Category:</h5></td>
	<td><input class="form-control" type="text" name="category" value=<%=coupon.getCategory() %>></td>
</tr>
<tr>
<td><h5>Expiry Date:</h5></td>
<td><input class="form-control" type="datetime-local" name="date" required value=<%=date%>></td>
</tr>

<input type="hidden" name="latitude" value=<%=latitude%>>
<input type="hidden" name="longitude" value=<%=longitude%>>

<tr>
<td><button class="btn btn-primary btn-lg" type="submit">Update</button></td>
</tr>
</table>
</form>
</body>
</html>
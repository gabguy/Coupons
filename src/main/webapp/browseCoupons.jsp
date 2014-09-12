<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Browse Coupons</title>
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
line-height: 40% !important;    

}

input{
	font-size: 20px !important;
}
</style>

<!--Locstion gathering-->

<script language="javascript">
var lat, lon;

function myfunc(ob)
{
  lat = ob.coords.latitude;
  lon = ob.coords.longitude;
}
function errfunc(ob)
{
 alert(ob.message);
}
if(window.navigator.geolocation)
{
 window.navigator.geolocation.getCurrentPosition(myfunc,errfunc);
}
else
{
 alert("geolocation is not supported");
}
</script>

</head>
<body>

<% 
	if(application.getAttribute("couponTable") == null)
	{
		application.setAttribute("couponTable",CouponTable.getInstance());
	}

	CouponTable couponTable = (CouponTable)application.getAttribute("couponTable");

	couponTable.removeExpiredCoupons();

	String latitude = request.getParameter("latitude");
	String longitude = request.getParameter("longitude");

%>

<div class="container">
<div class="row">
<div class="col-md-4">
<h1><i class="fa fa-search fa-lg"></i>&nbsp;Browse Coupons</h1>

</div>
</div>



<div class="row">
<div class="col-md-8">
<form action="InventoryController" method="post">

<div class="col-md-3">
<h4>Filter by category:</h4>
</div>
<div class="col-md-5">
<select class="form-control" name="category">
<option value="All">All</option>
<%
	String categoryAttribute = (String)request.getAttribute("category");

	Iterator<String> iterator = couponTable.getCategories();

	String cat = "", selected = "";

	while (iterator.hasNext())
	{
		cat = ((String)iterator.next());
		
		if (cat.equals(categoryAttribute))
		{
			selected = "selected";
		}
		else
		{
			selected = "";
		}

%>

<option value=<%=cat%> <%=selected%>><%=cat%></option>

<%
	}
%>

</select>
</div>

<button type="submit" class="btn btn-success">
<i class="fa fa-arrow-circle-right"></i></button>
&nbsp;&nbsp;
<a href="#" onclick="window.location.href='browseCoupons.jsp?latitude='+lat+'&longitude='+lon" class="btn btn-success">
<i class="fa fa-location-arrow"></i>&nbsp;Filter by location</a>

</form>
</div>
</div>
<br>
<table class="table borderless">
<tr>
<th><h4>ID</h4></th>
<th><h4>Business ID</h4></th>
<th><h4>Image</h4></th>
<th><h4>Details</h4></th>
<th><h4>Category</h4></th>
<th><h4>Expiry Date</h4></th>
</tr>

<%
	

	Iterator iter = null;

	if (categoryAttribute != null)
	{
		iter = couponTable.getCouponsByCategory(categoryAttribute);
	}
	else if (latitude != null && longitude != null)
	{
		iter = couponTable.getCouponsByLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
	}
	else
	{
		iter = couponTable.getCoupons();
	}

	
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
	<td><a href=couponsCart.jsp?id=<%=String.valueOf(id)%> class="btn btn-warning"><i class="fa fa-shopping-cart"></i>&nbsp;Add</a></td>
	</tr>
<%
		}
%>

</table>

<br><br>
<a href="index.html" class="btn btn-default"><i class="fa fa-home"></i>&nbsp;Home</a>
&nbsp;&nbsp;
<a href="couponsCart.jsp" class="btn btn-info"><i class="fa fa-shopping-cart"></i>&nbsp;Coupons Cart</a>
<br><br><br>
</body>
</html>
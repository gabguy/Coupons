<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isErrorPage="false" errorPage="error.jsp" %>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Add New Coupon</title>
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

<!-- Location calculation -->

<script type="text/javascript"
src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
</script>

<script type="text/javascript">
var defaultLat = 32.080331;
var defaultLng = 34.781230;

var map;
var myCenter=new google.maps.LatLng(defaultLat, defaultLng);
var lat, lng;

function initialize()
{
var mapProp = {
  center:myCenter,
  zoom:15,
  mapTypeId:google.maps.MapTypeId.ROADMAP
  };

  map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

  google.maps.event.addListener(map, 'click', function(event) {
    placeMarker(event.latLng);
  });
}

function placeMarker(location) {
  var marker = new google.maps.Marker({
    position: location,
    map: map,
  });
  var infowindow = new google.maps.InfoWindow({
    content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng()
  });

  lat = location.lat();
  lng = location.lng();

  infowindow.open(map,marker);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

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
<h1><i class="fa fa-plus fa-lg"></i>&nbsp;Add New Coupon</h1>
<div class="row"><div class="col-md-6">
<table class="table borderless">
<form action="InventoryController" enctype="multipart/form-data" method="post" id="form">
<tr>
<th class="col-sm-3"></th>
<th class="col-sm-3"></th>
</tr>

<tr>
<td><h5>Id:</h5></td>
<td><input type="text" name="id" autofocus tabindex="1" pattern="[0-9]{1,8}" required placeholder="numbers only" class="form-control"></td>
</tr>
<tr>
<td><h5>Business:</h5></td>
<td><input type="text" name="business" tabindex="2" pattern="[0-9]{1,8}" required placeholder="numbers only" class="form-control" value=<%=user.getBusinessName()%> readonly></td>
</tr>
<tr>
<td><h5>Image:</h5></td>
<td><input type="file" name="image" accept="image/*" required></td>
<tr>
<td><h5>Details:</h5></td>
<td><textarea rows="3" name="details" tabindex="3" class="form-control"></textarea>
</td>
</tr>
<tr>
<td><h5>Category:</h5></td>
<td><input type="text" name="category" tabindex="4" required class="form-control"></td></tr>
<tr>
<td><h5>Expiry Date:</h5></td>
<td><input type="datetime-local" name="date" required class="form-control"></td>
</tr>
<tr>
<td><h5>Location Data</h5>
</tr>
<tr>
<td><div id="googleMap" style="width:550px;height:430px;"></div></td>
</tr>

<input type="hidden" value="OUTPUT" id="latitude" name="latitude">
<input type="hidden" value="OUTPUT" id="longitude" name="longitude">

<script type="text/javascript">
var form = document.getElementById("form");
  form.onsubmit = function() {
	document.getElementById('latitude').value = lat;
    document.getElementById('longitude').value = lng;
	}
 </script>

<!--Buttons-->
<tr>
<td align="right"><button class="btn btn-primary btn-lg" type="submit">Add</button></td>
<td><button class="btn btn-default btn-lg" type="reset">Reset</button></td>
</tr>

</table>
</div>
</form>
</body>
</html>
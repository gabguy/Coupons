<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isErrorPage="false" errorPage="error.jsp" %>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Add New User</title>
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

h4{
	font-size: 15px !important;
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
	else
	{
		if (!user.isAdmin())
		{
			throw new CouponException("You do not have access to this page!");
		}
	}
%>

<div class="container">
<h1><i class="fa fa-plus fa-lg"></i>&nbsp;Add New User</h1>
<div class="row"><div class="col-md-6">
<form action="UserController" method="post">

<table class="table borderless">
<tr>
	<td></td>
	<td></td>
</tr>
<tr>
<td><h5>Username:</h5></td>
<td><input type="text" name="username" autofocus tabindex="1" required class="form-control"></td>
</tr>
<tr>
<td><h5>Password:</h5></td>
<td><input type="password" name="password" tabindex="2" required class="form-control"></td>
</tr>
<tr>
<td><h5>Business Name:</h5></td>
<td><input type="test" name="businessName" tabindex="3" required class="form-control"></td>
</tr>

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
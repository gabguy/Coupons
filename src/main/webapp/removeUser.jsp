<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isErrorPage="false" errorPage="error.jsp" %>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Remove User</title>
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
	else
	{
		if (!user.isAdmin())
		{
			throw new CouponException("You do not have access to this page!");
		}
	}
%>









<div class="container">
<h1><i class="fa fa-trash-o fa-lg"></i>&nbsp;Remove User</h1>

<div class="row"><div class="col-md-7">

<form action="UserController" method="post">

<table class="table borderless">

<tr>
<td><h5>Enter the username to be removed:</h5></td>

<td><input type="text" name="username" autofocus tabindex="1" required class="form-control"></td>
</tr>

<tr>

<td><button class="btn btn-danger btn-lg" type="submit">Remove</button></td>
</tr>
</table>
</form>
</body>
</html>
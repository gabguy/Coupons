<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Update User</title>

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
<div class="container">
<h1><i class="fa fa-refresh fa-lg"></i>&nbsp;Update User</h1>

<%
	User user = (User)request.getAttribute("user");	
%>

<div class="row"><div class="col-md-7">

<table class="table borderless">

<form action="UserController" method="post">
<tr>
	<td><h5>Username:</h5></td>
	<td><input class="form-control" name="username" value=<%=user.getUsername() %> readonly></td>
</tr>
<tr>
	<td><h5>Password:</h5></td>
	<td><input type="password" name="password" autofocus tabindex="1" class="form-control">
	</td>
</tr>
<tr>
	<td><h5>Business Name:</h5></td>
	<td><input type="text" name="businessName" autofocus tabindex="2" class="form-control" value=<%=user.getBusinessName()%>>
	</td>
</tr>

<tr>
<td><button class="btn btn-primary btn-lg" type="submit">Update</button></td>
</tr>
</table>
</form>
</body>
</html>
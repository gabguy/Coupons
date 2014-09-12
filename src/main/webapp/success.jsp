<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*, java.util.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Success</title>
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
	color: #3B11BA;
}


h5
{
	font-size: 20px !important;
	line-height: 30% !important;    
	color: #3B11BA;
}

input{
	font-size: 20px !important;
}
</style>

</head>


<body>

<%

	String successmsg = (String)request.getAttribute("successmsg");

	String redirect = "index.html";
	if(((User)session.getAttribute("user")).isAdmin())
	{
		redirect = "adminPanel.jsp";
	}

	else
	{
		redirect = "businessPanel.jsp";
	}
		
%>

<div class="container">
<center>
<img src="http://mahshev.herobo.com/agcoupons/success1.jpg"></img>
<h5>&nbsp;&nbsp;<%=successmsg%></h5>
<a href=<%=redirect%> border="0"><img src="http://mahshev.herobo.com/agcoupons/success2.jpg" border="0"></img></a>
</center>
</div>
</div>
</div>
</body>
</html>
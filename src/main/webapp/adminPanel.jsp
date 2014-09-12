<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="il.ac.hit.model.*" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Admin Panel</title>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="http://mahshev.herobo.com/css/bootstrap1.css">
<link rel="stylesheet" type="text/css" href="http://mahshev.herobo.com/css/bootstrap-theme.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="http://mahshev.herobo.com/js/bootstrap.min.js"></script>

<style type="text/css">

span
{
    display:inline-block;
    margin:0 20px;
}


a     {outline : none;
font-size:12px  }
a     {border:0;        }
img   {border : 0;      }
a img {outline : none;  }

body
{
    color: #76797c;
    background-color: #ffffff;
}

h2
{
    margin: 0;     
    color: #000;
    padding-top: 20px;
    font-size: 40px;
    font-family: "trebuchet ms", sans-serif;    
}


h3
{
    margin: 0;     
    color: grey;
    padding-top: 20px;
    font-size: 30px;
    font-family: "trebuchet ms", sans-serif;    
}

h4
{
    margin: 0;     
    color: #000;
    padding-top: 20px;
    font-size: 20px;
    font-family: "trebuchet ms", sans-serif;    
}

.item
{
    background: #efefef00;   
    text-align: center;
    height: 580px !important;

}

.carousel
{
    margin-top: 0px;
}
.bs-example
{
  margin: 0px !important;
     background: "#ffffff"
}

p
{
    font-size: 16px !important;
}


</style>
</head>


<body oncontextmenu="return false">




<%  User user = (User)session.getAttribute("user");

  if (user == null)
  {
%>
  <jsp:forward page="accessDenied.html"/>
<%
  }

%>

    <!-- add navbar-fixed-top for topping -->
   <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      
    </div>
<p class="navbar-text navbar-left"> <a href="#" class="navbar-link"> </a></p>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
<li><img src="http://mahshev.herobo.com/agclogo3.png"></img></li>
        <li><a href="index.html"><i class="fa fa-home fa-lg"></i></a></li>
        <li><a href="about.html">About</a></li>
        <li><a href="browseCoupons.jsp">Coupons</a></li>

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">MVC Chooser <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
             
            <li><a href="#"><i class="fa fa-circle"></i>&nbsp; Admin side</a></li>
           
            
            <li class="divider"></li>
             <li><a href="#"><i class="fa fa-circle-o"></i>&nbsp; Client side</a></li>
           
          </ul>
           <li><a data-toggle="modal"href="http://mahshev.herobo.com/#myModal" border="0">Contact</a></li>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-user"></i>&nbsp;<%=user.getUsername()%> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
           
            <li><a href="login.jsp"><i = class="fa fa-sign-out"></i>&nbsp; Sign out</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

 <div class="container">
  <div class="row">
   
  </div>  </div>
  
</div>
</div>
</div>
</div>

<hr class="layout">
  <section class="content">
    <div class="container">
<h3 class="text-center"><br><i style="color:grey" class="fa fa-bars"></i>  Admin Panel</h3>
<br><br>

      <div class="hr hr-short hr-center"><span class="hr-inner"><span class="hr-inner-style"></span></span></div>

      <div class="row">

        <div class="col-xs-4">

          <div class="feature">

            <a href="addCoupon.jsp"> <i style="color:#684e91" class="fa fa-plus fa-4x"></i></a>

            <div class="text">
              <h4>Add Coupon</h4>
            
              <p>Add a coupon to the database. </p>

            </div>

          </div> <!-- /.feature -->


        </div> <!-- /.col-xs-4 -->


        <div class="col-xs-4">

          <div class="feature">

           <a href="updateCoupon.jsp"> <i style="color:#684e91" class="fa fa-refresh fa-4x"></i></a>

            <div class="text">
              <h4>Update Coupon </h4>
            
              <p>Update a coupon fields.</p>

            </div>

          </div> <!-- /.feature -->

        </div> <!-- /.col-xs-4 -->


        <div class="col-xs-4">

          <div class="feature">

            <a href="removeCoupon.jsp"><i style="color:#e90404" class="fa fa-trash-o fa-4x"></i></a>

            <div class="text">
              <h4>Remove Coupon</h4>
            
              <p>Removing a coupon from the database.    </p>

            </div>

          </div> <!-- /.feature -->

        </div> <!-- /.col-xs-4 -->


      </div> <!-- /.row -->


      <br/><br/><br/><br/><br/><br/>


      <div class="row">
        <div class="col-xs-4">
          <div class="feature">
            <a href="addUser.jsp"> <i style="color:#684e91" class="fa fa-plus fa-4x"></i> </a>
            <div class="text">
              <h4>Add User</h4>
            
              <p>Add new user to the database.</p>

            </div>

          </div> <!-- /.feature -->


        </div> <!-- /.col-xs-4 -->


        <div class="col-xs-4">

          <div class="feature">

           <a href="updateUser.jsp"> <i style="color:#684e91" class="fa fa-refresh fa-4x"></i></a>

            <div class="text">
              <h4>Update User</h4>
            
              <p>Update user data.</p>

            </div>

          </div> <!-- /.feature -->

        </div> <!-- /.col-xs-4 -->


        <div class="col-xs-4">

          <div class="feature">

          <a href="removeUser.jsp">   <i style="color:#e90404" class="fa fa-trash-o fa-4x"></i></a>

            <div class="text">
              <h4>Remove User</h4>
            
              <p>Remove user from the database.</p>

            </div>

          </div> <!-- /.feature -->

        </div> <!-- /.col-xs-4 -->
      </div> <!-- /.row -->


  <br/><br/><br/><br/><br/><br/>

      <div class="row">
        <div class="col-xs-4">
          <div class="feature">
            <a href="browseCoupons.jsp"> <i style="color:#684e91" class="fa fa-search fa-4x"></i> </a>
            <div class="text">
              <h4>Browse Coupons</h4>
            
              <p>List of all coupons available in the store.</p>

            </div>
          </div> <!-- /.feature -->
        </div> <!-- /.col-xs-4 -->


<div class="col-xs-4">

          <div class="feature">

           <a href="couponsCart.jsp"> <i style="color:#684e91" class="fa fa-shopping-cart fa-4x"></i></a>

            <div class="text">
              <h4>Shopping Cart</h4>
            
              <p>Getting your shopping cart description.</p>

            </div>

          </div> <!-- /.feature -->

        </div> <!-- /.col-xs-4 -->



<div class="col-xs-4">

          <div class="feature">

           <a href="login.jsp"> <i style="color:#e90404" class="fa fa-sign-out fa-4x"></i></a>

            <div class="text">
              <h4>Logout</h4>
            
              <p>Close the admin's session.</p>

            </div>

          </div> <!-- /.feature -->

        </div> <!-- /.col-xs-4 -->



  </div> <!-- /.container -->

<br><br>
    <div class="banner">

        <div class="container"><br><br><br><br> 

        </div>
        <!-- /.container -->

    </div>
    <!-- /.banner -->

</div>
</div>
</div>

<footer>  
<div class="container text-left">

 <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li>
                            <a class="almogLinker1" href="#home">Home</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a class="almogLinker1" href="#about">About</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a class="almogLinker1" href="#services">Coupons</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a class="almogLinker1" href="#contact">Contact</a>
                        </li>
                    </ul>
                  
                </div>
            </div>
        </div>


&nbsp; &nbsp;<a class="almogLinker1" data-toggle="modal" href="#myModal">Copyrights &copy; AGCoupons 2014. All Rights Reserved.</a>
                </p></p>

</div>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4><i class="fa fa-gear fa-spin"></i>&nbsp;AGCoupons | Developing team.</h4>
                            </div>
                            <div class="modal-body">

<div class="container">
        <div class="row">

          <div class="col-md-4">
         
          </div>
        </div>


          <div class="row">
<li><img src="http://mahshev.herobo.com/almogprofile.png">Almog Albalach |  <a href="https://facebook.com/almogalbalach" target="_blank">Profile</a></img></li>

<br>
<li><img src="http://mahshev.herobo.com/guyprofile.png"></img>Guy Gabbay | <a href="https://www.facebook.com/gabguy" target="_blank">Profile</a></img></li>
</div>

<br>
       <p class="almogLinker1" font-size:24px>Copyrights ֳƒג€�ֳ‚ֲ© 2014 AGCoupons,
       <a href="https://facebook.com/almogalbalach" target="_blank">Almog Albalach</a>
       &
       <a href="https://www.facebook.com/gabguy" target="_blank">Guy Gabbay</a>.
   
</p>
 </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">

                                  Close

                                </button>
                            </div>
                        </div>
                    </div>
                </div>


                </footer>
            </div>
        </div>
   

</div> 
 </div>
   </div>  

   </div>
</div>
</body>
</html>     
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<% String ctxPath = request.getContextPath(); %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Signin Template for Bootstrap</title>

  <link href="<%= ctxPath %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">

      <!-- TODO : fix form method -->
      <form class="form-signin"  action="auth" method="post">
        
        <!-- TODO : check for error message and display this div -->
      show error message here if any !
		<% String errMessage="";
			if( request.getAttribute("errorMessage")!= null ){
				errMessage=(String)request.getAttribute("errorMessage");
				out.print("<div class='alert alert-success' role='alert'>");				
				out.print(errMessage);
				out.print("</div>");
			}
			else if( request.getParameter("logout")!= null ){
                   out.print("<div class='alert alert-success' role='alert'>");
                   out.print("tu est  déconnecté !");
				   out.print("</div>");
			}
			
		%>
		
        
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <!-- TODO : should set input names -->
        <input id="inputEmail" name="femail" class="form-control" placeholder="Email address" required="" autofocus="" type="email">
        <label for="inputPassword" class="sr-only">Password</label>
        <input id="inputPassword" name="fpassword" class="form-control" placeholder="Password" required="" type="password">
        <div class="checkbox">
          <label>
            <input value="remember-me" type="checkbox"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

</div>


  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="<%= ctxPath %>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./Base.jsp"%>
</head>
<body style="background: #FFC0CB;">

	<div class="container-fluid">
		<div class="row">
			<%@include file="./NavBarHorizontal.jsp" %>
		</div>
		
		
		
		<div class="container-fluid row div1">
			<div class="col-12 col-md-6 ">
			<img alt="rocket" src="../images/rocket.png" id="rocket">
			</div>
			<div class="col-12 col-md-6 create-div">
				<h2 class="col-12 create-content" style="font-family: monospace;">Empower Your Digital Presence with Seamless Content Management!</h2>
				<%HttpSession s1=request.getSession(false);
					if(s1==null || s1.getAttribute("loginedUser")==null){
				%>
				<a class="col-2 btn btn-primary create-btn " href="loginPage">Create</a>
				<% } else{ %>
				<a class="col-2 btn btn-primary create-btn " href="create">Create</a>
				<%} %>
				
			</div>
		</div>
	</div>


<%@include file="./baseScript.jsp" %>
</body>
</html>
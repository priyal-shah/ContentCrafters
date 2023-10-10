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
			<%@include file="./NavBarHorizontal.jsp"%>
		</div>

		<div class="row col-12 col-md-6 offset-md-3 mt-5">
			<form action="login" method="get" >
			
				<h3>Login Page</h3>
				<span class="text-danger">${errorMsg }</span>
				<span class="text-success">${successMsg }</span>
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Email
						address</label> <input type="email" class="form-control"
						id="exampleInputEmail1"  name="email" required="required">
					
				</div>
				
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Password</label>
					<input type="password" class="form-control"
						id="exampleInputPassword1" name="password" required="required">
				</div>
				
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>



	</div>

	<%@include file="./baseScript.jsp"%>
</body>
</html>
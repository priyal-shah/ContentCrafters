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



		<div class="container-fluid row mt-5 ">
			<form class="col-12 justify-content-center p-5" action="contents"
				method="post" style="background-color: white;" >
				<h3 class="m-2">Create Post</h3>
				<div class="mb-3">

					<input type="text" class="form-control" name="title"
						placeholder="Title" style="border-bottom-color: #FF1493; border-left-color: white; border-right-color: white; border-top-color: white;">
				</div>
				<div class="mb-3">
					<input type="text" name="content" placeholder="Write something..."
						class="form-control" id="contentTextArea" style="border-bottom-color: #FF1493; border-left-color: white; border-right-color: white; border-top-color: white;"></input>
				</div>
				
				<div class="mb-3">
					<input type="text" name="imageUrl" placeholder="Enter image url here"
						class="form-control" id="imageUrl " style="border-bottom-color: #FF1493; border-left-color: white; border-right-color: white; border-top-color: white;"></input>
				</div>

				<button type="submit" class="btn btn-primary">Post</button>
			</form>
	
		</div>
	</div>



	<%@include file="./baseScript.jsp"%>
</body>
</html>
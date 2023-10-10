<%@page import="com.contentCrafters.entities.Reply"%>
<%@page import="com.contentCrafters.entities.Content"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./Base.jsp"%>
</head>
<body style="background-color: #F5F5F5;">

	<div class="container-fluid">

		<div class="row">
			<%@include file="./NavBarHorizontal.jsp"%>
		</div>

		<div class="row">

			<!-- header breadcrum -->
			<div class="col-12 container-fluid">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<div class="container-fluid">

						<div>
							<span><a class="navbar-toggler" type="button"
								data-bs-toggle="collapse" data-bs-target="#filterNav"
								aria-controls="navbarSupportedContent" aria-expanded="false"
								aria-label="Toggle navigation"><i
									class="fa-solid fa-magnifying-glass"></i> Filter</a></span> <span>Filters</span>
							<%
							List<String> filters = (List) request.getAttribute("filters");
							if(filters!=null){
							for (String f : filters) {
							%>
							<span>>> <%=f%></span>
							<%
							}}
							%>
						</div>
					</div>
				</nav>
			</div>


			<div class="col-12 row">
				<!-- filter -->
				<div class="col-lg-3">
					<nav class="navbar navbar-expand-lg navbar-light bg-light">
						<div class="container-fluid">


							<div class="collapse navbar-collapse" id="filterNav">
								<form action="filter-contents" method="get" id="filter-form">
									<ul class="nav navbar-nav me-auto mb-2 mb-lg-0">
										<li class="nav-item"><h5>All Filter</h5></li>
										<li class="nav-item"><div class="mb-3">
												<label for="title" class="form-label">Title</label> <input
													type="text" class="form-control" id="title" value=""
													onchange="FilterFunction()" name="title">
											</div></li>


										<li class="nav-item"><div class="mb-3">
												<label for="creator" class="form-label">Creator</label> <input
													type="text" class="form-control" id="creator" value=""
													onchange="FilterFunction()" name="creator">
											</div></li>


										<li class="nav-item"><div class="mb-3">
												<label for="date" class="form-label">Date</label> <input
													type="date" class="form-control" id="date" value=""
													onchange="FilterFunction()" name="date">
											</div></li>
										<li class="nav-item"><button type="submit"
												class="btn btn-info">Filter</button></li>
									</ul>
								</form>
							</div>
						</div>
					</nav>
				</div>



				<!-- posts -->

				<div class="col-12 col-md-8">
					<%
					List<Content> list = (List) request.getAttribute("contents");
					if(list!=null){
					for (Content c : list) {
						
						
					%>

					<div class="card mt-3 content-card">
						<div class="card-body ">
							<%
							HttpSession s1 = request.getSession(false);
							User user = null;
							if (s1 != null) {
								user = (User)s1.getAttribute("loginedUser");
							}
							if (c.getUser()!=null && user!= null && c.getUser().getId() == user.getId()) {
							%>
							<div class="col-2 ms-auto ">
								<a href="delete-contents/<%=c.getC_id()%>"><i
									class="fa-solid fa-trash" style="color: red;"></i></a> <a><i
									class="fa-solid fa-pen-to-square" style="color: blue;"></i></a>
							</div>
							<%
							}
							%>
							<h4 class="card-title"><%=c.getTitle()%></h4>
							<%
							if (c.getUser() != null) {
							%><p style="font-size: 70%; color: grey;"><%=c.getUser().getName()%>
								~
								<%=c.getDate_created()%></p>
							<%
							}
							%>
							<p class="card-text"><%=c.getContent()%></p>
							<%
							if (c.getFile() != null) {
							%><a href="<%=c.getFile()%>" target="_blank"><img alt="image"
								src="<%=c.getFile()%>" id="contentimg"></a>
							<%
							}
							%>
							<br>
							<p>
								<%
								if (c.getLike() != null) {
								%>
								<span><%=c.getLike()%></span>
								<%
								}
								%>

								<a href="<%=request.getContextPath() %>/contentCrafters/like/<%=c.getC_id()%>"><i
									class="fa-regular fa-thumbs-up"></i></a>

								<%
								if (c.getDislike() != null) {
								%>
								<span><%=c.getDislike()%></span>
								<%
								}
								%>
								<a href="<%=request.getContextPath() %>/contentCrafters/dislike/<%=c.getC_id()%>"><i
									class="fa-regular fa-thumbs-down"></i></a>
							</p>

							<form action="<%=request.getContextPath() %>/contentCrafters/replys/<%=c.getC_id()%>" class="d-flex"
								method="post">

								<textarea rows="1" class="form-control" name="reply"></textarea>
								<%
								if (s.getAttribute("loginedUser") != null) {
								%>
								<button type="submit" class="btn bg-info" style="height: 50px;">Reply</button>
								<%
								} else {
								%>
								<a class="btn bg-info" href="<%=request.getContextPath() %>/contentCrafters/loginPage" style="height: 50px;">Login</a>


								<%
								}
								%>

							</form>






							<p>
								<a class="mt-3" data-bs-toggle="collapse"
									href="#collapseExample<%=c.getC_id()%>" role="button"
									aria-expanded="false" aria-controls="collapseExample">See
									all replys</a>

							</p>
							<div class="collapse" id="collapseExample<%=c.getC_id()%>">
								<div class="card card-body">

									<%
									List<Reply> replys = (List) c.getReply();
									for (Reply r : replys) {
									%>
									<div class="card mt-1">
										<p style="font-size: 70%;">
											<%
											if (r.getUser() != null) {
											%>
											<span><%=r.getUser().getName()%></span>
											<%
											}
											%>
											~
											<%=r.getDate_created()%></p>
										<p><%=r.getDesc()%></p>
									</div>
									<%
									}
									%>
								</div>
							</div>

							<!-- end of replys -->
						</div>
					</div>
					<!-- end of card -->

					<%
					}}
					%>

				</div>

			</div>

		</div>
	</div>

	<%@include file="./baseScript.jsp"%>


</body>
</html>
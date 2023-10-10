

<%@page import="com.contentCrafters.entities.User"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=request.getContextPath() %>/contentCrafters/home" style="font-family: cursive;">ContentCrafters</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse d-lg-flex"
			id="navbarSupportedContent">

			<form class="d-flex ms-md-auto mt-sm-2">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>


			<ul class="navbar-nav  mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link 
					aria-current="
					href="contents">View all</a></li>
				<%
				HttpSession s = request.getSession(false);
				if (s==null || s.getAttribute("loginedUser") == null) {
				%>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/contentCrafters/loginPage">Login</a></li>
				<li class="nav-item"><a class="nav-link " href="<%=request.getContextPath() %>/contentCrafters/registerPage">SignUp</a></li>
				<%
				}
				%>


			</ul>

			<%HttpSession s2=request.getSession(false);
			User u=(User)s.getAttribute("loginedUser");
				if(s2!=null && s2.getAttribute("loginedUser")!=null){
			%>
			<div class="dropdown">
				<button class="nav-link ml-1 mr-1 btn btn-secondary dropdown-toggle"
					type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
					aria-expanded="false"
					style="border-radius: 50%; background-color: #FFC0CB; box-shadow: 1px grey;"><%=u.getName().toUpperCase().charAt(0) %></button>


				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="dropdownMenuButton1">
					<li><a class="dropdown-item" href="#">Hello <%=u.getName() %> !</a></li>
					<hr>
					<li><a class="dropdown-item" href="#">Update Profile</a></li>
					<li><a class="dropdown-item" href="#">Change Password</a></li>
					<li><a class="dropdown-item" href="<%=request.getContextPath() %>/contentCrafters/my-contents">View my posts</a></li>
					<li><a class="dropdown-item" href="<%=request.getContextPath() %>/contentCrafters/create">Create</a></li>
					<li><a class="dropdown-item" href="<%=request.getContextPath() %>/contentCrafters/logout">Logout</a></li>
				</ul>
			</div>
			<%
				}
			%>

		</div>
	</div>
</nav>
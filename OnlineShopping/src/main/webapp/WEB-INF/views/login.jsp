<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css/" />
<spring:url var="js" value="/resources/js/" />
<spring:url var="images" value="/resources/images/" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping-${title}</title>
<script>
window.menu='${title}';
window.contextRoot='${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap readABLE theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<!--Bootstrap datatable theme  -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/shop-homepage.css" rel="stylesheet">
</head>
<body>
	<div class='wrapper'>
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a href="${contextRoot}/home" class="navbar-brand">Online
						Shopping</a>
				</div>
			</div>
		</nav>
		<!-- Page Content -->
		<div class="content">
			<div class="container">
			<!-- display if wrong credential -->
			<c:if test="${not empty message}">
			<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="alert alert-danger">
						${message}
						</div>
					</div>
					</div>
			</c:if>
			<!-- display if logout -->
			<c:if test="${not empty logout}">
			<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="alert alert-success">
						${logout}
						</div>
					</div>
					</div>
			</c:if>
			
			
			
			
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Login</h4>
							</div>
							<div class="panel-body">
								<form action="${contextRoot}/login" method="POST"
									class="form-horizontal" id="loginForm">
									<div class="form-group">
										<label class="control-label col-md-4" for="username">Email</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4" for="password">Password</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
									<label class="control-label col-md-4" >Remember Me</label>
										<div class="col-md-8">
											<input type='checkbox' name='remember-me'/>   
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<input type="submit" value="login" class="btn btn-primary" />
										</div>
									</div>
								</form>
							</div>
							<div class="panel-footer">
								<div class="text-right">
									New User-<a href="${contextRoot}/register">Register Here</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>
		<!-- JQuery core -->
		<script src="${js}/jquery.min.js"></script>
		<!-- JQuery validator -->
		<script src="${js}/jquery.validate.js"></script>
		<script src="${js}/jquery.validate.min.js"></script>
		<!-- Bootstrap core JavaScript -->
		<script src="${js}/popper.min.js"></script>
		<!-- datatable plugin with bootstrap-->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- self coaded  JavaScript -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>
</html>

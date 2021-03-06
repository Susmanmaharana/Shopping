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
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
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
		<%@include file="./shared/navbar.jsp"%>
		<!-- Page Content -->
		<div class="content">
			<c:if test="${userClickHome==true}">
				<%@include file="home.jsp"%>
			</c:if>
			<c:if test="${userClickAbout==true}">
				<%@include file="about.jsp"%>
			</c:if>
			<c:if test="${userClickContact==true}">
				<%@include file="contact.jsp"%>
			</c:if>
			<c:if
				test="${userClickAllProducts==true or userClickCategoryProducts==true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<c:if test="${userClickShowProduct==true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			<c:if test="${userClickManageProducts==true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>

			<c:if test="${userClickShowCart==true}">
				<%@include file="cart.jsp"%>
			</c:if>
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
		<!--  jQuery Data tables  plugin-->
		<script src="${js}/jquery.dataTables.js"></script>
		<!--  Data tables  bootstrap javascript-->
		<script src="${js}/dataTables.bootstrap.js"></script>
		<!--  bootbox javascript-->
		<script src="${js}/bootbox.min.js"></script>

		<!-- self coaded  JavaScript -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>
</html>

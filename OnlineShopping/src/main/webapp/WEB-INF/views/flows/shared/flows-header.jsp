<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
    <spring:url var="css" value="/resources/css/" />
     <spring:url var="js" value="/resources/js/" />
     <spring:url var="images" value="/resources/images/" />
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Shopping-${title}</title>
<script >
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

<%@include file="flows-navbar.jsp" %>




    <!-- Page Content -->
    <div class="content">
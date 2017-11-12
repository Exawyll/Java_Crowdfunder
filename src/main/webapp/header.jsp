<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/SupCrowdfunder/css/main.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<title>CrowdFunder</title>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li><a href="/SupCrowdfunder/index.jsp">Home</a></li>
				<li><a href="/SupCrowdfunder/about">About</a></li>
				<li><a href="/SupCrowdfunder/listProjects">Projects</a></li>
				<c:choose>
					<c:when test="${empty sessionScope.userSession}">
						<li><a href="/SupCrowdfunder/register">Sign up</a></li>
						<li><a href="/SupCrowdfunder/login"><span
								class="glyphicon glyphicon-log-in"></span></a></li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${sessionScope.userSession.isAdmin()}">
								<li><a href="/SupCrowdfunder/auth/listCategories">Categories</a></li>
								<li><a href="/SupCrowdfunder/auth/addCategory">Add a category</a></li>
							</c:when>
						</c:choose>
						<li><a href="/SupCrowdfunder/auth/addProject">Add a
								project</a></li>
						<li><a href="/SupCrowdfunder/auth/editProfil"><span
								class="glyphicon glyphicon-user"></span>${ sessionScope.userSession.email }</a></li>
						<li><a href="/SupCrowdfunder/logout"><span
								class="glyphicon glyphicon-log-out"></span></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		</nav>
		<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
			</div>
			<div class="col-sm-8 text-left">
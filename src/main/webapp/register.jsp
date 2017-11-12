<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@include file="./header.jsp"%>


<form action="/SupCrowdfunder/register" method="POST" class="container">
	<div class="form-group">
		<label for="Firstname">Firstname :</label> <input name="Firstname"
			type="text" class="form-control">
	</div>
	
	<div class="form-group">
		<label for="Lastname">Lastname :</label> <input name="Lastname"
			type="text" class="form-control">
	</div>
	
	<div class="form-group">
		<label for="password">Password :</label> <input name="password"
			type="password" class="form-control">
	</div>

	<div class="form-group">
		<label for="password-verif">Password confirmation :</label> <input name="password"
			type="password" class="form-control">
	</div>
	
	<input type="submit" value="Submit" class="btn btn-success">
</form>
<%@include file="./footer.jsp"%>
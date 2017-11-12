<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@include file="../header.jsp"%>
<form action="/SupCrowdfunder/auth/addCategory" method="POST">

	<div class="form-group">
		<label for="name">Name :</label> <input name="name"
			type="text" class="form-control">
	</div>
	<input type="submit" value="Ajouter" class="btn btn-success">
</form>
<%@include file="../footer.jsp"%>
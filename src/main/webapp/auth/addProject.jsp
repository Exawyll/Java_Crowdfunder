<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="../header.jsp"%>
<form action="/SupCrowdfunder/auth/addProject" method="POST"
	enctype="multipart/form-data">

	<!-- TODO : Move JS into external file -->
	<script>
		$(document).ready(function() {
			$(".datepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
	</script>

	<div class="form-group">
		<label for="picture">Select picture to upload: </label><input
			type="file" name="picture" size="60" /><br /> <span class="error">${form.errors['error']}</span>
	</div>

	<div class="form-group">
		<label for="name">Name :</label> <input name="name" type="text"
			class="form-control">
	</div>
	<div class="form-group">
		<label for="endsAt">Finishes at :</label> <input name="endsAt"
			class="datepicker form-control" />
	</div>
	<div class="form-group">
		<label for="description">Description :</label>
		<textarea class="form-control" name="description"></textarea>
	</div>
	<div class="form-group">
		<label for="objective">Objectif :</label>
		<div class="input-group" style="z-index: 0;">
			<input type="number" class="form-control" name="objective" /> <span
				class="input-group-addon">&#8364;</span>
		</div>
	</div>
	<div class="form-group">
		<label for="projCat">Category</label> <select id="projCat"
			class="form-control" name="category">
			<c:forEach var="item" items="${categories}">
				<option value="${item.id}">${item.name}</option>
			</c:forEach>
		</select>
	</div>
	<input type="submit" value="Créer" class="btn btn-success">
</form>

<%@include file="../footer.jsp"%>
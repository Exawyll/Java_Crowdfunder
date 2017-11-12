<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="../header.jsp"%>

<form class="form-horizontal" method="post"
	action="/SupCrowdfunder/auth/editProfil">
	<div class="form-group">
		<label for="inputFirstname" class="col-sm-2 control-label">Firstname</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" id="inputFirstname"
				name="firstname" placeholder="Firstname"
				value="<c:out value="${ u.firstname }" />">
		</div>
	</div>
	<div class="form-group">
		<label for="inputLastname" class="col-sm-2 control-label">Lastname</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" id="inputLastname"
				name="lastname" placeholder="Lastname"
				value="<c:out value="${ u.lastname }" />">
		</div>
	</div>
	<div class="form-group">
		<label for="inputEmail" class="col-sm-2 control-label">Email <span
			class="required">*</span></label>
		<div class="col-sm-5">
			<input type="email" class="form-control" name="email" id="inputEmail"
				placeholder="Email" value="<c:out value="${ u.email }" />">
			<span class="error">${form.errors['email']}</span>
		</div>
	</div>
	<div class="form-group">
		<label for="inputPassword" class="col-sm-2 control-label">Password
			<span class="required">*</span>
		</label>
		<div class="col-sm-5">
			<input type="password" class="form-control" name="password"
				id="inputPassword" placeholder="Password"
				value="<c:out value="${ u.password }"/>"> <span
				class="error">${form.errors['password']}</span>
		</div>
	</div>
	<div class="form-group">
		<label for="inputConfirm" class="col-sm-2 control-label">Confirm
			Password <span class="required">*</span>
		</label>
		<div class="col-sm-5">
			<input type="password" class="form-control" name="confirm"
				id="inputConfirm" placeholder="Confirm"
				value="<c:out value="${ u.password }"/>" /> <span class="error">${form.errors['confirm']}</span>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-5">
			<button type="submit" class="btn btn-default">Update profil</button>
		</div>
	</div>
	<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
</form>

<%@include file="../footer.jsp"%>
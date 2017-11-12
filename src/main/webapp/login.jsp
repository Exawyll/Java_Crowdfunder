<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="./header.jsp"%>

<%@ page isELIgnored="false"%>
<c:choose>
	<%-- Test if the login worked and send the logged email --%>
	<c:when test="${!empty sessionScope.userSession}">
		<p class="success">You are logged in with the email :
			${sessionScope.userSession.email}</p>
	</c:when>
	<c:otherwise>
		<form action="/SupCrowdfunder/login" method="POST">
			<h3>Please, fill this form to log in</h3>
			<div class="form-group">
				<label for="email">Email <span class="required">*</span></label> <input
					type="email" class="form-control" name="email"
					value="<c:out value="${user.email}"/>" size="20" maxlength="60" />
				<span class="error">${form.errors['email']}</span>
			</div>
			<div class="form-group">
				<label for="password">Password <span class="required">*</span></label>
				<input name="password" type="password" class="form-control" /> <span
					class="error">${form.errors['password']}</span>
			</div>
			<input type="submit" value="Submit" class="btn btn-success">
			<span class="error">${form.errors['error']}</span>
			<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
		</form>
	</c:otherwise>
</c:choose>

<%@include file="./footer.jsp"%>
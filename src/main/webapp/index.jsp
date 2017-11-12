<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@include file="./header.jsp"%>

<c:if test="${not empty form.result}">
	<p class="${empty form.errors ? '.success' : '.error'}">${form.result}</p>
</c:if>

<h2>Welcome to the CrowFunder website.</h2>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin in
	malesuada nibh. Mauris placerat nibh ac justo aliquet, at convallis
	odio porta. Duis vitae placerat odio. Praesent et nisl ac ligula porta
	semper. Curabitur facilisis lectus non eros euismod, sed blandit eros
	fermentum. Curabitur et nulla eget justo congue sollicitudin vitae eget
	elit. Aliquam feugiat volutpat nisl elementum finibus. Donec accumsan
	nunc auctor, vestibulum enim vel, finibus enim. Quisque sed nisl sed
	eros auctor interdum in non nisi. Integer pharetra semper nunc at
	elementum. Sed vitae neque est. Nullam consectetur convallis ornare.</p>
<%@include file="./footer.jsp"%>
</body>
</html>

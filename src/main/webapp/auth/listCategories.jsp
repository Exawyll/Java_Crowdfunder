<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>
<!--<%@ page import="fr.imie.supcrowdfunder.entity.Category" %>-->
<%@include file="../header.jsp"%>
<c:choose>
	<c:when test="${empty categories}">
		<p>
			<c:out value="Empty list..." />
		</p>
	</c:when>
	<c:otherwise>
		<p class="success">${result}</p>
		<table class="table">
			<thead>
				<tr>
					<th>Name of category</th>
					<th>View</th>
					<th>Delete</th>
				</tr>
			</thead>
			<c:forEach items="${categories}" var="c">

				<tr>
					<td><c:out value="${c.name}" /></td>
					<td>
						<form action="/SupCrowdfunder/auth/showCategory" method="POST">
							<div class="form-group">
								<input name="id" type="hidden" value="${c.id}"><br>
								<button type="submit" class="btn btn-success"
									style="width: 30%;">
									<span class="glyphicon glyphicon-menu-down"></span>
								</button>
							</div>
						</form>
					</td>
					<td>
						<form action="/SupCrowdfunder/auth/removeCategory" method="POST">
							<div class="form-group">
								<input name="id" type="hidden" value="${c.id}"><br>
								<button type="submit" class="btn btn-danger">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</div>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

<%@include file="../footer.jsp"%>

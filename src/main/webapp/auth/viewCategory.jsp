<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@include file="../header.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="fr.imie.supcrowdfunder.entity.Category" %>
<%@ page import="fr.imie.supcrowdfunder.entity.Project" %>
<c:choose>
	<c:when test="${empty c}">
		<h2 class="alert alert-warning">
			<c:out value="Category not found" />
		</h2>
	</c:when>
	<c:otherwise>
		<h3><c:out value="${c.name}"/></h3>
		<span>
		<c:choose>
			<c:when test="${empty projects}">
				<p class="alert alert-warning">
					<c:out value="No project..." />
				</p>
			</c:when>
			<c:otherwise>
				<c:forEach items="${projects}" var="p">
					<tr>
						<td><c:out value="${p.name}" /></td>
						<td><c:out value="${p.createdAt}" /></td>
						<td><c:out value="${p.objective}" /></td>
						<td><c:out value="${p.getDonationsTotal()}" /></td>
						<td><c:out value="${p.description}" /></td>
						<td>
							<form action="/SupCrowdfunder/viewProject" method="POST">
								<div class="form-group">
								  <input name="id" type="hidden" value="${p.id}"><br>
								  <button type="submit" class="btn btn-success"> 
								  	<span class="glyphicon glyphicon-menu-down"></span> 
								  </button>
							  	</div>
							</form> 
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</span>
	</c:otherwise>
</c:choose>

<%@include file="../footer.jsp"%>
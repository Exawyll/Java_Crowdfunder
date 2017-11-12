<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>
<!--<%@ page import="fr.imie.supcrowdfunder.entity.Project" %>-->
<%@include file="./header.jsp"%>
<c:choose>
	<c:when test="${empty projects}">
		<p>
			<c:out value="Empty list..." />
		</p>
	</c:when>
	<c:otherwise>
		<div class="row">
			<c:forEach items="${projects}" var="p">

				<div class="col-md-4 col-sm-6 col-xs-12  ">
					<form action="/SupCrowdfunder/viewProject" method="POST">
						<input name="id" type="hidden" value="${p.id}"><br>
						<button type="submit" class="btn btn-default" style="width: 100%;" >


							<div class="project__title--category">
								<div class="project">
									<h2 class="project__title">
										<c:out value="${p.name}" />
									</h2>

									<div class="project__transform">
										<img class="project__transform"
											src="data:image/jpeg;base64,${p.picture}" alt="project">

										<p class="project__description">
											<c:out value="${p.description}" />
										</p>
									</div>

									<h4>
										<c:out value="${p.category.name}" />
									</h4>

								</div>
							</div>

						</button>
					</form>

					<%-- 					<c:out value="${p.affCreatedAt()}" /> --%>
					<%-- 					<c:out value="${p.affEndsAt()}" /> --%>
					<%-- 					<c:out value="${p.objective}" /> --%>
					<%-- 					<c:out value="${p.getDonationsTotal()}" /> --%>


				</div>

			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>

<%@include file="./footer.jsp"%>

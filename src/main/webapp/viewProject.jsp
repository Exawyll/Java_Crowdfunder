<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<%@include file="./header.jsp"%>

<c:choose>
	<c:when test="${empty p}">
		<h2 class="alert alert-warning">
			<c:out value="Project not found" />
		</h2>
	</c:when>
	<c:otherwise>
		<div class="row" style="margin-top: 30px;">
			<div class="col-xs-4 item-photo">
				<img style="max-width: 100%;"
					src="data:image/jpeg;base64,${p.picture}" alt="picture project" />
			</div>
			<div class="col-xs-5" style="border: 0px solid gray">
				<h3>
					<c:out value="${p.name}" />
				</h3>
				<h5 style="color: #337ab7">
					<c:out value="${p.category.name}" />
				</h5>

				<!-- price -->
				<h6 class="title-price">
					<h2>
						<c:out value="${p.objective}"></c:out>
						<span class="glyphicon glyphicon-euro"></span>
					</h2>
				</h6>
				<h3 style="margin-top: 0px;">
					(
					<c:out value="${p.getGoal()}%"></c:out>
					)
				</h3>

				<!-- Donations amount -->
				<div class="section">
					<h6 class="title-attr" style="margin-top: 15px;">
						<small><span>Actual donation : </span> <span><c:out
									value="${p.getDonationsTotal()}" /></span> <span
							class="glyphicon glyphicon-euro"></span></small>
					</h6>
				</div>
				<div class="section" style="padding-bottom: 20px;">
					<h6 class="title-attr">
						<small> <c:if
								test="${sessionScope.userSession.email == p.creator.email || sessionScope.userSession.isAdmin()}">
								<td>
									<form action="/SupCrowdfunder/auth/removeProject" method="POST">
										<div class="form-group">
											<input name="id" type="hidden" value="${p.id}">
											<button type="submit" class="btn btn-danger">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
										</div>
									</form>
								</td>
							</c:if>
						</small>
					</h6>
				</div>

				<!-- Add a donation -->
				<div class="section" style="padding-bottom: 20px;">
					<form action="/SupCrowdfunder/auth/addDonation" method="POST">
						<div class="form-group">
							<label>Donation amount :</label>
							<div class="input-group">
								<input id="amount" type="text" class="form-control"
									name="amount"> <span aria-describedby="amount"
									class="input-group-addon glyphicon glyphicon-euro"></span>
							</div>
							<input type="hidden" class="form-control" name="idProject"
								value="${p.id}">
						</div>
						<button type="submit" value="Do a donation"
							class="btn btn-success">
							<span style="margin-right: 20px"
								class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
						</button>
					</form>
				</div>
			</div>

			<div class="col-xs-9">
				<div style="width: 100%; border-top: 1px solid silver">
					<p style="padding: 15px;">
						<small> <c:out value="${p.description}" />
						</small>
					</p>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<span class="error">${error}</span>

<%@include file="./footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/login/booking" />

<c:url var="addSearch" value="/home/login/booking-upcoming" />

<c:url var="editUrl" value="/home/login/feedback?id=" />

<c:url var="imageUrl" value="/image?id=" />

<c:url var="acceptUrl" value="/home/login/accept-booking?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/booking-upcoming"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 100vh">
		<h2>Upcoming Booking</h2>
		<hr />
		<div class="form-outline ">


			<input type="text" name="fname" placeholder="Search By First Name"
				class="form-control" /> <font color="red" style="font-size: 13px"><sf:errors
					path="${status.expression}" /></font>


		</div>
		<div class="form-check d-flex justify-content-center mb-4 mt-2">
			<input type="submit" name="operation"
				class="btn btn-primary  pull-right" value="Search"> &nbsp; <input
				type="submit" name="operation" class="btn btn-primary pull-right"
				value="Reset">
		</div>

		<div class="container mt-2" style="width: 50%">
			<b><%@ include file="businessMessage.jsp"%></b>
		</div>
		<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />
		<div class="table-responsive">
			<table class="table bg-header rounded shadow-sm table-hover mt-5">
				<thead class="bg-footer text-white">
					<tr>

						<th scope="col">Professional Name</th>
						<th scope="col">Service Name</th>
						<th scope="col">Date Of Booking</th>
						<th scope="col">Customer Id</th>
						<c:if test="${sessionScope.user.roleId == 3}">
							<th scope="col">Status</th>
							<th scope="col">Pay</th>
							<th scope="col">Feedback</th>

						</c:if>
						<c:if test="${sessionScope.user.roleId == 2}">
							<th scope="col">Status</th>

							<th scope="col">View Feedback</th>

						</c:if>
					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="s_list" varStatus="u">
						<tr>


							<c:if
								test="${sessionScope.user.roleId == 2 || sessionScope.user.roleId == 1 || sessionScope.user.login eq s_list.createdBy}">

								<td scope="row">${s_list.user.firstName}</td>
								<td scope="row">${s_list.serviceName}</td>
								<td scope="row">${s_list.dateOfBooking}</td>
								<td scope="row">${s_list.createdBy}</td>

								<c:if test="${sessionScope.user.roleId == 2 }">
									<c:if test="${s_list.status eq 'accept'}">
										<td><a class="text-white btn btn-dark"
											href="${acceptUrl}${s_list.id}&status=accept">Accepted</a></td>
									</c:if>
									<c:if test="${s_list.status eq 'reject'}">
										<td><a class="text-white btn btn-danger"
											href="${acceptUrl}${s_list.id}&status=reject">Rejected</a></td>
									</c:if>
									<c:if test="${s_list.status eq 'requestbooking'}">
										<td><a class="text-white btn btn-dark"
											href="${acceptUrl}${s_list.id}&status=accept">Accept</a> <a
											class="text-white btn btn-danger"
											href="${acceptUrl}${s_list.id}&status=reject">Reject</a></td>
									</c:if>

								</c:if>
								<c:if test="${sessionScope.user.roleId == 3}">
									<c:if test="${s_list.status eq 'accept'}">
										<td><a class="text-white btn btn-success disabled"
											href="#">Accepted</a></td>
									</c:if>
									<c:if test="${s_list.status eq 'reject'}">
										<td><a class="text-white btn btn-danger disabled"
											href="${acceptUrl}${s_list.id}&status=reject">Rejected</a></td>
									</c:if>
									<c:if test="${s_list.status eq 'requestbooking'}">
										<td><a class="text-white btn btn-secondary disabled"
											href="#">Booking Requested...</a>
									</c:if>
									<c:if test="${sessionScope.user.roleId == 3}">
										<td></td>
										<td><a class="text-dark"
											href="${editUrl} ${s_list.user.id}"><i
												class="fas fa-edit"></i></a></td>
									</c:if>


								</c:if>
								<c:if test="${sessionScope.user.roleId == 2}">
									<c:if
										test="${s_list.status eq 'accept' || s_list.modifiedBy!=null}">
										<td><a class="nav-link "
											href="<c:url value="/home/login/feedback/search"/>?fid=${s_list.createdBy}">View
												Feedback</a></td>
									</c:if>
								</c:if>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
		<div class="clearfix">





			<nav aria-label="Page navigation example float-end">
				<ul class="pagination justify-content-end" style="font-size: 13px">
					<li class="page-item"><input type="submit" name="operation"
						class="page-link"
						<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
						value="Previous"></li>
					<c:forEach var="i" begin="1" end="${(listsize/10)+1}">
						<c:if test="${i== pageNo}">
							<li class="page-item active"><a class="page-link activate"
								href="${addSearch}?pageNo=${i}">${i}</a></li>
						</c:if>
						<c:if test="${i != pageNo}">
							<li class="page-item"><a class="page-link"
								href="${addSearch}?pageNo=${i}">${i}</a></li>
						</c:if>

					</c:forEach>
					<li class="page-item"><input type="submit" name="operation"
						class="page-link"
						<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
						value="Next"></li>
				</ul>
			</nav>
		</div>


	</div>

	</div>
</sf:form>

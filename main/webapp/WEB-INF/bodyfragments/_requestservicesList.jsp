<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/login/request-services" />

<c:url var="addSearch" value="/home/login/request-services/search" />

<c:url var="editUrl" value="/home/login/request-services?id=" />

<c:url var="imageUrl" value="/user/image?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/request-services/search"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 100vh">
		<h2>Employee List</h2>
		<hr/>
		
		
		<div class="container mt-2" style="width: 50%"><b><%@ include file="businessMessage.jsp"%></b></div>
		<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />
		<div class="table-responsive">
			<table class="table bg-header rounded shadow-sm table-hover mt-5">
				<thead class="bg-footer text-white">
					<tr>
						<%-- <c:if test="${sessionScope.user.roleId == 2 || sessionScope.user.id==s_list.userId}">
							<th scope="col">Select
								All</th>
						</c:if> --%>

						
						<th scope="col">Profile Picture</th>	
						<th scope="col">Services Name</th>
						<th scope="col">Name</th>
						<th scope="col">Available Between</th>
						<th scope="col">Cost Per Hour</th>
						<th scope="col">User Login</th>	
						<th scope="col">Contact No</th>		
										<c:if test="${sessionScope.user.roleId == 2 || sessionScope.user.id==s_list.userId}">
						<th scope="col">Action</th>
						</c:if>

					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="s_list" varStatus="u">
						<tr>
							<%-- <c:if test="${sessionScope.user.roleId == 1 || sessionScope.user.roleId == 2 || sessionScope.user.id==s_list.userId}">
								<td><input type="checkbox" class="case" name="ids"
									value="${s_list.id}"></td>
							</c:if> --%>

							<c:if test="${sessionScope.user.roleId == 1 || sessionScope.user.roleId == 2 || sessionScope.user.createdBy == s_list.createdBy || sessionScope.user.id == s_list.user.id }">
							
							<td><img src="${imageUrl} ${s_list.user.id}" width="100" height="100"></td>
							<td scope="row">${s_list.serviceName}</td>
							<td scope="row">${s_list.user.firstName}</td>
							<td scope="row">${s_list.timeSlotsStart}-${s_list.timeSlotsEnds}</td>
							<td scope="row">${s_list.costPerHour} &#36;</td>
							<td scope="row">${s_list.user.login}</td>
							<td scope="row">${s_list.user.contactNo}</td>
							
							</c:if>
							<c:if test="${sessionScope.user.id==s_list.userId}">
								<td><a class="text-dark" href="${editUrl} ${s_list.id}"
									><i class="fas fa-edit"></i></a></td>
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
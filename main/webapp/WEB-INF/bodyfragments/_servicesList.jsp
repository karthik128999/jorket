<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/login/services" />

<c:url var="addSearch" value="/home/login/services/search" />

<c:url var="editUrl" value="/home/login/services?id=" />

<c:url var="imageUrl" value="/image?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/services/search"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 100vh">
		<h2>Service List</h2>
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
						<c:if test="${sessionScope.user.roleId == 1}">
							<th scope="col">Select
								All</th>
						</c:if>

						<th scope="col">Image</th>
						<th scope="col">Service Name</th>
						<th scope="col">City</th>							
						<th scope="col">Created By</th>						
						<th scope="col">Action</th>

					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="s_list" varStatus="u">
						<tr>
							<c:if test="${sessionScope.user.roleId == 1}">
								<td><input type="checkbox" class="case" name="ids"
									value="${s_list.id}"></td>
							</c:if>
						
							<td><img src="${imageUrl} ${s_list.id}" width="100" height="100"></td>
							<td scope="row">${s_list.serviceName}</td>
							<td scope="row">${s_list.city}</td>
							<td scope="row">${s_list.createdBy}</td>
							<c:if test="${sessionScope.user.roleId == 1}">
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
			<c:if test="${sessionScope.user.roleId == 1}">
				<input type="submit" name="operation"
					class="btn btn-sm btn-danger float-start"
					<c:if test="${listsize == 0}">disabled="disabled"</c:if>
					value="Delete">
					 
				
			</c:if>
				
				


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
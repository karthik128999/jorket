<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/login/view-profile" />

<c:url var="addSearch" value="/home/login/view-profile/search" />

<c:url var="editUrl" value="/home/login/view-employee-profile?id=" />

<c:url var="imageUrl" value="/user/image?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/view-profile/search"
	modelAttribute="form">
	<div class="container mt-4"
		style="position: relative; min-height: 100vh">
		<h2 class="text-center">Service Offered</h2>
		<hr />


		<div class="container mt-2" style="width: 50%">
			<b><%@ include file="businessMessage.jsp"%></b>
		</div>
		<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />

		<%-- <div class="row">
			<div class="col-lg-12">
				<s:bind path="userId">
					<label for="inputEmail4" class="form-label">Select Location</label>
					<sf:select class="form-control" path="${status.expression}">
						<sf:option value="" label="---Select---" />
						<sf:options itemLabel="address" itemValue="id"
							items="${employeeList}" />
					</sf:select>
					<font color="red" style="font-size: 13px"> <sf:errors
							path="${status.expression}" /></font>
				</s:bind>
			</div>
		</div> 
		<div class="container text-center mt-2">
			<div class="col">


				<input type="submit" class="btn btn-lg btn-primary" name="operation"
					value="Search"></input> 

			</div>
		</div>--%>
		<c:forEach items="${list}" var="s_list" varStatus="u">
			<div class="container mt-2">
				<div class="card bg-footer text-white">
					<div class="card-body">
						<div class="row">
							<div class="col-lg-4">
								<img src="${imageUrl} ${s_list.user.id}" class="rounded" width="155">
							</div>
							<div class="col-lg-4">
								<h4 class="mb-0 mt-0">${s_list.user.firstName}</h4>
								<span>${s_list.user.lastName}</span>
							</div>
							<div class="col-lg-4">
								<a href="${editUrl}${s_list.id}"
									class="btn btn-secondary text-white">View Profile</a>
							</div>
						</div>
					</div>
				</div>


			</div>
		</c:forEach>
	</div>
</sf:form>


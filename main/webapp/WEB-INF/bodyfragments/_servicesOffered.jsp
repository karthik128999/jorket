<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<c:url var="addUrl" value="/home/login/services-offered" />

<c:url var="addSearch" value="/home/login/services-offered/search" />

<c:url var="editUrl" value="/home/login/view-profiles?id=" />

<c:url var="imageUrl" value="/image?id=" />

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/services-offered/search"
	modelAttribute="form">

	<div class="container mt-4"
		style="position: relative; min-height: 100vh">
		<h2 class="text-center">Service Offered</h2>
		<hr />
		<div class="row">
			<div class="col-lg-12">
				<s:bind path="city">
					<label for="inputEmail4" class="form-label">Select Location</label>
					<sf:input path="${status.expression}" 
						class="form-control" value="${form.city==null?sessionScope.user.city:form.city}" />
					<font color="red" style="font-size: 13px"> <sf:errors
							path="${status.expression}" /></font>
				</s:bind>
			</div>
		</div>
		<div class="container text-center mt-2">
			<div class="col">


				<input type="submit" class="btn btn-lg btn-primary" name="operation"
					value="Search"></input>
					<input type="submit" class="btn btn-lg btn-primary" name="operation"
					value="Reset"></input>

			</div>
		</div>

		<div class="container mt-2" style="width: 50%">
			<b><%@ include file="businessMessage.jsp"%></b>
		</div>
		<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />


		<div class="row">
			<c:forEach items="${list}" var="s_list" varStatus="u">
				<div class="col-lg-3">

					<div class="card" style="width: 18rem;">
						<img src="${imageUrl} ${s_list.id}" class="card-img-top"
							alt="Not found">
						<div class="card-body">
							<h5 class="card-title text-center">${s_list.serviceName}</h5>
							<div class="container text-center">
								<a href="${editUrl}${s_list.id}&serviceName=${s_list.serviceName}&city=${s_list.city}"
									class="btn btn-success text-white">Request Service</a>
							</div>

						</div>

					</div>

				</div>
			</c:forEach>
		</div>

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

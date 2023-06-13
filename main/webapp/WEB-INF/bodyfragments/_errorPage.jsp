<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="container mt-2"
	style="position: relative; min-height: 100vh">
	<div class="card">
		<div class="card-body bg-header">
			<h5 class="card-title text-center ">Error Page</h5>
			<hr>
			<b><%@ include file="businessMessage.jsp"%></b>
			 <center><img src="${pageContext.request.contextPath}/resources/images/error.png"  alt="..." width="10%" height="10%">
			</center><br><center><a class="btn btn-primary "
				href="<c:url value="/home/login"/>">Login</a></center>
		</div>
	</div>
</div>
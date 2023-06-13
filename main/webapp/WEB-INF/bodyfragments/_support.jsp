<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<div class="card" >
  <div class="card-body bg-header">
    <h5 class="card-title text-center ">Support Page</h5>
    <hr>
     <b><%@ include file="businessMessage.jsp"%></b>
    <div class="row">
    	<div class="col-lg-6">
    		<h2 class="text-success fw-bold mt-5"><u>Need Help?</u></h2>
    		<h3>Contact No: +1 518 212 0697</h3>
    		<h3>Address: ABC Building,<br>UAlbany,NY-12222.</h3>
    		<h3>Email Id: <a href="#">teamjorket@gmail.com</a></h3>
    	</div>
    	<div class="col-lg-6">
    	<img src="${pageContext.request.contextPath}/resources/images/download.png" class="d-block w-100" alt="..." width="100%" height="100%">
    	</div>
    </div>
  </div>
</div>
</div>
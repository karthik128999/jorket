<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<div class="card" >
  <div class="card-body bg-header" >
    <h5 class="card-title text-center ">Service Page</h5>
    <hr>
     <b><%@ include file="businessMessage.jsp"%></b>
    <sf:form method="post" action="${pageContext.request.contextPath}/home/login/services" enctype="multipart/form-data" modelAttribute="form"><br/>
 	<sf:hidden path="id"/>
    <div class="row">
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="serviceName">
						<label for="inputEmail4" class="form-label">Service Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Service Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="city">
						<label for="inputEmail4" class="form-label">City Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter City Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    <div class="row">	
    	<div class="col-lg-12">
    	<div class="form-outline ">
            <s:bind path="serviceImage">
						<label for="inputEmail4" class="form-label">Image</label>
						<sf:input type="file" path="${status.expression}"
							placeholder="Enter Last Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    
    <div class="form-check d-flex justify-content-center mb-4 mt-2">
             <input type="submit" name="operation"
						class="btn btn-primary btn-lg pull-right" value="Save"> &nbsp; <input
						type="submit" name="operation" class="btn btn-primary btn-lg pull-right"
						value="Reset">
            </div>
    </sf:form>
  </div>
</div>
</div>
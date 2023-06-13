<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<div class="card" >
  <div class="card-body bg-header">
    <h5 class="card-title text-center ">Login</h5>
    <hr>
     <b><%@ include file="businessMessage.jsp"%></b>
    <sf:form method="post" action="${pageContext.request.contextPath}/home/login" modelAttribute="form"><br/>
    <div class="row">
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="login">
						<label for="inputEmail4" class="form-label">Login</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Login Id" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-6">
    	<div class="form-outline ">
            <s:bind path="password">
						<label for="inputEmail4" class="form-label">Password</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    
    <div class="form-check d-flex justify-content-center mb-4 mt-2">
             <input type="submit" name="operation"
						class="btn btn-primary btn-lg pull-right" value="Login"> &nbsp; <input
						type="submit" name="operation" class="btn btn-primary btn-lg pull-right"
						value="Reset">
            </div>
            <a href="<c:url value="/home/login/forgetPassword"/>"
										>Forgot Password?</a>
    </sf:form>
  </div>
</div>
</div>
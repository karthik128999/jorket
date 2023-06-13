<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%> 
<c:url var="imageUrl" value="/user/image?id=" />   
<div class="container mt-2" style="position: relative; min-height: 100vh">
<div class="card" >
  <div class="card-body bg-header">
    <h5 class="card-title text-center ">My Profile</h5>
    <hr>
     <b><%@ include file="businessMessage.jsp"%></b>
    <sf:form method="post" action="${pageContext.request.contextPath}/home/login/myprofile" enctype="multipart/form-data" modelAttribute="form"><br/>
    <div class="row text-center">
				<div class="form-outline mt-2 ">
							<s:bind path="image">
								<c:if test="${sessionScope.user.roleId==1 }">
								<img src="${pageContext.request.contextPath}/resources/images/icon.png" class="rounded" width="155" >
								</c:if>
								<c:if test="${sessionScope.user.roleId==2 || sessionScope.user.roleId==3}">
								<img src="${imageUrl} ${sessionScope.user.id}" class="rounded" width="155" >
								</c:if>
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
				</div>
    <div class="row">
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="firstName">
						<label for="inputEmail4" class="form-label">First Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter First Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-6">
    	<div class="form-outline ">
            <s:bind path="lastName">
						<label for="inputEmail4" class="form-label">Last Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Last Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    <!-- IInd row starts -->
    <div class="row">
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="login">
						<label for="inputEmail4" class="form-label">Login</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Email Id" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-6">
    	<div class="form-outline ">
            <s:bind path="mobileNo">
						<label for="inputEmail4" class="form-label">Contact No</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Contact No." class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    <!-- IInd row Ends -->
    <div class="row">
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="password">
						<label for="inputEmail4" class="form-label">Password</label>
						<sf:input type="password" path="${status.expression}"
							 class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>              
            </div>            
    	</div>
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="gender">
						<label for="inputEmail4" class="form-label">Gender</label>
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:options   items="${gender}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	
    </div>
    <!-- IIIrd row Starts -->
        
    	
    
    <!-- Vth rows ends -->
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<div class="card" >
  <div class="card-body bg-header">
    <h5 class="card-title text-center ">Customer Sign Up</h5>
    <hr>
     <b><%@ include file="businessMessage.jsp"%></b>
    <sf:form method="post" action="${pageContext.request.contextPath}/home/employer-register"
    enctype="multipart/form-data" modelAttribute="form"><br/>
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
            <s:bind path="contactNo">
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
    <!-- IIIrd row Starts -->
        <div class="row">
    	<div class="col-lg-4">
    		<div class="form-outline ">
            <s:bind path="dob">
						<label for="inputEmail4" class="form-label">DOB</label>
						<sf:input path="${status.expression}"
							placeholder="Enter DOB in DD/MM/YYYY format" id="datepicker" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-4">
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
    	<div class="col-lg-4">
    	<div class="form-outline ">
            <s:bind path="zipCode">
						<label for="inputEmail4" class="form-label">Zip Code</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Zip Code"  class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    <!-- IIrd row Ends -->
    <div class="row">
    	<div class="col-lg-4">
    		<div class="form-outline ">
            <s:bind path="address">
						<label for="inputEmail4" class="form-label">Address</label>
						<sf:textarea path="${status.expression}"
							placeholder="Enter address"  class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-4">
    	<div class="form-outline ">
            <s:bind path="city">
						<label for="inputEmail4" class="form-label">City</label>
						<sf:input path="${status.expression}"
							placeholder="Enter City"  class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-4">
    	<div class="form-outline ">
            <s:bind path="state">
						<label for="inputEmail4" class="form-label">State</label>
						<sf:input path="${status.expression}"
							placeholder="Enter State"  class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    </div>
    <!-- IVth row starts -->
     <div class="row">
    	<div class="col-lg-6">
    		<div class="form-outline ">
            <s:bind path="password">
						<label for="inputEmail4" class="form-label">Password</label>
						<sf:input type="password" path="${status.expression}" id="passInput"
						  class="form-control" />
						  <input type="checkbox" class="form-check-input" onclick="myFun()"> Show Password
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	<div class="col-lg-6">
    	<div class="form-outline ">
            <s:bind path="confirmPassword">
						<label for="inputEmail4" class="form-label">Confirm Password</label>
						<sf:input type="password" path="${status.expression}"
						  class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
    	</div>
    	
    	
    </div>
    <div class="form-outline ">
            <s:bind path="image">
						<label for="inputEmail4" class="form-label">Image</label>
						<sf:input type="file" path="${status.expression}"
							class="form-control" required="required"/>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
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
<script>
function myFun() {
	  var x = document.getElementById("passInput");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}
</script>
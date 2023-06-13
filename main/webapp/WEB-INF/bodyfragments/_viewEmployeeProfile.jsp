<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:url var="editUrl" value="/home/login/booking?id=" />
<c:url var="imageUrl" value="/user/image?id=" />
<div class="container mt-2"
	style="position: relative; min-height: 100vh">
	<div class="card">
		<div class="card-body bg-header">
			<h5 class="card-title text-center ">View Profile</h5>
			<hr>
			<b><%@ include file="businessMessage.jsp"%></b>
			<sf:form method="post"
				action="${pageContext.request.contextPath}/home/login/booking-employee"
				enctype="multipart/form-data" modelAttribute="form">
				<br />
				<sf:hidden path="id"/>
				<div class="row text-center">
				<div class="form-outline mt-2 ">
							<s:bind path="image">
								
								<img src="${imageUrl} ${form.user.id}" class="rounded" width="155" >
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
				</div>
				<div class="row">
					
					<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="serviceName">
								<label for="inputEmail4" class="form-label">Service</label>
								<sf:input path="${status.expression}" value="${form.service.serviceName }" class="form-control" readonly="true"/>
								<font color="red" style="font-size: 13px"> <sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="user.firstName">
								<label for="inputEmail4" class="form-label">Name</label>
								<sf:input path="${status.expression}" value="${form.user.firstName }" class="form-control" readonly="true"/>
								<font color="red" style="font-size: 13px"> <sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>
				</div>
				<!-- IInd row starts -->
				<div class="row">
					<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="costPerHour">
								<label for="inputEmail4" class="form-label">Cost Per Hour</label>
								<sf:input path="${status.expression}"
									placeholder="Enter Cost Per Hour" class="form-control" readonly="true" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-outline mt-2 ">
							<s:bind path="dateOfBooking">
								<label for="inputEmail4" class="form-label">Date Of Availability</label>
								<sf:input path="${status.expression}"
									 class="form-control" readonly="true" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>
				</div>
				<!-- IInd row Ends -->
				
				<!-- IVth row starts -->
				<div class="row">
					<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="timeSlotsStart">
								<label for="inputEmail4" class="form-label">Time Slot Starts</label>
								<sf:input type="time" path="${status.expression}" class="form-control" readonly="true"/>
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
						</div>
						<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="timeSlotsEnds">
								<label for="inputEmail4" class="form-label">Time Slots Ends</label>
								<sf:input type="time" path="${status.expression}" class="form-control" readonly="true" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div></div>
					<!-- Testing starts -->
					 <div class="row">
					<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="dateOfAvailability">
								<label for="inputEmail4" class="form-label">Booking Request Date</label>
								<sf:input id="datepicker" path="${status.expression}" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
						</div>
						<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="timingRequest">
								<label for="inputEmail4" class="form-label">Timing</label>
								<sf:input type="time" path="${status.expression}" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>
				</div>	 
					<!-- Testing ends -->
<a class=" btn btn-success mt-2 text-white fw-bold "
										href="<c:url value="/home/login/feedback/search"/>?userId=${form.user.id}">View
											Feedback</a>

				</div>

				<!-- Vth rows ends -->
				<div class="form-check d-flex justify-content-center mb-4 mt-2">
				 <%-- <a href="${editUrl}${form.id}&status=requestbooking&serviceName=${form.serviceName}&dob=${form.dateOfBooking}" class="btn btn-primary text-white" >Go to Booking</a>
					
 --%>	 <input type="submit" name="operation"
						class="btn btn-primary btn-lg pull-right" value="Save"> 				&nbsp; 
 			
				</div>
				
			</sf:form>
			
		</div>
	</div>
	
</div>

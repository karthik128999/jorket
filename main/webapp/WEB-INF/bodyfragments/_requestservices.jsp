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
			<h5 class="card-title text-center ">Assign Services</h5>
			<hr>
			<b><%@ include file="businessMessage.jsp"%></b>
			<sf:form method="post"
				action="${pageContext.request.contextPath}/home/login/request-services"
			 modelAttribute="form">
				<br />
				<sf:hidden path="id"/>
				<div class="row">
					
					<div class="col-lg-12">
						<div class="form-outline ">
							<s:bind path="serviceName">
								<label for="inputEmail4" class="form-label">Select Service</label>
								<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:option value="${skill1}">${skill1}</sf:option>
									<sf:option value="${skill2}">${skill2}</sf:option>
									<sf:option value="${skill3}">${skill3}</sf:option>
									<sf:option value="${skill4}">${skill4}</sf:option>
									<sf:option value="${skill5}">${skill5}</sf:option>
									<%-- <sf:options itemLabel="skill_1" itemValue=""
										items="${servicesList}" /> --%>
								</sf:select>
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
									placeholder="Enter Cost Per Hour" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="dateOfBooking">
								<label for="inputEmail4" class="form-label">Choose Date</label>
								<sf:input type="text" id="datepicker" path="${status.expression}"
									placeholder="Enter Date in DD/MM/YYYY" class="form-control" required="required" />
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
								<sf:input type="time" path="${status.expression}" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
						</div>
						<div class="col-lg-6">
						<div class="form-outline ">
							<s:bind path="timeSlotsEnds">
								<label for="inputEmail4" class="form-label">Time Slots Ends</label>
								<sf:input type="time" path="${status.expression}" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>

						</div>
					</div>


				</div>

				<!-- Vth rows ends -->
				<div class="form-check d-flex justify-content-center mb-4 mt-2">
					<input type="submit" name="operation"
						class="btn btn-primary btn-lg pull-right" value="Save">
					&nbsp; <input type="submit" name="operation"
						class="btn btn-primary btn-lg pull-right" value="Reset">
				</div>
			</sf:form>
		</div>
	</div>
</div>
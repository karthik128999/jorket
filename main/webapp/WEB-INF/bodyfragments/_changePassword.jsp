<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="container mt-2"
	style="position: relative; min-height: 100vh">
	<div class="card" >
  <div class="card-body bg-header">
	<div class="row">
		<div class="col-xs-12 main-content">
			<div class="row">
				
						<h5 class="card-title text-center ">Change Password</h5>
						<hr>
					</div>

					
					
						<%@ include file="businessMessage.jsp"%>
					
					<div class="providerLinkingFeedback"></div>

					<div class="row">
						<div class="col-sm-12">

							<sf:form method="post"
								action="${pageContext.request.contextPath}/home/login/changepassword"
								modelAttribute="form">

								<div class="form-group">
									<s:bind path="oldPassword">
										<label for="inputEmail4" class="form-label">Old Password</label>
										<sf:input type="password" path="${status.expression}"
											placeholder="Enter Password" class="form-control" />
										<font color="red" style="font-size: 13px"> <sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div>

								<div class="form-group">
									<s:bind path="newPassword">
										<label for="inputEmail4" class="form-label">New Password</label>
										<sf:input type="password" path="${status.expression}"
											placeholder="Enter Password" class="form-control" />
										<font color="red" style="font-size: 13px"><sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div>
								<div class="form-group">
									<s:bind path="confirmPassword">
										<label for="inputEmail4" class="form-label">Confirm Password</label>
										<sf:input type="password" path="${status.expression}"
											placeholder="Enter Password" class="form-control" />
										<font color="red" style="font-size: 13px"><sf:errors
												path="${status.expression}" /></font>
									</s:bind>
								</div>


								<div class="text-center mt-2"></div>
								<div align="center">
									<input id="login" type="submit" name="operation" class="btn btn-primary"
										value="Save" />
										<input id="login" type="submit" name="operation" class="btn btn-primary"
										value="Reset" />
										 
								</div>
							</sf:form>

						</div>
						<div class="col-sm-5 hidden"></div>
					</div>

				</div>
				<div
					class="col-sm-6 col-md-6 col-md-offset-1 text-center imageoflogin">

					

				</div>
			</div>
		</div></div></div>
	</div>
</div>
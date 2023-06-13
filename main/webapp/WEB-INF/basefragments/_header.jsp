<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg navbar-light bg-footer">

	<a class="navbar-brand container" href="<c:url value="/home"/>"> <b
		class="text-white"><i class="fa fa-student" style="color: yellow;"></i>Jorket</b>
	</a>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav justify-content-end">

				<%-- <c:if test="${sessionScope.user != null}">
				<span class="navbar-text">
					Hello,${sessionScope.user.firstName} (${sessionScope.user.roleName }) </span>
			</c:if> --%>
				<c:if test="${sessionScope.user == null}">

				</c:if>
			</ul>

		</div>

	</div>
</nav>

<nav class="navbar navbar-expand-lg navbar-secondary bg-header">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a>
			</li>
			<c:if test="${sessionScope.user == null}">

				<li class="nav-item"><a class="nav-link "
					href="<c:url value="/home/login"/>">Login</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> SignUp </a>
					<ul class="dropdown-menu">

						<li><a class="dropdown-item"
							href="<c:url value="/home/employee-register"/>">Professional</a></li>

						<li><a class="dropdown-item"
							href="<c:url value="/home/employer-register"/>">Customer</a></li>
					</ul></li>
				<%-- <li class="nav-item"><a class="nav-link"
				href="<c:url value="/home/employee-register"/>">Employee Sign Up</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home/employer-register"/>">Employer Sign Up</a></li>
 --%>
				<li class="nav-item"><a class="nav-link "
					href="<c:url value="/aboutus"/>">About</a></li>
				<li class="nav-item"><a class="nav-link "
					href="<c:url value="/support"/>">Support</a></li>
			</c:if>

			<c:if test="${sessionScope.user != null}">
				<c:if test="${sessionScope.user.roleId == 1}">

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">Profiles</a>
						<ul class="dropdown-menu">

							<li><a class="dropdown-item"
								href="<c:url value="/home/login/users/search"/>">Professionals</a></li>

							<li><a class="dropdown-item"
								href="<c:url value="/home/login/customers/search"/>">Customers
									List</a></li>

						</ul></li>
				
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">Services</a>
						<ul class="dropdown-menu">

							<li><a class="dropdown-item"
								href="<c:url value="/home/login/services"/>">Add Services</a></li>

							<li><a class="dropdown-item"
								href="<c:url value="/home/login/services/search"/>">Services
									List</a></li>

						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">Bookings</a>
						<ul class="dropdown-menu">

							<li><a class="dropdown-item"
								href="<c:url value="/home/login/booking-history"/>">Booking
									History</a></li>
<li><a class="dropdown-item"
								href="<c:url value="/home/login/today-booking"/>">Today Bookings</a></li>
							<li><a class="dropdown-item"
								href="<c:url value="/home/login/booking-upcoming"/>">Upcoming
									Booking</a></li>

						</ul></li>
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/statistics/search"/>">View
							Statistics</a></li>



				</c:if>
				<c:if test="${sessionScope.user.roleId == 2}">



					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/request-services"/>">Assign
							Services</a></li>
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/request-services/search"/>">View
							Employees</a></li>
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/booking/search"/>">My Booking</a></li>
					

					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/feedback/search"/>">View
							Feedback</a></li>




				</c:if>
				<c:if test="${sessionScope.user.roleId == 3}">
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/services-offered/search"/>">Services
							Offered</a></li>
					<li class="nav-item "><a class="nav-link "
						href="<c:url value="/home/login/bookings/search"/>">Booking</a></li>



				</c:if>
			</c:if>
		</ul>

	</div>
	<ul class="navbar-nav justify-content-end">
		<c:if test="${sessionScope.user != null}">

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false">
					Hello,${sessionScope.user.firstName} (${sessionScope.user.roleName })
			</a>
				<ul class="dropdown-menu">

					<li><a class="dropdown-item"
						href="<c:url value="/home/login/myprofile"/>">My Profile</a></li>
					<li><a class="dropdown-item"
						href="<c:url value="/home/login/changepassword"/>">Change
							Password</a></li>

				</ul></li>


			<li class="nav-item "><a class="nav-link"
				style="padding: 6px; color: black;"
				href="<c:url value="/home/login"/>"><i class="fa fa-sign-out"></i>
					Logout</a></li>
		</c:if>
	</ul>
	<ul class="navbar-nav d-flex flex-row">
		<c:if test="${sessionScope.user == null}">


		</c:if>
	</ul>

</nav>

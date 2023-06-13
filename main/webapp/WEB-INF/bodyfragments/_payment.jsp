<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<div class="card" >
  <div class="card-body bg-header" >
    <h5 class="card-title text-center ">Booking Page</h5>
    <hr>
     <b><%@ include file="businessMessage.jsp"%></b>
    <sf:form method="post" action="${pageContext.request.contextPath}/home/login/payment" modelAttribute="form"><br/>
 
 	<c:if test="${status eq 'requestbooking'}">
 		<div class="container">
 			<div class="alert alert-danger text-center text-dark fw-bold">Wait for Professional
 			to accept the request</div>
 			<a href="/home/login/payment" >Check Payment Page</a>
 		</div>
 	</c:if>
    <c:if test="${status eq 'accept'}">
  
    <div class="container mt-5">
				<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        Payment Page
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      	<div class="form-outline mb-3">
                <input
                  type="text"
                  id="formControlLgXM8"
                  class="form-control"
                  placeholder="1234 5678 1234 5678"
                />
                <label class="form-label" for="formControlLgXM8">Card Number</label>
        </div>
        <div class="row mb-3">
                <div class="col-6">
                  <div class="form-outline">
                    <input
                      type="password"
                      id="formControlLgExpk8"
                      class="form-control"
                      placeholder="MM/YYYY"
                    />
                    <label class="form-label" for="formControlLgExpk8">Expire</label>
                  </div>
                </div>
                <div class="col-6">
                  <div class="form-outline">
                    <input
                      type="password"
                      id="formControlLgcvv8"
                      class="form-control"
                      placeholder="Cvv"
                    />
                    <label class="form-label" for="formControlLgcvv8">CVV</label>
                  </div>
                </div>
              </div>
        
      </div>
    </div>
  </div>
  </div>
			</div>
    
    <div class="form-check d-flex justify-content-center mb-4 mt-2">
             <input type="submit" name="operation"
						class="btn btn-primary btn-lg pull-right" value="Book"> 
            </div>
            </c:if>
    </sf:form>
    
  </div>
</div>
</div>
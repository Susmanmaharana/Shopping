<%@include file="../shared/flows-header.jsp"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
	<!-- column to display the personal details -->
	
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Personal Details</h4>
				</div>
				<div class="panel-body">
				<!-- code to display the personal details -->
				<div class="text-center">
				<h4>
				Name: ${registerModel.user.firstName}   ${registerModel.user.lastName}
				</h4>
				<h5>Email: ${registerModel.user.email} </h5>
				<h5>Password : ${registerModel.user.password} </h5>
				<h5>Role: ${registerModel.user.role} </h5>
				<h5>Contact Number:  ${registerModel.user.contactNumber} </h5>
				</div>
				
				
				
				</div>
				<div class="panel-footer">
				<!-- anchor to move to the edit of personal details -->
				<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
				</div>
				</div>
		</div>
				<!-- column to display the Address details -->
				<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>
				<div class="panel-body">
				<!-- code to display the Communication Address -->
				<div class="text-center">
				
				<h4>Address1:  ${registerModel.billing.addressLineOne}</h4>
				<h4>Address2: ${registerModel.billing.addressLineTwo}</h4>
				<h4>City:  ${registerModel.billing.city}</h4>
				<h4>State:  ${registerModel.billing.state}</h4>
				<h4>Country:  ${registerModel.billing.country}</h4>
				<h4>PostalCode:  ${registerModel.billing.postalCode}</h4>
				
				</div>
				</div>
				<div class="panel-footer">
				<!-- anchor to move to the edit of address -->
				<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
				</div>
				</div>
		</div>
		</div>
		<!-- to provice confirm button -->
		<div class="row">
		<div class="col-sm-4 clo-sm-offset-4">
		<div class="text-center">
		<!-- anchor to move to the success page -->
		<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary">Confirm</a>
		</div>
		</div>
		</div>
		
		</div>
				
<%@include file="../shared/flows-footer.jsp"%>

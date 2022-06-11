<jsp:include page="aheader.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-5 mx-auto mt-2">
			<div class="card shadow">
				<div class="card-header text-center">
					<h5>Book Appointment</h5>
				</div>
				<div class="card-body">
					<div class="form-group form-row">
						<label class="col-sm-4">Diagnostic Service</label>
						<div class="col-sm-8">${ap.servicename }</div>
					</div>
					<div class="form-group form-row">
						<label class="col-sm-4">Doctor</label>
						<div class="col-sm-8">${ap.docname }</div>
					</div>
					<div class="form-group form-row">
						<label class="col-sm-4">Date and Time</label>
						<div class="col-sm-8">${ap.date } ${ap.time }</div>
					</div>

					<div class="form-group form-row">
						<label class="col-sm-4">Remarks</label>
						<div class="col-sm-8">${ap.remarks }</div>
					</div>
					<div class="form-group form-row">
						<label class="col-sm-4">Medical Conditions</label>
						<div class="col-sm-8">${ap.conditions }</div>
					</div>
					<div class="form-group form-row">
						<label class="col-sm-4">Status</label>
						<div class="col-sm-8">${ap.status }</div>
					</div>
					
					<c:if test="${sessionScope.role =='Doctor' }">
						<a href="aptstatus?status=Confirmed&id=${ap.apptid }" class="btn btn-success btn-sm">Confirm</a>
						<a href="aptstatus?status=Rejected&id=${ap.apptid }" class="btn btn-danger btn-sm">Reject</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
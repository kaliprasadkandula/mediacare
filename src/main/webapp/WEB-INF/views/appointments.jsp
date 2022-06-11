<jsp:include page="aheader.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="card shadow">
	<div class="card-body">
		<h4 class="p-2" style="border-bottom:2px solid green;">Appointment List</h4>
		<c:if test="${msg ne null }">
			<div class="alert text-success font-weight-bold">${msg }</div>
		</c:if>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>Appt Id</th>
					<th>Doctor Name</th>
					<th>Patient Name</th>
					<th>Date</th>
					<th>Time</th>
					<th>Service Name</th>
					<th>Status</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${list }">
					<tr>
						<td>${u.apptid }</td>
						<td>Dr. ${u.docname }</td>
						<td>${u.patname }</td>
						<td>${u.date }</td>
						<td>${u.time }</td>
						<td>${u.servicename }</td>
						<td>${u.status }</td>
						<td><a href="/aptdetails?id=${u.apptid }" class="btn btn-primary btn-sm">Details</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>
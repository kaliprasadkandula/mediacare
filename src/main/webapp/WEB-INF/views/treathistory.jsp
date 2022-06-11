<jsp:include page="aheader.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="card shadow">
	<div class="card-body">
		<h4 class="p-2" style="border-bottom:2px solid green;">Treatment History</h4>
		<c:if test="${msg ne null }">
		<div class="alert text-success font-weight-bold">${msg }</div>
		</c:if>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>Id</th>
					<th>Patient Name</th>
					<th>Doctor Name</th>
					<th>Symptoms</th>
					<th>Diagnosis</th>
					<th>Diet</th>
					<c:if test="${sessionScope.role =='Doctor' }">
					<th>Action</th>
					</c:if>
					<th>View</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${list }">
					<tr>
						<td>${u.id }</td>
						<td>${u.patname }</td>
						<td>${u.docname }</td>
						<td>${u.symptoms }</td>
						<td>${u.diagnosis }</td>
						<td>${u.diet }</td>
						<c:if test="${sessionScope.role =='Doctor' }">
							<td>
								<a href="/edittreatment?tid=${u.id }" class="btn btn-primary btn-sm">Edit Treatment</a>
							</td>
						</c:if>
						<td>
						<a href="/treatdetails?tid=${u.id }" class="btn btn-primary btn-sm">View Details</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>
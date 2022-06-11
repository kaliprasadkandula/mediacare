<jsp:include page="aheader.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="card shadow">
	<div class="card-body">
		<h4 class="p-2" style="border-bottom:2px solid green;">Test List</h4>
		<c:if test="${msg ne null }">
			<div class="alert text-success font-weight-bold">${msg }</div>
		</c:if>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>Test Id</th>
					<th>Test Name</th>
					<th>Patient Id</th>
					<th>Test Date</th>
					<th>Remarks</th>
					<th>Result</th>
					<th>Status</th>
					<c:if test="${sessionScope.role =='Admin' }">
					<th>Action</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${tests }">
					<tr>
						<td>${u.id }</td>
						<td>${u.testname }</td>
						<td>${u.patid }</td>
						<td>${u.tdate }</td>
						<td>${u.remarks }</td>
						<td>${u.result }</td>
						<td>${u.status }</td>
						<c:if test="${sessionScope.role =='Admin' }">
						<td><a href="/updatetest?id=${u.id }" class="btn btn-primary btn-sm">Update</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>
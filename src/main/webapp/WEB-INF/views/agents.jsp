<jsp:include page="aheader.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="card shadow">
	<div class="card-body">
		<a href="/createagent" class="btn btn-primary btn-sm float-right mt-1">Add Agent</a>
		<h4 class="p-2" style="border-bottom:2px solid green;">Agents List</h4>
		<c:if test="${msg ne null }">
		<div class="alert text-success font-weight-bold">${msg }</div>
		</c:if>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>Agent Id</th>
					<th>Agent Name</th>
					<th>Password</th>
					<th>Date of Birth</th>
					<th>Email</th>
					<th>Gender</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${users }">
					<tr>
						<td>${u.agentid }</td>
						<td>${u.name }</td>
						<td>${u.pwd }</td>
						<td>${u.dob }</td>
						<td>${u.email }</td>
						<td>${u.gender }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>
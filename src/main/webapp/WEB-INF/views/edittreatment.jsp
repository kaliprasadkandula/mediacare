<jsp:include page="aheader.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 mx-auto mt-2">
				<div class="card shadow">
					<div class="card-header text-center">
						<h5>Add Treatment</h5>
					</div>
					<div class="card-body">
						<form method="post">
						<div class="form-row">
							<div class="col-sm-5">
							<input type="hidden" name="docid" value="${sessionScope.userid }">
							<input type="hidden" name="docname" value="${sessionScope.uname }">
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Patient Id</label>
								<div class="col-sm-8">
									<input type="text" value="${t.patid }" readonly name="patid" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Patient Name</label>
								<div class="col-sm-8">
									<input type="text" value="${t.patname }" readonly name="patname" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Symptoms</label>
								<div class="col-sm-8">
									<input type="text" value="${t.symptoms }" name="symptoms" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Diagnosis</label>
								<div class="col-sm-8">
									<input type="text" value="${t.diagnosis }" name="diagnosis" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Recommendations</label>
								<div class="col-sm-8">
									<input type="text" value="${t.recommendations }" name="recommendations" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Diet</label>
								<div class="col-sm-8">
									<input type="text" value="${t.diet }" name="diet" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Referal</label>
								<div class="col-sm-8">
									<input type="text" value="${t.referal }" name="referal" class="form-control form-control-sm">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-4 col-form-label">Tests Required</label>
								<div class="col-sm-8">
									<input type="text" value="${t.tests }" name="tests" class="form-control form-control-sm">
								</div>
							</div>
							</div>
							<div class="col-sm-5">
							<h5 class="p-2 border-bottom">Prescriptions</h5>
							<table class="table table-sm">
							<thead>
							<tr>
							<th>Medicine</th>
							<th style="width:100px;">Qty</th>
							<th style="width:120px;">Times/Day</th>
							<th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="p" items="${plist }">
							<tr>
								<td>
									${p.mname }
								</td>
								<td>
									${p.qty }
									${p.unit }
								</td>
								<td>
									${p.times }
								</td>
							</tr>
							</c:forEach>
							</table>
							</tbody>
							</div>
						</div>
														
							<input type="submit" value="Update Request" class="btn btn-primary float-right px-4">
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
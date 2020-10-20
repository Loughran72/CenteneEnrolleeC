<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
	<head>
		<title>Centene Enrollee Test</title>
	</head>
	
	<body>
		<div class="container">
		<a href="/">Home</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<td scope="col">ID</td>
					<td scope="col">Name</td>
					<td scope="col">Activation Status</td>
					<td scope="col">DOB</td>
					<td scope="col">Phone Number</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${enr}" var="enr">
				<tr>
					<td>${enr.id}</td>
					<td>${enr.name}</td>
					<td>${enr.activationStatus}</td>
					<td>${enr.dob}</td>
					<td>${enr.phoneNum}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</body>
</HTML>
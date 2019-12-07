<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap style -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Find a Food License By License Number</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<form action="findfoodlicensesbylicensenumber" method="post">
		<div class="jumbotron">
			<h1>Search for a Food License by License Number</h1>
		</div>
			<p>
				<label for="licensenumber">LicenseNumber</label>
				<input id="licensenumber" name="licensenumber" value="${fn:escapeXml(param.licensenumber)}">
			</p>
			<p>
				<input type="submit" class="btn btn-lg btn-primary">
			</p>
			<br/><br/><br/>
			<div class="alert alert-info" role="alert">
				<span id="successMessage"><b>${messages.success}</b></span>
			</div>

		</form>
		<br/>
		<div id="foodLicenseCreate"><a href="foodlicensescreate">Create Food License</a></div>
		<br/>
		<h1>Matching Food Licenses</h1>
        <table class="table table-striped">
            <tr>
                <th>FoodLicenseKey</th>
                <th>LicenseFK</th>
                <th>LicenseNumber</th>
            </tr>
            <c:forEach items="${foodLicenses}" var="foodLicense" >
                <tr>
                    <td><c:out value="${foodLicense.getFoodLicenseKey()}" /></td>
                    <td><c:out value="${foodLicense.getLicenseFK()}" /></td>
                    <td><c:out value="${foodLicense.getLicenseNumber()}" /></td>
                    <td><a href="foodlicensesdelete?foodlicensekey=<c:out value="${foodLicense.getFoodLicenseKey()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
    </div>
	<!-- Bootstrap scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
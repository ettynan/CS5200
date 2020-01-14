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
<title>Find a License By License Status</title>
</head>
<body>
	<form action="findlicensesbylicensestatus" method="post">
	<div class="jumbotron">
		<h1>Search for a License by License Status</h1>
	</div>
		<p>
			<label for="licensestatus">LicenseStatus (Active, Inactive)</label>
			<input id="licensestatus" name="licensestatus" value="${fn:escapeXml(param.licensestatus)}">
		</p>
		<p>
			<input type="submit">
		</p>
		<br/><br/><br/>
		<div class="alert alert-info" role="alert">
			<span id="successMessage"><b>${messages.success}</b></span>
		</div>

	</form>
	<br/>
    <div><a href="findestablishmentsbyname">Back to Find Establishments by Name</a></div>
	<div id="licenseCreate"><a href="licensescreate">Create License</a></div>
	<br/>
	<h3>Matching Licenses</h3>
       <table class="table table-striped">
           <tr>
               <th>LicenseKey</th>
               <th>LicenseNumber</th>
               <th>LicenseIssue</th>
               <th>LicenseExpiration</th>
               <th>LicenseStatus</th>
               <th>LicenseCategoryFK</th>
               <th>Delete License</th>
               <th>Update LicenseNumber</th>
               <th>Update LicenseExpiration</th>
               <th>Update LicenseStatus</th>
           </tr>
           <c:forEach items="${licenses}" var="license" >
               <tr>
                   <td><c:out value="${license.getLicenseKey()}" /></td>
                   <td><c:out value="${license.getLicenseNumber()}" /></td>
                   <td><fmt:formatDate value="${license.getLicenseIssue()}" pattern="yyyy-MM-dd"/></td>
                   <td><fmt:formatDate value="${license.getLicenseExpiration()}" pattern="yyyy-MM-dd"/></td>
                   <td><c:out value="${license.getLicenseStatus()}" /></td>
                   <td><c:out value="${license.getLicenseCategoryFK()}" /></td>
                   <td><a href="licensesdelete?licensekey=<c:out value="${license.getLicenseKey()}"/>">Delete</a></td>
                   <td><a href="licensesupdatelicensenumber?licensekey=<c:out value="${license.getLicenseKey()}"/>">Update Number</a></td>
                   <td><a href="licensesupdatelicenseexpiration?licensekey=<c:out value="${license.getLicenseKey()}"/>">Update Expiration</a></td>
                   <td><a href="licensesupdatelicensestatus?licensekey=<c:out value="${license.getLicenseKey()}"/>">Update Status</a></td>
               </tr>
           </c:forEach>
      </table>
	<!-- Bootstrap scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
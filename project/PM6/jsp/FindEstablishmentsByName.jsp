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
<title>Find an Establishment</title>
</head>
<body>
	<form action="findestablishmentsbyname" method="post">
	<div class="jumbotron">
		<h1>Search for an Establishment by its Name</h1>
	</div>
		<p>
			<label for="estabname">EstablishmentName</label>
			<input id="estabname" name="estabname" value="${fn:escapeXml(param.estabname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<div class="alert alert-info" role="alert">
				<span id="successMessage"><b>${messages.success}</b></span>
			</div>
		</p>
	</form>
	<br/>
	<div id="EstablishmentsCreate"><a href="establishmentscreate">Create an Establishment</a></div>
	<br/>
	<h3>Matching Establishments</h3>
        <table id="EstabTable" class="table table-striped">
            <tr>
                <th>EstablishmentKey</th>
                <th>EstablishmentName</th>
                <th>EstablishmentOwner</th>
                <th>LicenseFK</th>
                <th>Delete Establishment</th>
            </tr>
            <tr>
                <td>${establishment.getEstablishmentKey()}</td>
                <td>${establishment.getEstablishmentName()}</td>
                <td>${establishment.getEstablishmentOwner()}</td>
                <td>${establishment.getLicenseFK()}</td>
                <td><a href="establishmentsdelete?estname=<c:out value="${establishment.getEstablishmentName()}"/>">Delete</a></td>
            </tr>
       </table>
	<h3> Find Other Establishment Elements</h3>
	<p><a href="findfoodestablishmentsbyname">Find Food Establishments by Name</a></p>
	<p><a href="findliquorestablishmentsbyestabkey">Find Liquor Establishments by Establishment Key</a></p>
	<p><a href="findaddressesbykey">Find Establishment Addresses by Key</a></p>
	<p><a href="findlicensesbylicensenumber">Find License by License Number</a></p>
	<p><a href="findfoodlicensesbylicensenumber">Find Food License by License Number</a></p>
	<p><a href="findliquorlicensesbylicensenumber">Find Liquor License by License Number</a></p>
	<p><a href="findlicensesbylicensecategory">Find Licenses by License Category</a></p>
	<p><a href="findlicensesbylicensestatus">Find Licenses by License Status</a></p>
	<p><a href="findlicensecategories">Find License Categories</a></p>
	<p><a href="findweatherbyweatherdate">Find Weather by Weather Date</a></p>
	<p><a href="findweatherbyweatherevents">Find Weather by Weather Events</a></p>
	<p><a href="findinspectionbyinspectionkey">Find Inspections by Inspection Key</a></p>
	<p><a href="findinspectionsbyestname">Find Inspections by Establishment Name</a></p>
	<p><a href="findinspectionsbyfoodestablishmentkey">Find Inspections by Food Establishment Key</a></p>
	<p><a href="findinspectionsbyinspectiondate">Find Inspections by Inspection Date</a></p>
	<p><a href="findinspectionsbyinspectionresult">Find Inspections by Inspection Result </a></p>
	<p><a href="findviolationbyviolationkey">Find Violation by Violation Key</a></p>
	<p><a href="findviolationsbyfoodestablishmentkey">Find Violations by Food Establishment Key</a></p>
	<p><a href="findviolationsbyinspectionkey">Find Violations by Inspection Key</a></p>
	<p><a href="findviolationsbycode">Find Violations by Code</a></p>
	<p><a href="findviolationsbyviolationlevel">Find Violations by Level</a></p>
	<p><a href="findviolationsbyestname">Find Violations by Establishment Name</a></p>
	<p><a href="findviolationsbyviolationdate">Find Violations by Violation Date</a></p>
	<p><a href="findviolationsbyviolationstatus">Find Violations by Violation Status</a></p>
	
       <!-- Bootstrap scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>

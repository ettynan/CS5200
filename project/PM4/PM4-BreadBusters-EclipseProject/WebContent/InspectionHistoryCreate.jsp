<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Create an Inspection </title>
</head>
<body>
	<form action="inspectionhistorycreate" method="post">
		<div class= "jumbatron">
		<h1>Create an Inspection</h1>
		</div>
		<p>
			<label for="establishmentname">EstablishmentName</label>
			<input id="establishmentname" name="establishmentname" value="">
		</p>
		<p>
			<label for="inspectiondate">InspectionDate (yyyy-mm-dd)</label>
			<input id="inspectiondate" name="inspectiondate" value="">
		</p>
		<p>
			<label for="inspectionresult">InspectionResult</label>
			<input id="inspectionresult" name="inspectionresult" value="">
		</p>
		<p>
			<label for="foodestablishmentFK">FoodEstablishmentFK</label>
			<input id="foodestablishmentFK" name="foodestablishmentFK" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<!-- Bootstrap -->
	<!-- jQuery (necessary for Bootstrap's Javascript plugins)-->	
	<script src="https://ajax.googleapis.com/ajax/lib/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below) or include individual files as needed-->
	<script src="js/bootstrap.min.js"></script>
</body>
</html> 
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
<title>Create Violation</title>
</head>
<body>
	<form action="violationhistorycreate" method="post">
		<div class= "jumbatron">
		<h1>Create a Violation</h1>
		</div>
		<div class="form-group form-inline"">
			<label for="establishmentname">EstablishmentName</label>
			<input id="establishmentname" class="form-control mx-sm-3" name="establishmentname" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="violationcode">ViolationCode</label>
			<input id="violationcode" class="form-control mx-sm-3" name="violationcode" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="violationlevel">ViolationLevel</label>
			<input id="violationlevel" class="form-control mx-sm-3" name="violationlevel" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="violationdescription">ViolationDescription</label>
			<input id="violationdescription" class="form-control mx-sm-3" name="violationdescription" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="violationdate">ViolationDate (yyyy-mm-dd)</label>
			<input id="violationdate" class="form-control mx-sm-3" name="violationdate" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="violationstatus">ViolationStatus</label>
			<input id="violationstatus" class="form-control mx-sm-3" name="violationstatus" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="violationcomments">ViolationComments</label>
			<input id="violationcomments" class="form-control mx-sm-3" name="violationcomments" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="foodestablishmentFK">FoodEstablishmentFK</label>
			<input id="foodestablishmentFK" class="form-control mx-sm-3" name="foodestablishmentFK" value="">
		</div>
		<div class="form-group form-inline"">
			<label for="inspectionhistoryFK">InspectionHistoryFK</label>
			<input id="inspectionhistoryFK" class="form-control mx-sm-3" name="inspectionhistoryFK" value="">
		</div>
		
		  <button type="submit" class="btn btn-primary">Submit</button>
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
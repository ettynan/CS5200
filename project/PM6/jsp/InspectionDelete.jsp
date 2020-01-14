<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap style -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Delete an Inspection</title>
	<div class="jumbotron">
	<h1>Delete an Inspection</h1>
	</div>
</head>
<body>
	<h3>${messages.title}</h3>
	<form action="inspectiondelete" method="post">
		<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="inspectionhistorykey">InspectionHistoryKey</label>
			<input id="inspectionhistorykey" name="inspectionhistorykey" value="${fn:escapeXml(param.inspectionhistorykey)}">
		</div>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	<div><a href="findestablishmentsbyname">Back Home (Find Establishments by Name)</a></div>
	<div><a href="inspectionhistorycreate">Create Inspection</a></div>
	<div><a href="findinspectionsbyestname">Find Inspections by Establishment Name</a></div>
	<div><a href="findinspectionbyinspectionkey">Find Inspection by Inspection Key</a></div>
	<div><a href="findinspectionsbyfoodestablishmentkey">Find Inspections by Food Establishment Key</a></div>
	<div><a href="findinspectionsbyinspectiondate">Find Inspections by Inspection Date</a></div>
	<div><a href="findinspectionsbyinspectionresult">Find Inspections by Inspection Result </a></div>
	<div><a href="inspectionupdate">Update Inspection Result</a></div>
	
</body>
</html>

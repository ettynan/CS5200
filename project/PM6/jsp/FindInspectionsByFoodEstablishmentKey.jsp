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
<title>Find Inspections</title>
</head>
<body>
	<form action="findinspectionsbyfoodestablishmentkey" method="post">
	<div class="jumbotron">
		<h1>Search for Inspections by the Food Establishment Key</h1>
	</div>
		<p>
			<label for="fekey">Food Establishment Key</label>
			<input id="fekey" name="fekey" value="${fn:escapeXml(param.fekey)}">
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
	<div><a href="findestablishmentsbyname">Back Home (Find Establishments by Name)</a></div>
	<div id="InspectionHistoryCreate"><a href="inspectionhistorycreate">Create an Inspection</a></div>
	<div id="FindInspectionsByEstName"><a href="findinspectionsbyestname">Find an Inspection</a></div>
	<div id="ViolationHistoryCreate"><a href="violationhistorycreate">Create a Violation</a></div>
	<div id="FindViolationsByEstName"><a href="findviolationsbyestname">Find a Violation</a></div>
	<br/>
	<h3>Matching Inspection</h3>
         <table class="table table-striped">
           <thead><tr>
                <th>InspectionHistoryKey</th>
                <th>EstablishmentName</th>
                <th>InspectionDate</th>
                <th>InspectionResult</th>
                <th>FoodEstablishmentFK</th>
                <th>Update InspectionResult</th>
                <th>Delete Inspection</th>
            </tr></thead>
            <c:forEach items="${inspectionHistory}" var="inspectionHistory" >
                <tbody><tr>
                    <td><c:out value="${inspectionHistory.getInspectionHistoryKey()}" /></td>
                    <td><c:out value="${inspectionHistory.getEstablishmentName()}" /></td>
                    <td><fmt:formatDate value="${inspectionHistory.getInspectionDate()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${inspectionHistory.getInspectionResult()}" /></td>   
                    <td><c:out value="${inspectionHistory.getFoodEstablishment().getFoodEstablishmentKey()}" /></td>   
                    <td><a href="inspectionupdate?inspectionresult=<c:out value="${inspectionHistory.getInspectionResult()}"/>">Update</a></td>                   
                    <td><a href="inspectiondelete?inspectionhistorykey=<c:out value="${inspectionHistory.getInspectionHistoryKey()}"/>">Delete</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
       <!-- Bootstrap scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>

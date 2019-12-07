<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find Inspections</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<form action="findinspectionsbyestname" method="post">
			<div class= "jumbatron">
			<h1>Search for a Inspections by EstablishmentName</h1>
			</div>
			<p>
				<label for="establishmentname">Establishment Name</label>
				<input id="establishmentname" name="establishmentname" value="${fn:escapeXml(param.establishmentname)}">
			</p>
			<p>
				<input type="submit" class= "btn btn-lg btn-primary">
				<br/><br/><br/>
			</p>
		</form>
	<br/>
 	<h3><div id="inspectionHistoryCreate"><a href="inspectionhistorycreate">Create Inspection</a></div></h3>
 	<h3><div id="findViolationsByEstName"><a href="findviolationsbyestname">Find Violation</a></div></h3>
 	<h3><div id="violationHistoryCreate"><a href="violationhistorycreate">Create Violation</a></div></h3>
 	
 	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h3>Matching Inspections</h3>
        <table class="table table-striped">
           <thead><tr>
                <th>InspectionHistoryKey</th>
                <th>EstablishmentName</th>
                <th>InspectionDate</th>
                <th>InspectionResult</th>
                <th>FoodEstablishmentFK</th>
                <th>Delete Inspection</th>
                <th>Update InspectionResult</th>
            </tr></thead>
            <c:forEach items="${inspectionHistory}" var="inspectionHistory" >
                <tbody><tr>
                    <td><c:out value="${inspectionHistory.getInspectionHistoryKey()}" /></td>
                    <td><c:out value="${inspectionHistory.getEstablishmentName()}" /></td>
                    <td><fmt:formatDate value="${inspectionHistory.getInspectionDate()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${inspectionHistory.getInspectionResult()}" /></td>   
                    <td><c:out value="${inspectionHistory.getFoodEstablishment().getFoodEstablishmentKey()}" /></td>   
                    <td><a href="inspectiondelete?inspectionhistorykey=<c:out value="${inspectionHistory.getInspectionHistoryKey()}"/>">Delete</a></td>
                    <td><a href="inspectionupdate?inspectionresult=<c:out value="${inspectionHistory.getInspectionResult()}"/>">Update</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
	</div>
	
	<!-- Bootstrap -->
	<!-- jQuery (necessary for Bootstrap's Javascript plugins)-->	
	<script src="https://ajax.googleapis.com/ajax/lib/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below) or include individual files as needed-->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
 
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
<title>Find Violations</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<form action="findviolationsbylevel" method="post">
			<div class= "jumbatron">
			<h1>Search for a Violations by Violation Level</h1>
			</div>
			<p>
				<label for="violationlevel">Violation Level</label>
				<input id="violationlevel" name="violationlevel" value="${fn:escapeXml(param.violationlevel)}">
			</p>
			<p>			
				<input type="submit" class= "btn btn-primary">
				<br/><br/><br/>
			</p>
		</form>
	<br/>
 	<h2><div id="violationHistoryCreate"><a href="violationhistorycreate">Create Violation</a></div></h2>
 	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h3>Matching Violations</h3>
        <table class="table table-striped">
           <thead><tr>
                <th>ViolationHistoryKey</th>
                <th>EstablishmentName</th>
                <th>ViolationCode</th>
                <th>ViolationLevel</th>
                <th>ViolationDescription</th>
                <th>ViolationDate</th>
                <th>ViolationStatus</th>
                <th>ViolationComments</th>
                <th>FoodEstablishmentFK</th>
                <th>Delete Violation</th>
                <th>Update Comments</th>
            </tr></thead>
            <c:forEach items="${violationHistory}" var="violationHistory" >
                <tbody><tr>
                    <td><c:out value="${violationHistory.getViolationHistoryKey()}" /></td>
                    <td><c:out value="${violationHistory.getEstablishmentName()}" /></td>
                    <td><c:out value="${violationHistory.getViolationCode()}" /></td>
                    <td><c:out value="${violationHistory.getViolationLevel()}" /></td>                 
                    <td><c:out value="${violationHistory.getViolationDescription()}" /></td>                                    
                    <td><fmt:formatDate value="${violationHistory.getViolationDate()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${violationHistory.getViolationStatus()}" /></td>   
                    <td><c:out value="${violationHistory.getViolationComments()}" /></td>                       
                    <td><c:out value="${violationHistory.getFoodEstablishment().getFoodEstablishmentKey()}" /></td>   
                    
					<td><a href="violationdelete?violationhistorykey=<c:out value="${violationHistory.getViolationHistoryKey()}"/>">Delete</a></td>
                    <td><a href="violationupdate?inspectionhistorykey=<c:out value="${violationHistory.getInspectionHistory().getInspectionHistoryKey()}"/>">Update</a></td>
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
 
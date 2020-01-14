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
<title>Delete a Violation</title>
</head>
<body> 
	<div class="jumbotron">
	<h1>Delete a Violation</h1>
	</div>
	<h3>${messages.title}</h3>
	<form action="violationdelete" method="post">
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="violationhistorykey">ViolationHistoryKey</label>
				<input id="violationhistorykey" name="violationhistorykey" value="${fn:escapeXml(param.violationhistorykey)}">
			</div>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	<div><a href="findestablishmentsbyname">Back Home (Find Establishments by Name)</a></div>
	<div><a href="violationhistorycreate">Create a Violation</a></div>
	<div><a href="findviolationbyviolationkey">Find Violation by Violation Key</a></div>
	<div><a href="findviolationsbyfoodestablishmentkey">Find Violations by Food Establishment Key</a></div>
	<div><a href="findviolationsbyinspectionkey">Find Violations by Inspection Key</a></div>
	<div><a href="findviolationsbycode">Find Violations by Violation Code </a></div>
	<div><a href="findviolationsbyviolationlevel">Find Violations by Violation Level </a></div>
	<div><a href="findviolationbyviolationkey">Find Violations by Establishment Name</a></div>
	<div><a href="findviolationsbyviolationdate">Find Violations by Violation Date </a></div>
	<div><a href="findviolationsbyviolationstatus">Find Violations by Violation Status </a></div>
	<div><a href="violationupdate">Update Violation Comment</a></div>
</body>
</html>

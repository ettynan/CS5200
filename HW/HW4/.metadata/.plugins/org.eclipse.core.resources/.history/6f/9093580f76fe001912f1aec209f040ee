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
<title>Find a User</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<form action="findusers" method="post">
			<div class= "jumbatron">
			<h1>Search for a BlogUser by FirstName</h1>
			</div>
			<p>
				<label for="firstname">FirstName</label>
				<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
			</p>
			<p>
				<input type="submit" class= "btn btn-lg btn-primary">
				<br/><br/><br/>
			</p>
		</form>
	<br/>
	<h3><div id="userCreate"><a href="usercreate">Create BlogUser</a></div></h3>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching BlogUsers</h1>
        <table class="table table-striped">
           <thead><tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>DoB</th>
                <th>BlogPosts</th>
                <th>Comments</th>
                <th>Delete BlogUser</th>
                <th>Update BlogUser</th>
            </tr></thead>
            <c:forEach items="${blogUsers}" var="blogUser" >
                <tbody><tr>
                    <td><c:out value="${blogUser.getUserName()}" /></td>
                    <td><c:out value="${blogUser.getFirstName()}" /></td>
                    <td><c:out value="${blogUser.getLastName()}" /></td>
                    <td><fmt:formatDate value="${blogUser.getDob()}" pattern="yyyy-MM-dd"/></td>
                    <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>
                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td>
                    <td><a href="userdelete?username=<c:out value="${blogUser.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${blogUser.getUserName()}"/>">Update</a></td>
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

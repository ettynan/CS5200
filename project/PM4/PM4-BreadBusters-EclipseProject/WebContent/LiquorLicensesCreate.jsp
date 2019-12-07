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
<title>Create a Liquor License</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
		<h1>Create License</h1>
		</div>
		<form action="liquorlicensescreate" method="post">
			<p>
				<label for="liquorlicensekey">LiquorLicenseKey</label>
				<input id="liquorlicensekey" name="liquorlicensekey" value="">
			</p>
			<p>
				<label for="liquorlicensecomments">LiquorLicenseComments</label>
				<input id="liquorlicensecomments" name="liquorlicensecomments" value="">
			</p>
			<p>
				<label for="liquorlicensecomments">LicenseLocationComments</label>
				<input id="liquorlicensecomments" name="liquorlocationcomments" value="">
			</p>
			<p>
				<label for="licensefk">LicenseFK</label>
				<input id="licensefk" name="licensefk" value="">
			</p>
			<p>
				<label for="licensenumber">LicenseNumber</label>
				<input id="licensenumber" name="licensenumber" value="">
			</p>
			<p>
				<input type="submit" class="btn btn-lg btn-primary">
			</p>
		</form>
		<br/><br/>
		<div class="alert alert-info" role="alert">
			<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</div>
	<p><a href="findliquorlicensesbylicensenumber">Back to Find Liquor Licenses By Number</a></p>
	<!-- Bootstrap scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
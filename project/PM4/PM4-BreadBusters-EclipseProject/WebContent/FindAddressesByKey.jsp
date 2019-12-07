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
	<form action="findaddressesbykey" method="post">
	<div class="jumbotron">
		<h1>Search for an Address by the Establishment's Key</h1>
	</div>
		<p>
			<label for="estabkey">EstablishmentKey</label>
			<input id="estabkey" name="estabkey" value="${fn:escapeXml(param.estabkey)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="AddressesCreate"><a href="addressescreate">Create an Address</a></div>
	<br/>
	<h1>Matching Addresses</h1>
        <table id="AddressTable" border="1">
            <tr>
                <th>EstablishmentKey</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Zip</th>
                <th>PropertyId</th>
                <th>Update Address</th>
                <th>Delete Address</th>
            </tr>
            <tr>
                <td>${address.getEstablishmentKey()}</td>
                <td>${address.getStreet()}</td>
                <td>${address.getCity()}</td>
                <td>${address.getState()}</td>
                <td>${address.getZip()}</td>
                <td>${address.getPropertyId()}</td>
                <td><a href="addressesupdate?estkey=<c:out value="${address.getEstablishmentKey()}"/>">Update</a></td>
                <td><a href="addressesdelete?estaKey=<c:out value="${address.getEstablishmentKey()}"/>">Delete</a></td>
            </tr>
       </table>
       <!-- Bootstrap scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
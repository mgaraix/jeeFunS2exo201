<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="modele.Personne" %>
<% List<Personne>  lesPersons = (List<Personne>) request.getAttribute("lPersons"); %>
<!DOCTYPE html>
<html lang="en"> 
	<head>
	<meta charset="utf-8" />
	<title>Afficher les listes des personnes depuis jsp</title>
	</head>
	<body>
	<% lesPersons.size(); %>
	<div class="container"> 	
		<div class="panel panel-default>
		<!-- default panel  contents -->
			<div class="panel-heading">Listes des personnes<div/>
			<div class="panel-body">
				<P>
				<!-- show  contents personnes -->
				</p>
			<div/>
			<!-- show  Table -->
			<table class="table">
				<tr>
					<th>Id<th/>
					<th>Nom<th/>
					<th>Prénom<th/>
					<th>Age<th/>
				<tr/>
				<!-- Iterate through Personnes   -->
				<% for (Personne p :lesPersons ) { %>
				<tr>
					<td><%= p.getId() %><td/>
					<td><%= p.getNom() %><td/>
					<td><%= p.getPrenom() %><td/>
					<td><%= p.getAge() %><td/>
				<tr/>
				<% } %>
			<table/>
			
		<div/>
	<div/> <!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Wibdows8 & bug -->
		<script scr='js/ie10-"viewport-bug-work>around.js<script/>
	</body>
</html>
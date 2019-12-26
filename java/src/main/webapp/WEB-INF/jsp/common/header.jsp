<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>National Park Geek</title>
<c:url value="/css/site.css" var="cssURL" />
<link rel="stylesheet" type="text/css" href="${cssURL}">
<body class="mainContainer">

<img src="img/logo.png" id="logo" />
<!--  <h1 id="headerTitle">National Park Geek</h1>  -->


<div id="userGreeting">
<c:if test="${not empty appCurrentUser}">
	<h3>Welcome, ${appCurrentUser.firstName }!</h3>
</c:if>
</div>
<div id="headerButtonDiv">
<c:if test="${empty appCurrentUser}">
	
	<form method="get" action="./">
		<button type="submit" class="navButton">Home</button>
	</form>
	<form method="get" action="./registrationForm">
		<button type="submit" class="navButton">Register</button>
	</form>
	<form method="get" action="./login">
		<button type="submit" class="navButton">Login</button>
	</form>

</c:if>
<c:if test="${not empty appCurrentUser}">
	<form method="get" action="./">
		<button type="submit" class="navButton">Home</button>
	</form>
	<form method="get" action="./registrationForm">
		<button type="submit" class="navButton">Register</button>
	</form>
	<form method="get" action="./logout">
		<button type="submit" class="navButton">Logout</button>
	</form>
	<form method="get" action="./submitSurvey">
		<button type="submit" class="navButton">Survey</button>
	</form>
</c:if>
</div>

<hr />
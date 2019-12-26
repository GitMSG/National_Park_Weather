<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:set var="page to load " value="homepage"/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>National Park Geek</title>
  <link rel="stylesheet" href="stylesheet.css" type="text/css">
  </head>
  <body>
  
  <c:forEach var="park" items="${parks}">
  	<div>
   	<a href="./parkDetails?parkCode=${park.parkCode}"><img src="img/parks/${park.parkCode}.jpg"/></a> 
  		<h1>${park.parkName}</h1>
  		<p>${park.parkDescription}</p>
  	</div>
  </c:forEach>
  
  </body>
</html>
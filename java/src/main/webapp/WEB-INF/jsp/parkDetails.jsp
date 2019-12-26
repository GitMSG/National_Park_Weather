<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${park.parkName}Details</title>
</head>

<body>
	
	<h2 id="parkName">${park.parkName }</h2>
	<div class="detailContainer">
	<div class="detailImg">
		<br>
		<img class="imgOnlyDetail" src="img/parks/${park.parkCode}.jpg" /> 
	 	<p id="italics"> ${park.inspirationalQuote} - ${park.inspirationalQuoteSource }</p>
	</div>
	<div class="finerDetails">
		<b>State:</b> <span id="detailSpan">${park.state }</span><br> 
		<b>Acreage:</b> <span id="detailSpan">${park.acreage}</span><br>
		<b>Elevation in Feet:</b><span id="detailSpan"> ${park.elevationInFeet} ft</span><br>
	   <b> Miles Of Trail:</b><span id="detailSpan"> ${park.milesOfTrail } miles</span><br> 
	   <b> Number of Campsites:</b><span id="detailSpan"> ${park.numberOfCampsites}</span><br> 
	    <b>Climate:</b> <span id="detailSpan">${park.climate }</span> <br>
		<b>Year Founded:</b><span id="detailSpan"> ${park.yearFounded}</span><br> 
		<b>Annual Visitors:</b><span id="detailSpan"> ${park.annualVisitorCount}</span><br> 
		<b>Entry Fee:</b><span id="detailSpan">$${park.entryFee }</span> <br>
		<b>Number of Species:</b><span id="detailSpan"> ${park.numberOfAnimalSpecies }</span>
		<hr>
		<div id="detailDescription" >${park.parkDescription }</div>
	</div>
	
	</div>
	<hr>
	<h2>5 - Day Weather Forecast for ${park.parkName }</h2>
	<div id="tempScaleForm">
		<form action="./parkDetails?parkCode=${park.parkCode}" method="POST">
			<label for="Farenheit">Farenheit</label>
			<input type="radio" name="temp" value="farenheit" id="Farenheit" /> 
			
			<label for="Celcius">Celcius</label>
			<input type="radio" name="temp" value="celcius" id="Celcius"/>
			
			<button type="submit" value="convert" class=navButton>Convert</button>
		</form>
	</div>
		<div class="weatherPane">
		<c:forEach var="dailyForecast" items="${weather}">
			<div class="dailyForecast">
				<img src="img/weather/${dailyForecast.forecast}.png" id="weatherImg"/>
				<c:choose>
				<c:when test="${unitPref == 'celcius' }">
				<h4 id="lowTemp">Low: <fmt:formatNumber type="number" maxFractionDigits="0" minFractionDigits="0" value="${(dailyForecast.low - 32) * (5/9)} "/>° C</h4>
				<h4 id="highTemp">High: <fmt:formatNumber type="number" maxFractionDigits="0" minFractionDigits="0" value="${(dailyForecast.high- 32) * (5/9)} "/>°  C</h4>
				</c:when>
				<c:otherwise>
				<h4 id="lowTemp">Low: ${dailyForecast.low}° F</h4>
				<h4 id="highTemp">High: ${dailyForecast.high}° F</h4>
				</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${dailyForecast.forecast == 'rain'}">
						<p id="weatherComment">Pack rain gear and wear waterproof shoes!</p>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${dailyForecast.forecast == 'snow'}">
						<p id="weatherComment">Pack your snowshoes!</p>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${dailyForecast.forecast == 'thunderstorms'}">
						<p id="weatherComment">Seek shelter and avoid hiking on exposed ridges!</p>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${dailyForecast.forecast == 'sunny'}">
						<p id="weatherComment">Pack sunblock!</p>
					</c:when>
				</c:choose>

				<c:if test="${dailyForecast.high > 75 }">Bring an extra gallon of water!</c:if>
				<c:if test="${(dailyForecast.high - dailyForecast.low) > 20 }">Wear breathable layers!</c:if>
				<c:if test="${dailyForecast.low < 20 || dailyForecast.high < 20 }">Frigid temperatures are dangerous!</c:if>
			</div>
		</c:forEach>
	</div>


<%@include file="common/footer.jsp" %>

</body>
</html>
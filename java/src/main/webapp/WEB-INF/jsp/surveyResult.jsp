<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<h1>Favorite Park Survey Results</h1>
<c:forEach var="park" items="${parkInfo.keySet()}">
<c:set var="parkCode" value="${park.parkCode }"></c:set>
<div class="detailContainer">
<div class="detailImg">
<img src="img/parks/${park.parkCode}.jpg" class="imgOnlyDetail"/>
</div>
<h2>${park.parkName} Has..</h2>

<h2> ${parkInfo.get(park)} Votes.</h2>

</div>
<hr>

</c:forEach>
<%@include file="common/footer.jsp" %>
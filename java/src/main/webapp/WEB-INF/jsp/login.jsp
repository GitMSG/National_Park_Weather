<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div class="container body-content">
    <c:if test="${not empty message}">
        <div class="message alert alert-danger" role="alert">
            <c:out value="${message}"/>
        </div>
	    </c:if>
	    </div>
<c:url var="loginUrl" value="/login"/>
<h2>Login</h2>
<div class="formCard">
<form:form action="${loginUrl}" method="POST" modelAttribute="Login">
    <div class="form-group">
        <label for="username">Username</label>
        <form:input path="username"   />
        <form:errors path="username"/>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <form:password path="password"  />
        <form:errors path="password"/>
    </div>
    <button type="submit" class="formButton">Login</button>
</form:form>
</div>
<div class="formFooter">
  <%@include file="common/footer.jsp" %>
  </div>
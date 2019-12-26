<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
  <h2>Register to join the National Park Geeks!</h2>
  <div id="regFormCard">
  	<form:form action="registrationForm" modelAttribute="Registration" method="POST"> 
		<label for="firstName">First Name</label>
		<form:input name= "firstName" path="firstName" />
		<form:errors path="firstName"  cssClass="error"/><br>
		
		<label for="lastName">Last Name</label>
		<form:input name= "lastName" path="lastName" />
		<form:errors path="lastName"  cssClass="error"/><br>
		
		<label for="email">Email</label>
		<form:input type ="email" path="emailAddress" />
		<form:errors path="emailAddress"  cssClass="error"/><br>
		
		<label for="state">State</label>
	    <form:select path="state">
			<form:option value="AL">Alabama</form:option>
			<form:option value="AK">Alaska</form:option>
			<form:option value="AZ">Arizona</form:option>
			<form:option value="AR">Arkansas</form:option>
			<form:option value="CA">California</form:option>
			<form:option value="CO">Colorado</form:option>
			<form:option value="CT">Connecticut</form:option>
			<form:option value="DE">Delaware</form:option>
			<form:option value="DC">District Of Columbia</form:option>
			<form:option value="FL">Florida</form:option>
			<form:option value="GA">Georgia</form:option>
			<form:option value="HI">Hawaii</form:option>
			<form:option value="ID">Idaho</form:option>
			<form:option value="IL">Illinois</form:option>
			<form:option value="IN">Indiana</form:option>
			<form:option value="IA">Iowa</form:option>
			<form:option value="KS">Kansas</form:option>
			<form:option value="KY">Kentucky</form:option>
			<form:option value="LA">Louisiana</form:option>
			<form:option value="ME">Maine</form:option>
			<form:option value="MD">Maryland</form:option>
			<form:option value="MA">Massachusetts</form:option>
			<form:option value="MI">Michigan</form:option>
			<form:option value="MN">Minnesota</form:option>
			<form:option value="MS">Mississippi</form:option>
			<form:option value="MO">Missouri</form:option>
			<form:option value="MT">Montana</form:option>
			<form:option value="NE">Nebraska</form:option>
			<form:option value="NV">Nevada</form:option>
			<form:option value="NH">New Hampshire</form:option>
			<form:option value="NJ">New Jersey</form:option>
			<form:option value="NM">New Mexico</form:option>
			<form:option value="NY">New York</form:option>
			<form:option value="NC">North Carolina</form:option>
			<form:option value="ND">North Dakota</form:option>
			<form:option value="OH">Ohio</form:option>
			<form:option value="OK">Oklahoma</form:option>
			<form:option value="OR">Oregon</form:option>
			<form:option value="PA">Pennsylvania</form:option>
			<form:option value="RI">Rhode Island</form:option>
			<form:option value="SC">South Carolina</form:option>
			<form:option value="SD">South Dakota</form:option>
			<form:option value="TN">Tennessee</form:option>
			<form:option value="TX">Texas</form:option>
			<form:option value="UT">Utah</form:option>
			<form:option value="VT">Vermont</form:option>
			<form:option value="VA">Virginia</form:option>
			<form:option value="WA">Washington</form:option>
			<form:option value="WV">West Virginia</form:option>
			<form:option value="WI">Wisconsin</form:option>
			<form:option value="WY">Wyoming</form:option>
		</form:select><br>
	    
	    <label for="username">Username</label>
		<form:input name= "username" path="username" />
		<form:errors path="username"  cssClass="error"/><br>
		
		<label for="password">Password</label>
		<form:password name= "password" path="password" />
		<form:errors path="password"  cssClass="error"/><br>
		
		<label for="password">Password (Confirm)</label>
		<form:password name= "confirmPassword" path="confirmPassword" />
		<form:errors path="confirmPassword"  cssClass="error"/><br>
		
		<button type="submit" value="Submit" class="formButton">Register</button>
	</form:form>
	</div>
	
  </body>
</html>
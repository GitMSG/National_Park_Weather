package com.techelevator.models;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Registration {
	
	@NotBlank
	@Length(min=1, max=20, message = "First name must be at least 1 character and no more than 20 characters")
	private String firstName;
	
	@NotBlank
	@Length(min=1, max=20, message = "Last name must be at least 1 character and no more than 20 characters")
	private String lastName;
	
	@NotBlank
	@Email
	private String emailAddress;
	
	
	private String state;
	
	@NotBlank
	@Length(min = 3, max = 20, message = "Username must be at least 3 characters and no more than 20 characters")
	private String username;
	
	@NotBlank
	@Length(min = 8, max = 20, message = "Password must be at least 8 characters and no more than 20 characters")
	private String password;
	
	@NotBlank
	@Length(min = 8, max =  20, message = "Password must be at least 8 characers and no more than 20 characters")
	private String confirmPassword;
	private String salt;
	
	public Registration() {
		// TODO Auto-generated constructor stub
	}
	
	
	@SuppressWarnings("unused")
	private boolean emailMatching;
	@AssertTrue(message="Passwords must match")
	public boolean isEmailMatching() {
		if(password != null) {
			return password.equals(confirmPassword);
		} else {
			return false;
		}
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	
	
}

package com.techelevator.jdbc;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;
import com.techelevator.dao.RegistrationDao;

import com.techelevator.models.Registration;

@Component
public class JdbcRegistrationDao implements RegistrationDao{

	JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	@Autowired
	public JdbcRegistrationDao(DataSource dataSource,PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}

	@Override
	public void registerUser(Registration aRegistration) {
		String password= aRegistration.getPassword();
		String userName = aRegistration.getUsername();
		 byte[] salt = passwordHasher.generateRandomSalt();
	        String hashedPassword = passwordHasher.computeHash(password, salt);
	        String saltString = new String(Base64.encode(salt));

		String sqlInsertUser = 	"INSERT INTO user_account " + 
				"        (first_name, last_name, email_address, state_of_residence) " + 
				"        VALUES (?, ?, ?, ?);" + 
				
				"INSERT INTO user_login " + 
				"        (account_number, username, password, salt) " + 
				"        VALUES ((SELECT account_number FROM user_account ORDER BY account_number DESC LIMIT 1), ? , ? , ?);"; 

		jdbcTemplate.update(sqlInsertUser, aRegistration.getFirstName(), aRegistration.getLastName(), aRegistration.getEmailAddress(), aRegistration.getState(),userName,hashedPassword,saltString );
		
	}

}

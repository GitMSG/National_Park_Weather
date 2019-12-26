package com.techelevator.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;
import com.techelevator.dao.UserDao;
import com.techelevator.models.User;

@Component
public class JdbcUserDao implements UserDao{

	private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    @Autowired
    public JdbcUserDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }

	 @Override
	    public User getValidUserWithPassword(String userName, String password) {
	        String sqlSearchForUser = "SELECT * FROM user_login WHERE username = ?";
	        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName);
	        if (results.next()) {
	        	
	            String storedSalt = results.getString("salt");
	            String storedPassword = results.getString("password");
	            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
	            if (storedPassword.equals(hashedPassword)) {
	                return getUserByAccountNumber(results.getInt("account_number"));
	            } else {
	                return null;
	            }
	        } else {
	            return null;
	        }
	    }

	@Override
	public User getUserByAccountNumber(int accountNumber) {
		User aUser =  null;
		String sqlGetUserByAccountNumber  = "SELECT * FROM user_account WHERE account_number = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserByAccountNumber, accountNumber);
		
		while(results.next()) {
			aUser = mapRowToUser(results);
		}
		return aUser;
	}

	public User mapRowToUser(SqlRowSet results) {
        User user = new User();
        user.setAccountNumber(results.getInt("account_number"));
        user.setEmailAddress(results.getString("email_address"));
        user.setFirstName(results.getString("first_name"));
        user.setLastName(results.getString("last_name"));
        user.setState(results.getString("state_of_residence"));
        return user;
    }


}

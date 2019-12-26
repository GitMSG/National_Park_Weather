package com.techelevator.jdbc;



import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;
import com.techelevator.dao.LoginDao;
import com.techelevator.dao.UserDao;
import com.techelevator.models.User;

@Component
public class JdbcLoginDao implements LoginDao {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	
	private UserDao userDao;
	
	@Autowired
	public JdbcLoginDao(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
		// TODO Auto-generated constructor stub
		
	}

}

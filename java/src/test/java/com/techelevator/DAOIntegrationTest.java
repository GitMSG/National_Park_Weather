package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.authentication.PasswordHasher;
import com.techelevator.jdbc.JdbcLoginDao;
import com.techelevator.jdbc.JdbcParkDao;
import com.techelevator.jdbc.JdbcRegistrationDao;
import com.techelevator.jdbc.JdbcSurveyDao;
import com.techelevator.jdbc.JdbcUserDao;
import com.techelevator.jdbc.JdbcWeatherDao;
import com.techelevator.models.Registration;
import com.techelevator.models.Survey;

public class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	
	private JdbcParkDao parkDao;
	private JdbcLoginDao loginDao;
	private JdbcRegistrationDao registrationDao;
	private JdbcSurveyDao surveyDao;
	private JdbcUserDao userDao;
	private JdbcWeatherDao weatherDao;
	private PasswordHasher passwordHasher = new PasswordHasher();
	
	JdbcTemplate jdbcTemplate;
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		parkDao = new JdbcParkDao(dataSource);
		loginDao = new JdbcLoginDao(dataSource, passwordHasher);
		registrationDao = new JdbcRegistrationDao(dataSource, passwordHasher);
		surveyDao = new JdbcSurveyDao(dataSource);
		userDao = new JdbcUserDao(dataSource, passwordHasher);
		weatherDao = new JdbcWeatherDao(dataSource);
	}
	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	
	//Testing parkdDao;
	
	@Test
	public void testGetParkByParkCode() {
		
		String parkName =  parkDao.getParkByParkCode("GNP").getParkName();
		assertEquals("The returned value does not match the expected value", "Glacier National Park", parkName);
	}
	@Test
	public void testGetAllParks() {
		
		int initialParkCount = parkDao.getAllParks().size();
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlCreateNewPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "  +
				"VALUES ('A', 'A', 'A', 1, 1, 1, 1, 'A' , 1, 1, 'A', 'A', 'A', 1, 1) ";
		
		jdbcTemplate.update(sqlCreateNewPark);
		
		int finalParkCount = parkDao.getAllParks().size();
		assertNotEquals("The returned value does not match the expected value", initialParkCount,parkDao.getAllParks().size());
	}
	//Testing userDao;
	@Test
	public void testGetValidUserWithPassword() {
		
		//Register new user to test with
		Registration aRegistration = new Registration();
		aRegistration.setEmailAddress("test@test.com");
		aRegistration.setFirstName("Tester");
		aRegistration.setLastName("Testserson");
		aRegistration.setPassword("testtest");
		aRegistration.setUsername("test");
		aRegistration.setState("OH");
		
		
		registrationDao.registerUser(aRegistration);
		
		String firstName =  userDao.getValidUserWithPassword(aRegistration.getUsername(), aRegistration.getPassword()).getFirstName();
		assertEquals("The returned value does not match the expected value", "Tester", firstName);
	}
	
	
	//Testing weatherDao
	
	@Test
	public void testGetWeatherByParkCode() {
		String forecast =  weatherDao.getWeatherByParkCode("GNP").get(0).getForecast();
		assertEquals("The returned value does not match the expected value", "snow", forecast);
	}
	
	
	//Testing surveyDao
	
	@Test
	public void testSaveSurvey_and_getParksSurveyCount() {
		//Create one survey and record surveyCount
		Survey aSurvey = new Survey();
		aSurvey.setParkCode("MRNP");
		aSurvey.setActivityLevel("slow");
		aSurvey.setEmailAddress("test@test.com");
		aSurvey.setState("OH");
		
		surveyDao.saveSurvey(aSurvey);
		
		int count = surveyDao.getParksSurveyCount().get("MRNP");
		assertEquals("The returned value matches the expected value" , 1, count);
	}
	//Testing registrationDao
	@Test
	public void testRegisterUser() {
		
		//Register new user to test with
		Registration aRegistration = new Registration();
		aRegistration.setEmailAddress("test@test.com");
		aRegistration.setFirstName("Tester");
		aRegistration.setLastName("Testserson");
		aRegistration.setPassword("testtest");
		aRegistration.setUsername("test");
		aRegistration.setState("OH");
		
		
		registrationDao.registerUser(aRegistration);
		
		String firstName =  userDao.getValidUserWithPassword(aRegistration.getUsername(), aRegistration.getPassword()).getFirstName();
		assertEquals("The returned value does not match the expected value", "Tester", firstName);
	}
}

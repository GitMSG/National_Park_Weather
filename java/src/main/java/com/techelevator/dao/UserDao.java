package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.models.User;

public interface UserDao {


    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    public User getValidUserWithPassword(String userName, String password);
    public User getUserByAccountNumber(int accountNumber);
    /**
     * Get all of the users from the database.
     * @return a List of user objects
     */
    public User mapRowToUser(SqlRowSet results);

}

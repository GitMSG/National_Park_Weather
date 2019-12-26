package com.techelevator.authentication;

import com.techelevator.models.User;

public interface AuthProvider {
    /**
     * Returns true if a current user is logged in.
     * @return true if user is logged in
     */
    boolean isLoggedIn();

    /**
     * Returns the currently signed in user.
     * @return the currently signed in user
     */
    User getCurrentUser();

    /**
     * Signs in a user using the given username and password
     * @param username the given username
     * @param password the given password
     * @return true if user was successfully signed in
     */
    boolean signIn(String username, String password);

    /**
     * Sign out the currently signed in user
     */
    void logOff();

    /**
     * Change the current signed in user's password.
     * @param existingPassword the current user's current password
     * @param newPassword the new password for the current user
     * @return true, if successful
     */
    
   
    
}

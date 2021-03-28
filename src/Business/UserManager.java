/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import com.sun.org.apache.bcel.internal.Constants;
import java.util.HashMap;

/**
 *
 * @author micha
 */
public class UserManager {

    private static String response;
    private User us = new User();
    private static HashMap<String, User> users;

    public UserManager() {
    }

    //register method needs to be synchronized 
    public Boolean signUp(String email, String password) {
        //set uo status flag
        boolean flag = false;
        //check the storage if the credentials exist
        if (users.containsKey(email)) {
            if (users.get(email).getEmail().equalsIgnoreCase(us.getEmail())
                    && users.get(email).getPassword().equalsIgnoreCase(password)) {
                flag = true;
                response = "USER_EXIST";
            }
        }
        //add the new user to the map
        us.setEmail(email);
        us.setPassword(password);
        synchronized (users) {
            users.put(email, us);
            response = "CREATED";
        }
        return flag;

    }

    //login method
    public Boolean signIn(String email, String password) {
        //validate user's credentials
        boolean flag = false;

        synchronized (users) {
            if (users.containsKey(email) && users.containsValue(us.getPassword())) {
                flag = true;
                response = "LOGGED_IN";
            } else {
                response = "INVALID_CREDENTIALS";
            }
        }
        return flag;

    }

    //sign out user
    public synchronized boolean signOut() {
        response = "LOGGEDOUT";
        return false;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Business.Email;
import Business.EmailManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class Service {

    //setu up breaking character
    public final static String BREAKING_CHARACTER = "%%";

    // host setup
    public final static String HOSTNAME = "localhost";
    public final static int PORT_NUM = 8081;

    //command to sign u a user
    public final static String SIGNUP_COMMAND = "signup";
    public final static String SUCCESSFUL_SIGNUP = "SIGNEDUP";
    public final static String FAILED_SIGNUP = "FAILED";

    //command to login a User
    public final static String SIGNIN_COMMAND = "signin";
    public final static String SUCCESSFUL_SIGNIN = "SIGNEDIN";
    public final static String FAILED_SIGNIN = "INVALID_CREDENTIALS";

    //command to send an email
    public final static String SEND_COMMAND = "send";
    public final static String SUCCESSFUL_SENT = "SEND";
    public final static String FAILED_SEND = "FAILED!";


    //command delete email
    public final static String DELETE_COMMAND = "delete";
    public final static String SUCCESSFUL_DELETE = "DELETED";
    public final static String FAILED_DELETE = "FAILED";

//command to mark email as spam
    public final static String SPAM_COMMAND = "markSpam";
    public final static String SUCCESSFUL_SPAM = "SPAMMED";
    public final static String FAILED_SPAM = "FAILED";
    
    //command to select a single email
    public final static String SINGLE_EMAIL_COMMAND = "singleEmail";
    public final static String SUCCESSFUL_SELECTED = "SELECTED";
    public final static String FAILED_SELECTION = "NOT_FOUND";

    //command to mark email as spam
    public final static String MARKAS_READ_COMMAND = "markRead";
    public final static String SUCCESSFUL_MARKED = "MARKED";
    public final static String FAILED_MARK = "FAILED";
    
    //command to get list of all emails
    public final static String VIEW_COMMAND = "viewAll";
    public final static String EMAIL_SEPARATOR = "##";

    //command to signout
    public final static String LOGOUT_COMMAND = "logout";
    public final static String SIGN_OFF = "GOODBYE";

    private static EmailManager emailmanager;

    public static String flattenEmailList(List<Email> emailList) {
        if (!emailList.isEmpty()) {
            String emails = emailList.get(0).format();
            for (int i = 1; i < emailList.size(); i++) {
                emails = emails + EMAIL_SEPARATOR + emailList.get(i).format();
            }
            return emails;
        }
        return null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.EmailManager;
import Business.UserManager;

/**
 *
 * @author micha
 */
public interface Command {
    //user manager response 
    public String generateResponse(String [] components, UserManager usermanager);
    
     //Email manager response 
    public String generateResponse(String [] components, EmailManager emailmanager);
}

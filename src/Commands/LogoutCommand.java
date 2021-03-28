/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.EmailManager;
import Business.UserManager;
import Services.Service;

/**
 *
 * @author micha
 */
public class LogoutCommand implements Command {

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
        String response = null;
        if(components.length ==1){
            response =Service.SIGN_OFF;
        }
        return response;
    }
    
}

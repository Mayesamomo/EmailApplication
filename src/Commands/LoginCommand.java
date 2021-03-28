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
public class LoginCommand implements Command{

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
       String response = null;
        if (components.length == 3) {
            try {
                String username = components[1];
                String password = components[2];
                boolean loggedIn = usermanager.signIn(username, password);
                if (loggedIn) {
                    response = Service.SUCCESSFUL_SIGNIN;
                }
                else{
                 response = Service.FAILED_SIGNIN;
                }
            }catch(NumberFormatException ex){
                  response = Service.FAILED_SIGNIN;
            }

        }
        return response;
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

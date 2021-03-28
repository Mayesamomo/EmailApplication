/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.EmailManager;
import Business.User;
import Business.UserManager;
import Services.Service;

/**
 *
 * @author micha
 */
public class RegisterCommand implements Command {

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
        String response = null;
        if (components.length == 3) {
            try {
                String username = components[1];
                String password = components[2];
                boolean registered = usermanager.signUp(username, password);
                if (registered) {
                    response = Service.SUCCESSFUL_SIGNUP;
                }
                else{
                 response = Service.FAILED_SIGNUP;
                }
            }catch(NumberFormatException ex){
                 response = Service.FAILED_SIGNUP;
            }

        }
        return response;
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.Email;
import Business.EmailManager;
import Business.UserManager;
import Services.Service;
import java.util.List;

/**
 *
 * @author micha
 */
public class SingleEmailCommand implements Command {

    public SingleEmailCommand() {
    }

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
      String response = null;
       
       if(components.length ==3){
           //remove email using the subject
           String emailaddress = components[1];
           int id = Integer.parseInt(components[2]);
           Email mailTobeSelected = new Email(emailaddress);
           boolean selected = emailmanager.getSelectedEmail(emailaddress, id);
           if(selected){
               response = Service.SINGLE_EMAIL_COMMAND;
           }else{
               response = Service.SUCCESSFUL_SELECTED;
           }
       }
        return response;
    }
    
}

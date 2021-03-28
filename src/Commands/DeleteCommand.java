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

/**
 *
 * @author micha
 */
public class DeleteCommand implements Command{

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
       String response = null;
       
       if(components.length ==3){
           //remove email using the subject
           String emailSubject = components[1];
           int id = Integer.parseInt(components[2]);
           Email mailTobeDeleted = new Email(emailSubject);
           boolean deleted = emailmanager.deleteEmail(emailSubject, id);
           if(deleted){
               response = Service.SUCCESSFUL_DELETE;
           }else{
               response = Service.FAILED_DELETE;
           }
       }
        return response;
    }
    
}

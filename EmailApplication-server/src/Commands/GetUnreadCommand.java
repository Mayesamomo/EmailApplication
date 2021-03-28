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
public class GetUnreadCommand implements Command {

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
       String response = null;
        if(components.length ==2){
            String sender = components[1];
             List<Email> mails = emailmanager.getUnreadEmails(sender);
             response = Service.flattenEmailList(mails);
             if(response ==null){
                 response =Service.VIEW_COMMAND;
             }
        }
        return response;
           
        }
    
}

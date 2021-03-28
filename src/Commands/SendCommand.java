/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.EmailManager;
import Business.UserManager;
import Services.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author micha
 */
public class SendCommand implements Command{

    @Override
    public String generateResponse(String[] components, UserManager usermanager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateResponse(String[] components, EmailManager emailmanager) {
       String response = null;
        if (components.length == 6) {
            try {
                String sender = components[1];
                  long sentDate = Date.parse(components[2]);
                String subject = components[3];
                String content = components[4];
                String receiver = components[5];
              
               // ArrayList receiver = new ArrayList<>();
                List<String> al = new ArrayList<>();
               al= Arrays.asList(receiver);
                
                Long date = Long.parseLong(components[5]);
                boolean added = emailmanager.sendEmail(sender, new Date(sentDate) , subject, content, (ArrayList) al);
                if (added) {
                    response = Service.SEND_COMMAND;
                }
                else{
                 response = Service.FAILED_SEND;
                }
            }catch(NumberFormatException ex){
                  response = Service.FAILED_SEND;
            }

        }
        return response;
    }
    
}

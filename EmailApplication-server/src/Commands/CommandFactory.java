/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Services.Service;

/**
 *
 * @author micha
 */
public class CommandFactory {
    public static Command createCommand(String requestCommand){
        Command c = null;
        switch(requestCommand){
            case Service.SIGNUP_COMMAND:
                c = new RegisterCommand();
                break;
            case Service.SIGNIN_COMMAND:
                c = new LoginCommand();
                break;
            case Service.SEND_COMMAND:
                c = new SendCommand();
                 break;
          
            case Service.VIEW_COMMAND:
                c = new ViewAllCommand();
                 break;
            case Service.DELETE_COMMAND:
                c =  new DeleteCommand();
                 break;
            case Service.SPAM_COMMAND:
                c = new SpamCommand();
                 break;
            case Service.MARKAS_READ_COMMAND:
                c = new MarkReadCommand();
                 break;
            case Service.SINGLE_EMAIL_COMMAND:
                c = new SingleEmailCommand();
                 break;
            case Service.LOGOUT_COMMAND:
                c = new LogoutCommand();
                 break;
            default:
                 c = new ViewAllCommand();
                
        }
        return c;
    }
}

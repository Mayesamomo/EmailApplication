/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Business.EmailManager;
import Business.UserManager;
import Commands.Command;
import Commands.CommandFactory;
import Commands.LogoutCommand;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author micha
 */
public class EmailCLientHandler implements Runnable{

    private Socket clientSocket;
    private EmailManager email;
    private UserManager users;

    public EmailCLientHandler(Socket clientSocket, EmailManager email, UserManager users) {
        this.clientSocket = clientSocket;
        this.email = email;
        this.users = users;
    }
    
    
    @Override
    public void run() {
        try {
            //communication lines
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream());
            
            //set up repeated Exchanges
            
            boolean sessionActive = true;
            while(sessionActive){
                
                String request = clientInput.nextLine();
                
                //separet info
                String[] components  = request.split(Service.BREAKING_CHARACTER);
                String responseEmail;
                String responseUser;
                
                Command c = CommandFactory.createCommand(components[0]);
                
                // if the command is not null (command exists)
                if(c != null){
                    responseEmail =c.generateResponse(components, email);
                    responseUser  = c.generateResponse(components, users);
                    //if there is a response
                    if(responseEmail != null ||  responseUser != null ){
                        clientOutput.println(responseEmail + responseUser);
                        //if response(user)chose to exit 
                        if(c instanceof LogoutCommand){
                            sessionActive = false;
                        }
                    }
                }
            }
            //close connection
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(EmailCLientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Business.EmailManager;
import Business.UserManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author micha
 */
public class Server {

    /**
     *
     * @param clientSocket the value of clientSocket
     * @param emailList the value of emailList
     * @param user the value of user
     */
    public Server(Socket clientSocket, EmailManager emailList, UserManager user) {
    }

    public static void main(String[] args) {

        EmailManager emailList = new EmailManager();
        UserManager user = new UserManager();
        try {
            ServerSocket connection = new ServerSocket(Service.PORT_NUM);
            while (true) {
                Socket clientSocket = connection.accept();
                Server clientHandler = new Server(clientSocket, emailList, user);
                Thread worker = new Thread((Runnable) clientHandler);
                worker.start();
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong!" + ex);
        }

    }
}

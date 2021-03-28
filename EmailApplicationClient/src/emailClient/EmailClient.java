/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.OptionMenu;

/**
 *
 * @author micha
 */
public class EmailClient {

    public static void main(String[] args) {
        //variables
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        String BREAKING_CHARACTER = "%%";
        String response = "";
        String email;
        String password;
        System.out.println("Please enter the hostname on which your service is running:");
        String hostname = input.next();
        System.out.println("Please enter the port number on which your service is running:");
        int port = input.nextInt();

        try {
            Socket dataSocket = new Socket(hostname, port);
            Scanner serverIn = new Scanner(dataSocket.getInputStream());
            PrintWriter serverOut = new PrintWriter(dataSocket.getOutputStream(), true);

            boolean keepRunning = true;
            while (keepRunning) {
                System.out.println("Please enter the command to be sent");
                String toBeSent = input.next();
                serverOut.println(toBeSent);

                response = serverIn.nextLine();
                System.out.println("Response: " + response);

                System.out.println("Do you wish to continue? (-1 to end, any other key to continue)");
                int choice = input.nextInt();
                choice = OptionMenu.MenuOption(input);
                switch (choice) {
                    case 1: {
                        System.out.println("Enter email address");
                        email = input.nextLine();
                        System.out.println("Enter password");
                        password = input.nextLine();

                        response = "SIGNIN_COMMAND"
                                + BREAKING_CHARACTER + email + BREAKING_CHARACTER + password;

                        serverOut.println(response);
                        serverOut.flush();
                        response = serverIn.nextLine();
                        if (response.equals("FAILED_SIGNIN")) {
                            System.out.println("Oops! bad credential");
                        } else if (response.equals("SUCCESSFUL_SIGNIN")) {
                            System.out.println("You are LoggedIn!");

                            int emailMenu = -1;
                            while (emailMenu != 0) {
                                response = ""; //purge resposne's values
                                emailMenu = OptionMenu.emailMenu(input);
                                switch (emailMenu) {
                                    case 1: {
                                        String receivers = "";
                                        List<String> recepients = new ArrayList<>();
                                        boolean flag = true;
                                        System.out.println("==Receiver's email==");

                                        String receivee = input.nextLine();

                                        //while true
                                        while (flag) {
                                            System.out.println("enter another receiver if there is, or type " + " DONE" + "when done"); //prompt user for action
                                            receivers = input.nextLine();
                                            if (receivers.equals("DONE")) {
                                                receivee = receivee.concat(BREAKING_CHARACTER + receivers);

                                            }
                                            flag = false;
                                        }
                                        System.out.println("Email Subject");
                                        String subject = input.nextLine();
                                        System.out.println("Email body");
                                        String body = input.nextLine();

                                        LocalDate date = LocalDate.now(); //get localtime 

                                        DateTimeFormatter dateformater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //set time format to datTime
                                        String formatted = date.format(dateformater);

                                        //parse response and values
                                        response = "SEND_COMMAND"
                                                + BREAKING_CHARACTER + email
                                                + BREAKING_CHARACTER + formatted
                                                + BREAKING_CHARACTER + subject
                                                + BREAKING_CHARACTER + body
                                                + BREAKING_CHARACTER + receivee;
                                        serverOut.println(response);
                                        response = serverIn.nextLine(); //append inputs

                                        String[] components = response.split(BREAKING_CHARACTER); // array of input choice 

                                        if (response.equals("SUCCESSFUL_SENT")) {
                                            System.out.println("SENT!");
                                        } else if (response.equals("FAILED_SEND")) {
                                            System.out.println("Failed to send email");
                                        } else if (components[0].equalsIgnoreCase("SENDING ")) {
                                            System.out.println("some email address where invalid!");
                                            //list bad emails

                                            for (int i = 0; i < components.length - 1; i++) {
                                                System.out.println("could not sent emails to: <maillTo> " + components[i]);
                                            }
                                        }

                                        System.out.println("unknown error encountered!");
                                        break;
                                    }
                                    case 2: {

                                        response = "GET_UNREAD_EMAILS" + BREAKING_CHARACTER + email;

                                        serverOut.println(response);
                                        serverOut.flush();
                                        response = serverIn.nextLine();

                                        String[] components = response.split(BREAKING_CHARACTER);

                                        if (response.equals("NO_NEW_MAILS")) {
                                            System.out.println("All emails are read!");
                                        } else if (components[0].equals("GET_UNREAD_EMAILS")) {
                                            for (int i = 1; i < components.length; i++) {
                                                String[] emailaddress = components[i].split(BREAKING_CHARACTER);
                                                System.out.println("===========================================================");
                                                System.out.println("Sender: " + emailaddress[0]);
                                                System.out.println("Date sent: " + emailaddress[1]);
                                                System.out.println("Subject: " + emailaddress[2]);
                                                System.out.println("Body: " + emailaddress[3]);
                                                System.out.println("===========================================================");
                                            }
                                        } else {
                                            System.out.println("Unknown response");
                                        }
                                        break;
                                    }
                                    case 3: {

                                        response = "viewAll" + BREAKING_CHARACTER + email;

                                        serverOut.println(response);
                                        serverOut.flush();
                                        response = serverIn.nextLine();

                                        String[] components = response.split(BREAKING_CHARACTER);
                                        if (components[0].equals("viewAll")) {
                                            for (int i = 1; i < components.length; i++) {
                                                String[] emailaddress = components[i].split(BREAKING_CHARACTER);
                                                System.out.println("===========================================================");
                                                System.out.println("Sender: " + emailaddress[0]);
                                                System.out.println("Date sent: " + emailaddress[1]);
                                                System.out.println("Subject: " + emailaddress[2]);
                                                System.out.println("Body: " + emailaddress[3]);
                                                System.out.println("===========================================================");
                                            }
                                        } else {
                                            System.out.println("Unknown response");
                                        }

                                        break;
                                    }
                                    case 4: {
                                        response = "GET_READ_EMAILS" + BREAKING_CHARACTER + email;
                                        serverOut.println(response);
                                        serverOut.flush();
                                        response = serverIn.nextLine();

                                        String[] components = response.split(BREAKING_CHARACTER);

                                        if (response.equals("NEW_MAILS")) {
                                            System.out.println("You have unread emails!");
                                        } else if (components[0].equals("GET_EMAILS")) {
                                            for (int i = 1; i < components.length; i++) {
                                                String[] emailaddress = components[i].split(BREAKING_CHARACTER);
                                                System.out.println("(" + i + ") Sender: " + emailaddress[0] + "/n" + "subject" + emailaddress[2]);
                                            }
                                        }
                                        System.out.println("Select Email to delete");

                                        int deleteChoice = -1;
                                        while (deleteChoice < 1 || deleteChoice > components.length) {
                                            try {
                                                deleteChoice = input.nextInt();
                                            } catch (InputMismatchException ex) {
                                                System.out.println("Please enter a number(Value) to select from the menu!");
                                                input.nextLine();
                                            }
                                        }
                                        deleteChoice -= 1;

                                        response = "DELETE_COMMAND"
                                                + BREAKING_CHARACTER
                                                + email + BREAKING_CHARACTER + deleteChoice;

                                        serverOut.println(response);
                                        serverOut.flush();
                                        response = serverIn.nextLine();
                                        System.out.println(response);

                                        if (response.equals("SUCCESSFUL_DELETE")) {
                                            System.out.println("Email deleted!");
                                        } else if (response.equals("FAILED_DELETE")) {
                                            System.out.println("could not delete email");
                                        } else {
                                            System.out.println("Unknown error");
                                            System.out.println(response);
                                        }

                                    }
                                    break;
                                }
                            }
                        }
                    }
                    break;
                    case 2: {

                        System.out.println("Enter email address: ");
                        email = input.nextLine();
                        System.out.println("Enter password: ");
                        password = input.nextLine();

                        response = "SIGNUP_COMMAND"
                                + BREAKING_CHARACTER
                                + email + BREAKING_CHARACTER
                                + password;

                        serverOut.println(response);
                        serverOut.flush();
                        response = serverIn.nextLine();
                        System.out.println(response);

                        if (response.equals("SUCCESSFUL_SIGNUP")) {
                            System.out.println("Your email is created!");
                        } else if (response.equals("FAILED_SIGNUP ")) {
                            System.out.println("Register failed.");
                        } else {
                            System.out.println("Unknown error occurred");
                        }
                        break;

                    }
                    case 0: {
                       
                        System.out.println("logging off");
                        break;
                    }
                    default:
                        if (choice == 1) {
                            keepRunning = false;
                        }

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thank you for using this client.");
    }

}

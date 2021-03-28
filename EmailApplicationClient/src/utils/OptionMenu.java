/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author micha
 */
public class OptionMenu {
    
           //pick an option for email menu
      public static int emailMenu(Scanner input) {
        System.out.println("==============================================================");
        int emailOptions = -1;
        while (emailOptions < 0 || emailOptions > 9) {
            System.out.println("Make a choice please!");
            System.out.println("(1) Send Email");
            System.out.println("(2) Check Unread emails");
            System.out.println("(3) List all Email");
            System.out.println("(4) Delete email");
            System.out.println("0) Exit");

            try {
                emailOptions= input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Pick a menu of menu choice form the above!");
                input.nextLine();
            }
        }
        input.nextLine();
        System.out.println("==============================================");
        return emailOptions;
    }
      
      
    public static int MenuOption(Scanner input) {
        System.out.println("========================================================");
        int choice = -1;
        while (choice < 0 || choice > 2) {
            System.out.println("choose Menu choice!");
            System.out.println("(1) SignIn");
            System.out.println("(2) SignUp");
            System.out.println("(0) Logout/exit");

            try {
                choice = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Enter a number value from the example  to select from the menu option!");
                input.nextLine();
            }
        }
        input.nextLine();
        System.out.println("===============================================================");
        return choice;
    }

}

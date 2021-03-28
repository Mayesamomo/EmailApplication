/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author micha
 */
public class EmailManager {

    //configure storage type
    private static HashMap<String, ArrayList<Email>> sentEmails = new HashMap<>();
    private static HashMap<String, ArrayList<Email>> receivedEmails = new HashMap<>();
    private static HashMap<String, ArrayList<Email>> spam = new HashMap<>();
    private static HashMap<String, ArrayList<Email>> newEmails = new HashMap<>();
    private static HashMap<String, ArrayList<Email>> readEmails = new HashMap<>();

    private List<Email> emails;

    public EmailManager(List<Email> emails) {
        this.emails = emails;
    }

    public EmailManager() {
    }

    public static HashMap<String, ArrayList<Email>> getSentEmails() {
        return sentEmails;
    }

    public static HashMap<String, ArrayList<Email>> getReceivedEmails() {
        return receivedEmails;
    }

    public static HashMap<String, ArrayList<Email>> getSpam() {
        return spam;
    }

    public static HashMap<String, ArrayList<Email>> getNewEmails() {
        return newEmails;
    }

    public static HashMap<String, ArrayList<Email>> getReadEmails() {
        return readEmails;
    }

    //initialialise map, create array of mailing elements ,
    // and put them into the hashmap 
    public void initalizeMaps(String email) {
        ArrayList<Email> sentMails = new ArrayList();
        ArrayList<Email> allReceivedMails = new ArrayList();
        ArrayList<Email> spammedMails = new ArrayList();
        ArrayList<Email> unreadEmails = new ArrayList();
        //put into respective map using the key (email address)
        sentEmails.put(email, sentMails);
        receivedEmails.put(email, allReceivedMails);
        spam.put(email, spammedMails);
        newEmails.put(email, unreadEmails);

    }

    /**
     * use synchronization , to allow multiple registration and load balancing
     * get the sender details and attach it to the sender variable, it also
     * stands as storage for all email sent by the specific sender If the
     * specified key is not already associated with a value (or is mapped to
     * null), attempts to compute its value using the given mapping function and
     * enters it into this map unless null.
     *
     * @param sender
     * @param sendDate
     * @param content
     * @param receivers
     * @param subject
     * @return
     */
    public synchronized boolean sendEmail(String sender, Date sendDate, String subject, String content, ArrayList receivers) {
        
        for (int i = 0; i < receivers.size(); i++) {
            
            Email email = new Email(sender, sendDate, subject, content, receivers.get(i).toString());
            
            newEmails.get(receivers.get(i)).add(email);

        }
        
        Email email = new Email(sender, sendDate, subject, content, sender);

        sentEmails.computeIfAbsent(sender, (String fnctn) -> {
            return new ArrayList<>();
        }).add(email);

        return true;
    }

//method returns a list of unread emails from all address
    public ArrayList<Email> getUnreadEmails(String email) {
        ArrayList<Email> emai = new ArrayList(newEmails.get(email));
        //lamda expression compute ,If the specified key is not already associated with a value
        //attempts to compute its value using the given mapping function and enters it into this map unless null
        receivedEmails.computeIfAbsent(email, fnctn -> new ArrayList<>()).addAll(emails);
        newEmails.get(email).clear(); //clear out after it is read
        readEmails.put(email, emai);
        return emai;
    }

    //return all received email 
    public ArrayList<Email> getReceivedEmails(String email) {
        return receivedEmails.get(email); //rturns all emails using the key email to return an object
    }

    //return all read emails
    public ArrayList<Email> getReadEmails(String email) {
        Email mail = new Email();

        return readEmails.get(mail); //rturns all emails using the key email to return an object
    }

    //return a list of spammed emails
    public ArrayList<Email> getSpammedEmails(String email) {
        return spam.get(email);
    }

    //return a selected email using the email as an Id (KEY)
    public boolean getSelectedEmail(String email, int selectedEmail) {
        boolean contains;
        contains = receivedEmails.get(email).contains(selectedEmail);
        return true;
    }

    //delete email
    public boolean deleteEmail(String email, int selectedEmail) {
        receivedEmails.get(email).remove(selectedEmail);
        return true;
    }

//mark a selected email as spam
    public boolean markEmailSpam(String email, int selectedEmail) {
        synchronized (receivedEmails) {
            spam.computeIfAbsent(email, fnctn -> new ArrayList<>()).
                    add(receivedEmails.get(email).get(selectedEmail));
            receivedEmails.get(email).remove(selectedEmail);
            return true;
        }

    }

    //delete a spam and reduced memory usage
    public byte deleteAllSpam(String email) {
        synchronized (receivedEmails) {
            byte spamSize = (byte) spam.get(email).size();
            spam.get(email).clear();
            return spamSize;
        }

    }
}

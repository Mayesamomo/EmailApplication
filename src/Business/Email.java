/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Services.Service;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author micha
 */
public class Email {

    private String sender;
    private Date dateSent;
    private String subject;
    private String emailContent;
    private String receiver;
    private int id;
    private static int count;

    public Email(String sender, Date dateSent, String subject, String emailContent) {
        this.sender = sender;
        this.dateSent = dateSent;
        this.subject = subject;
        this.emailContent = emailContent;
        this.id =count ++;
       
    }

    public Email(String sender, Date dateSent, String subject, String emailContent, String receiver) {
        this.sender = sender;
        this.dateSent = dateSent;
        this.subject = subject;
        this.emailContent = emailContent;
        this.receiver = receiver;
    }
    

    //no parametrized parameter
    Email() {
    }

    public Email(String subject) {
        this.subject = subject;
    }

    public Email(String subject, int id) {
        this.subject = subject;
        this.id = id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Email.count = count;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.sender);
        hash = 53 * hash + Objects.hashCode(this.dateSent);
        hash = 53 * hash + Objects.hashCode(this.subject);
        hash = 53 * hash + Objects.hashCode(this.emailContent);
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.emailContent, other.emailContent)) {
            return false;
        }
        if (!Objects.equals(this.dateSent, other.dateSent)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Email{"
                + " "
                +"Id: " + id + "/n"
                + "Sender: " + sender + "/n"
                + "DateTime: " + dateSent + "/n"
                + "Subject: " + subject + "/n"
                + " EmailContent: " + emailContent
                + '}';
    }

    public String format() {
        return sender + Service.BREAKING_CHARACTER
                + subject + Service.BREAKING_CHARACTER
                + dateSent.getTime()
                + emailContent + Service.BREAKING_CHARACTER;
    }
}

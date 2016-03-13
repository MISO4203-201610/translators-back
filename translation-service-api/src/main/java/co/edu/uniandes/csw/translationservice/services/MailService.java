/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.services;

import javax.mail.*;

import javax.mail.internet.*;

import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author jhony
 */
public class MailService {

    public static void sendMailAdmin(String[] to, String subject, String body) {

        PropertyReader.initializePropertyReader();
        String from = PropertyReader.getPropertyValue("userAdmin");
        String pass = PropertyReader.getPropertyValue("pass");
        String host = PropertyReader.getPropertyValue("mail.smtp.host");
        
        System.out.println("from: "+from);
        
        Session session = Session.getDefaultInstance(PropertyReader.getProperties());

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtps");
            transport.connect(host, from, pass);

            List<String> toAddresses = new ArrayList<String>();
            Address[] recipients = message.getRecipients(Message.RecipientType.TO);
            for (Address address : recipients) {
                toAddresses.add(address.toString());
            }

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

    }
}

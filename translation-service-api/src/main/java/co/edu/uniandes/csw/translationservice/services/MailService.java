/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.services;

import co.edu.uniandes.csw.translationservice.dtos.TranslatorDTO;
import javax.mail.*;

import javax.mail.internet.*;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhony
 */
public class MailService {
    
    private static final int MAX_EMAIL =40;
    
    private MailService(){
    }

    //metodo para enviar mail a traslators
    public static void sendMailAdmin(List<TranslatorDTO> list, String subject, String body) {

    String[] to= new String[MAX_EMAIL];
    to[0]= "jhonyt37@gmail.com";
        try {
            PropertyReader.initializePropertyReader();
            String from = PropertyReader.getPropertyValue("userAdmin");
            String pass = PropertyReader.getPropertyValue("pass");
            String host = PropertyReader.getPropertyValue("mail.smtp.host");
            
            Session session = Session.getDefaultInstance(PropertyReader.getProperties());
            
            MimeMessage message = new MimeMessage(session);
            
            int i = 1;
            if (list.isEmpty()) {
                Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, "lista vacia");
                return;
            }
            for (TranslatorDTO item : list) {
                if (item.getEmail() != null) {
                    to[i] = item.getEmail();
                    i++;
                }
            }

            message.setFrom(new InternetAddress(from));

            // To get the array of addresses
            for ( i = 0; i < to.length; i++) {
                if(to[i]!=null)
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
        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

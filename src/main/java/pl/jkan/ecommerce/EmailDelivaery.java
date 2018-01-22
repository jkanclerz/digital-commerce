package pl.jkan.ecommerce;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EmailDelivaery {

    public void postMail(String recipient, String subject,
                         String content , String from) throws MessagingException {

        final String username = "kanclerj@uek.krakow.pl";
        final String password = "Kubusbubus1311";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "poczta.uek.krakow.pl");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kanclerj@uek.krakow.pl"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Testing Subject");
            message.setText(content);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

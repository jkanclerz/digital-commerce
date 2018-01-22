package pl.jkan.ecommerce.delivery.infrastructure;

import org.junit.Test;
import pl.jkan.ecommerce.EmailDelivaery;

import javax.mail.MessagingException;

public class EmailDeliveryMechanismTest {
    @Test
    public void itAllowSendEmail() {
        EmailDelivaery emailDelivaery = new EmailDelivaery();

        try {
            emailDelivaery.postMail("jakub.kanclerz@gmail.com", "xy", "message", "Jakub");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}

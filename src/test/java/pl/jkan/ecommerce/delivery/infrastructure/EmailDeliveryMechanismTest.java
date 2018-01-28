package pl.jkan.ecommerce.delivery.infrastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.support.smtp.Mailer;
import pl.jkan.support.smtp.MailerException;

import javax.mail.MessagingException;

public class EmailDeliveryMechanismTest {

    private SpyMailer mailer;
    private EmailDeliveryMechanism emailDeliverMechanism;

    @Before
    public void setUp() {
        this.mailer = new SpyMailer();
        this.emailDeliverMechanism = new EmailDeliveryMechanism(mailer);
    }
    @Test
    public void itDeliverTitledEmail() {
        Identifier oderId = Identifier.generateUUID();
        DeliverySubject subject = new DeliverySubject(oderId, "", "Your order is here");

        emailDeliverMechanism.handleDelivery(subject);

        Assert.assertEquals(
                "Your order is here",
                mailer.lastSubject
        );
    }

    class SpyMailer implements Mailer {
        public String lastSubject;
        public String lastRecipient;
        public String lastContent;

        @Override
        public void send(String recipient, String subject, String content, String from) throws MailerException {
            this.lastRecipient = recipient;
            this.lastSubject = subject;
            this.lastContent = content;
        }
    }
}

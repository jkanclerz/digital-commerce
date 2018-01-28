package pl.jkan.ecommerce.delivery.infrastructure;

import pl.jkan.ecommerce.delivery.domain.DeliveryMechanism;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.support.smtp.Mailer;
import pl.jkan.support.smtp.MailerException;

public class EmailDeliveryMechanism implements DeliveryMechanism {
    private Mailer mailer;

    public EmailDeliveryMechanism(Mailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public void handleDelivery(DeliverySubject deliverySubject) {
        String subject = String.format("Your order %s is here", deliverySubject.getOrderId().toString());

        try {
            mailer.send("kuba.kanclerz@gmail.com", subject, "", "");
        } catch (MailerException e) {
        }
    }
}

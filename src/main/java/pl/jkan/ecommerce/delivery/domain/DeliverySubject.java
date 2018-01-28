package pl.jkan.ecommerce.delivery.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class DeliverySubject {
    private Identifier orderId;
    private String content;
    private String subject;

    public DeliverySubject(Identifier orderId, String content, String subject) {
        this.orderId = orderId;
        this.content = content;
        this.subject = subject;
    }

    public Identifier getOrderId() {
        return orderId;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }
}

package pl.jkan.ecommerce.delivery.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class DeliverySubject {
    private Identifier orderId;

    public DeliverySubject(Identifier orderId) {
        this.orderId = orderId;
    }

    public Identifier getOrderId() {
        return orderId;
    }
}

package pl.jkan.ecommerce.delivery.application;

import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.delivery.domain.DeliveryMechanism;
import pl.jkan.ecommerce.delivery.domain.DeliverySubject;
import pl.jkan.ecommerce.system.Subscriber;

public class OrderConfirmedListener implements Subscriber<OrderConfirmed> {

    private DeliveryMechanism deliveryMechanism;

    public OrderConfirmedListener(DeliveryMechanism deliveryMechanism) {
        this.deliveryMechanism = deliveryMechanism;
    }

    @Override
    public void handle(OrderConfirmed event) {
        deliveryMechanism.handleDelivery(new DeliverySubject(event.getOrderId()));
    }
}

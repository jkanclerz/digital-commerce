package pl.jkan.digitalcommerce.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.delivery.application.OrderConfirmedListener;
import pl.jkan.ecommerce.delivery.infrastructure.DumpToFileDeliveryMechanism;
import pl.jkan.ecommerce.system.EventBus;

@Component
public class DeliveryComponent {

    @Autowired
    EventBus eventBus;

    @Bean
    private OrderConfirmedListener orderConfirmedListener() {

        OrderConfirmedListener listener = new OrderConfirmedListener(new DumpToFileDeliveryMechanism());
        eventBus.addEventListener(OrderConfirmed.class, listener);

        return listener;
    }
}

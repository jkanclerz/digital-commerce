package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.sales.application.services.payment.Przelewy24PaymentConfirmation;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;
import pl.jkan.ecommerce.system.EventBus;

public class VerifyPaymentHandler {

    private Przelewy24PaymentConfirmation paymentConfirmation;
    private OrderRepository orderRepository;
    private EventBus eventBus;


    public VerifyPaymentHandler(Przelewy24PaymentConfirmation paymentConfirmation, OrderRepository orderRepository, EventBus eventBus) {
        this.paymentConfirmation = paymentConfirmation;
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    public void handle(VerifyPaymentCommand command) {
        Order order = orderRepository.loadByPayment(command.getPaymentIdentifier());

        paymentConfirmation.verifyPayment(order.getPayment(), command.getCheckSum(), command.getOrderId());

        order.confirm();

        eventBus.dispatch(new OrderConfirmed(Identifier.generateUUID()));
    }
}

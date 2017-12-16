package pl.jkan.ecommerce.sales.application;

import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;

public class ConfirmOrderHandler {

    private OrderRepository orderRepository;

    public ConfirmOrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void handle(ConfirmOrderCommand command) {
        orderRepository.add(new Order(command.getOrderId()));
    }
}

package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.sales.domain.order.*;
import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.offer.Offer;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.system.SystemUserContext;

public class ConfirmOrderHandler {

    private OrderRepository orderRepository;
    private OfferMaker offerMaker;
    private BasketStorage basketStorage;
    private SystemUserContext systemUserContext;
    private OrderFactory orderFactory;
    private ClientInformation clientInformation;

    public ConfirmOrderHandler(OrderRepository orderRepository, OfferMaker offerMaker, BasketStorage basketStorage, SystemUserContext systemUserContext, OrderFactory orderFactory, ClientInformation clientInformation) {
        this.orderRepository = orderRepository;
        this.offerMaker = offerMaker;
        this.basketStorage = basketStorage;
        this.systemUserContext = systemUserContext;
        this.orderFactory = orderFactory;
        this.clientInformation = clientInformation;
    }

    public void handle(ConfirmOrderCommand command) {

        Basket basket = basketStorage.loadForCustomer(systemUserContext.getCurrentUser().getId());

        Offer offer = offerMaker.calculateOffer(basket.getReservedProducts());

        Order order = orderFactory.create(command.getOrderId(), offer, loadClientData());

        orderRepository.add(order);
    }

    private ClientData loadClientData() {
        return clientInformation.getDataForClient(systemUserContext.getCurrentUser().getId());
    }
}

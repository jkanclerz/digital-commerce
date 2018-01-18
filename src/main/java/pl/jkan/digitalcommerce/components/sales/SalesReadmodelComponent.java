package pl.jkan.digitalcommerce.components.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;
import pl.jkan.ecommerce.sales.domain.payment.PaymentGateway;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferFinder;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.InMemoryProductFinder;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.ProductFinder;
import pl.jkan.ecommerce.sales.readmodel.readmodel.PaymentURLs;
import pl.jkan.ecommerce.system.SystemUserContext;

@Component
public class SalesReadmodelComponent {
    @Autowired
    BasketStorage basketStorage;

    @Autowired
    OfferMaker offerMaker;

    @Autowired
    SystemUserContext systemUserContext;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentGateway paymentGateway;

    @Bean
    OfferFinder offerFinder() {
        return new OfferFinder(
            basketStorage,
            offerMaker,
            systemUserContext
        );
    }

    @Bean
    ProductFinder productFinder() {
        return new InMemoryProductFinder();
    }

    @Bean
    PaymentURLs paymentURLs() {
        return new PaymentURLs(
            paymentGateway,
            orderRepository
        );
    }
}

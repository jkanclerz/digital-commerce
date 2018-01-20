package pl.jkan.digitalcommerce.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.api.AddToBasketHandler;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketStorage;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryProductCatalog;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferFinder;
import pl.jkan.ecommerce.system.SystemUserContext;

@Component
public class SaleComponent {

    @Autowired SystemUserContext systemUserContext;

    @Autowired
    private BasketStorage basketStorage;

    @Autowired
    private ProductCatalog productCatalog;

    @Autowired
    private OfferMaker offerMaker;

    @Bean
    private AddToBasketHandler addToBasketHandler() {
        return new AddToBasketHandler(
            systemUserContext,
            basketStorage,
            productCatalog
        );
    }

    @Bean
    private OfferFinder offerFinder() {
        return new OfferFinder(
            basketStorage,
            offerMaker,
            systemUserContext
        );
    }

    @Bean
    private OfferMaker getOfferMaker() {
        return new OfferMaker();
    }

    @Component
    private class SalesInfrastructure {
        @Bean
        private InMemoryBasketStorage getBasketStorage() {
            return new InMemoryBasketStorage();
        }

        @Bean
        private ProductCatalog getProductCatalog() {
            ProductCatalog pc = new InMemoryProductCatalog();
            pc.add(new Product(new Identifier("p1"), 10, 10.00));
            pc.add(new Product(new Identifier("p2"), 10, 13.50));
            pc.add(new Product(new Identifier("p3"), 0, 10.00));

            return pc;
        }
    }
}

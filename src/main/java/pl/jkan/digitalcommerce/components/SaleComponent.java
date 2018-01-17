package pl.jkan.digitalcommerce.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.api.AddToBasketHandler;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketStorage;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryProductCatalog;
import pl.jkan.ecommerce.system.SystemUserContext;
import pl.jkan.ecommerce.system.infrastructure.InMemorySystemUserContext;

@Component
public class SaleComponent {

    @Bean
    private AddToBasketHandler addToBasketHandler() {
        return new AddToBasketHandler(
            systemUserContext(),
            new InMemoryBasketStorage(),
            getProductCatalog()
        );
    }

    @Bean
    private ProductCatalog getProductCatalog() {
        ProductCatalog pc = new InMemoryProductCatalog();
        pc.add(new Product(new Identifier("p1"), 10));
        pc.add(new Product(new Identifier("p2"), 10));
        pc.add(new Product(new Identifier("p3"), 0));

        return pc;
    }

    @Bean
    private SystemUserContext systemUserContext() {
        InMemorySystemUserContext systemUserContext = new InMemorySystemUserContext();
        systemUserContext.authenticate(new Identifier("user_1"));

        return systemUserContext;
    }


}

package pl.jkan.ecommerce.sales.functional;

import org.junit.Assert;
import org.junit.Test;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.Basket;
import pl.jkan.ecommerce.sales.domain.Product;
import pl.jkan.ecommerce.sales.application.AddToBasketCommand;
import pl.jkan.ecommerce.sales.application.AddToBasketHandler;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketStorage;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryProductCatalog;

public class OrderingTest {

    private final InMemoryProductCatalog productCatalog;
    private final AddToBasketHandler addToBasketHandler;
    private final InMemoryBasketStorage basketStorage;
    private InMemorySystemUserContext systemUserContext;

    public OrderingTest() {
        this.systemUserContext = new InMemorySystemUserContext();
        this.productCatalog = new InMemoryProductCatalog();
        this.basketStorage = new InMemoryBasketStorage();
        this.addToBasketHandler = new AddToBasketHandler(
            systemUserContext,
            basketStorage,
            productCatalog
        );
    }

    @Test
    public void itAllowToSelectProduct() {
        iAmGuestBuyerIdentifiedWith(new Identifier("customer_1"));
        thereIsProductIdentifiedWith(new Identifier("p1"));
        thereIsProductIdentifiedWith(new Identifier("p2"));

        chooseProduct(new Identifier("p1"));
        chooseProduct(new Identifier("p2"));

        shoppingListForCustomerContainsProduct(new Identifier("customer_1"), new Identifier("p1"));
        shoppingListForCustomerContainsProduct(new Identifier("customer_1"), new Identifier("p2"));
    }

    private void shoppingListForCustomerContainsProduct(Identifier customer, Identifier product) {
        Basket basket = this.basketStorage.loadForCustomer(customer);

        Assert.assertFalse("Basket should contains products", basket.getReservedProducts().isEmpty());

        basket.getReservedProducts()
            .stream()
            .filter(basketItem -> basketItem.getId().equals(product))
            .forEach(basketItem -> Assert.assertTrue(basketItem.getId().equals(product)))
        ;
    }

    private void chooseProduct(Identifier p1) {
        addToBasketHandler.handle(new AddToBasketCommand(p1));
    }

    private void thereIsProductIdentifiedWith(Identifier p) {
        productCatalog.add(new Product(p, 10));
    }

    private void iAmGuestBuyerIdentifiedWith(Identifier id) {
        systemUserContext.authenticate(id);
    }
}

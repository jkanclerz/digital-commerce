package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketStorage;
import pl.jkan.ecommerce.sales.domain.basket.ItemAdded;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;
import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.ecommerce.system.SystemUserContext;

public class AddToBasketHandler {

    private SystemUserContext systemUserContext;
    private BasketStorage basketStorage;
    private ProductCatalog productCatalog;
    private EventBus eventBus;

    public AddToBasketHandler(SystemUserContext systemUserContext, BasketStorage basketStorage, ProductCatalog productCatalog, EventBus eventBus) {
        this.systemUserContext = systemUserContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
        this.eventBus = eventBus;
    }

    public void handle(AddToBasketCommand command) {
        Basket basket = basketStorage.loadForCustomer(systemUserContext.getCurrentUser().getId());

        Product product = productCatalog.load(command.getProductId());

        basket.add(product);

        basketStorage.store(systemUserContext.getCurrentUser().getId(), basket);

        eventBus.dispatch(new ItemAdded());
    }
}

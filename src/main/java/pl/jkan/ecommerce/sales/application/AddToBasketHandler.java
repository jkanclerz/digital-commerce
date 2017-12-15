package pl.jkan.ecommerce.sales.application;

import pl.jkan.ecommerce.sales.domain.Basket;
import pl.jkan.ecommerce.sales.domain.BasketStorage;
import pl.jkan.ecommerce.sales.domain.Product;
import pl.jkan.ecommerce.sales.domain.ProductCatalog;
import pl.jkan.ecommerce.system.SystemUserContext;

public class AddToBasketHandler {

    private SystemUserContext systemUserContext;
    private BasketStorage basketStorage;
    private ProductCatalog productCatalog;

    public AddToBasketHandler(SystemUserContext systemUserContext, BasketStorage basketStorage, ProductCatalog productCatalog) {
        this.systemUserContext = systemUserContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
    }

    public void handle(AddToBasketCommand command) {
        Basket basket = basketStorage.loadForCustomer(systemUserContext.getCurrentUser().getId());

        Product product = productCatalog.load(command.getProductId());

        basket.add(product);

        basketStorage.store(systemUserContext.getCurrentUser().getId(), basket);
    }
}

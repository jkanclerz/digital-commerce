package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.ClientData;
import pl.jkan.ecommerce.sales.domain.basket.BasketItem;

import java.util.List;

public class OrderFactory {
    public Order create(Identifier id, List<BasketItem> selectedItems, ClientData clientData) {

        if (selectedItems.isEmpty()) {
            throw new OrderMustContainsItemsException();
        }

        return null;
    }
}

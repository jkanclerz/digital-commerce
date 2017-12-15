package pl.jkan.ecommerce.sales.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class BasketItem {
    private Identifier id;
    private Integer quantity;

    public BasketItem(Identifier id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Identifier getId() {
        return id;
    }
}

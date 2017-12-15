package pl.jkan.ecommerce.sales.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class Product {

    private Identifier id;
    private final Integer stockQty;

    public Product(Identifier id, Integer stockQty) {
        this.id = id;
        this.stockQty = stockQty;
    }

    public Product(Identifier id) {
        this.stockQty = 0;
    }

    public boolean isInStock() {
        return stockQty > 0;
    }

    public Identifier getId() {
        return id;
    }
}

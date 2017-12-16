package pl.jkan.ecommerce.sales.domain.productcatalog;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class ProductData {
    private Identifier identifier;
    private String name;
    private Double basePrice;

    public ProductData(Identifier identifier, String name, Double basePrice) {
        this.identifier = identifier;
        this.name = name;
        this.basePrice = basePrice;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}

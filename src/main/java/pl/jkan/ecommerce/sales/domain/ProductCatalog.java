package pl.jkan.ecommerce.sales.domain;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public interface ProductCatalog {
    public Product load(Identifier id);

    public void add(Product product);
}

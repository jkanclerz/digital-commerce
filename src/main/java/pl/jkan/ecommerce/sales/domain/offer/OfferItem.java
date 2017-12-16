package pl.jkan.ecommerce.sales.domain.offer;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductData;

public class OfferItem {
    private ProductData productData;
    private Integer quantity;
    private Double totalCost;

    public OfferItem(ProductData productData, Integer quantity, Double totalCost) {
        this.productData = productData;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public Identifier getProductId() {
        return productData.getIdentifier();
    }

    public ProductData getProductData() {
        return productData;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalCost() {
        return totalCost;
    }
}

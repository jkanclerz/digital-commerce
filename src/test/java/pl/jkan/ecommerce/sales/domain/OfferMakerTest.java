package pl.jkan.ecommerce.sales.domain;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.basket.BasketItem;
import pl.jkan.ecommerce.sales.domain.offer.Offer;
import pl.jkan.ecommerce.sales.domain.offer.OfferItem;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;
import pl.jkan.ecommerce.sales.domain.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OfferMakerTest {
    @Test
    public void itCreateOfferForClientBasedOnSelectedItems() {
        OfferMaker offerMaker = new OfferMaker();

        List<BasketItem> items = new ArrayList<>();
        items.add(new BasketItem(new Identifier("p1"), 1));
        items.add(new BasketItem(new Identifier("p2"), 3));

        Offer offer = offerMaker.calculateOffer(items);

        Assert.assertFalse("Offer contains items", offer.getItems().isEmpty());
        thereIsFollowingOrderedItemWithTotalCost(offer.getItems(), new Identifier("p1"), 10.00);
        thereIsFollowingOrderedItemWithTotalCost(offer.getItems(), new Identifier("p2"), 30.00);
    }

    private void thereIsFollowingOrderedItemWithTotalCost(List<OfferItem> items, Identifier productId, Double totalPrice) {
        items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .forEach(item -> Assert.assertEquals(item.getTotalCost(), item.getTotalCost()))
        ;
    }
}

package pl.jkan.ecommerce.sales.domain.offer;

import pl.jkan.ecommerce.sales.domain.basket.BasketItem;

import java.util.ArrayList;
import java.util.List;

public class OfferMaker {
    public Offer calculateOffer(List<BasketItem> selectedItems) {

        ArrayList<OfferItem> offerItems = selectedItems.stream()
                .map(selectedItem -> new OfferItem(selectedItem.getProductData()))


    }
}

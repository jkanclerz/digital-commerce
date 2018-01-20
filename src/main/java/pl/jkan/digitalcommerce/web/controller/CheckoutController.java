package pl.jkan.digitalcommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferDto;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferFinder;

@RestController
public class CheckoutController {

    @Autowired
    private OfferFinder offerMaker;

    @RequestMapping("/offer")
    @ResponseBody
    public OfferDto offer() {
        return offerMaker.currentOffer();
    }

}
package pl.jkan.digitalcommerce.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.sales.domain.offer.OfferMaker;

@Component
public class OfferMakerConent {
    @Bean
    private OfferMaker getOfferMaker() {
        return new OfferMaker();
    }
}

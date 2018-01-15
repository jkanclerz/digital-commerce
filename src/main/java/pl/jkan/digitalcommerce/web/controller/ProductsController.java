package pl.jkan.digitalcommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import pl.jkan.digitalcommerce.web.model.ProductDto;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.AddToBasketCommand;
import pl.jkan.ecommerce.sales.application.AddToBasketHandler;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductNotAvailableException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private AddToBasketHandler addToBasketHandler;

    @RequestMapping("/products")
    @ResponseBody
    public List<ProductDto> products() {
        List<ProductDto> productsList = new ArrayList<>();
        productsList.add(new ProductDto("p1", "Battlefield", "products/battlefield.png", 10.50, "No battle is ever the same"));
        productsList.add(new ProductDto("p2", "Diablo III", "products/diablo.png", 12.50, "The demonically-besieged world of Sanctuary needs heroes"));
        productsList.add(new ProductDto("p3", "Starcraft II", "products/starcraft.png", 13.50, "Wage war across the galaxy with three unique and powerful races"));

        return productsList;
    }

    @RequestMapping("/add-to-basket/{id}")
    public String addToBasket(@PathVariable(value="id") String id) {
        try {
            addToBasketHandler.handle(new AddToBasketCommand(new Identifier(id)));
            return "OK";
        } catch (ProductNotAvailableException e) {
            return "Something is not YES!!!!!";
        }
    }
}
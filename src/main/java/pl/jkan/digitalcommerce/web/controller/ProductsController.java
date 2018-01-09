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
        productsList.add(new ProductDto("p1", "Cat 1", "images/cat1.jpeg", 10.00));
        productsList.add(new ProductDto("p2", "Cat 2", "images/cat1.jpeg", 12.00));
        productsList.add(new ProductDto("p3", "Cat 3", "images/cat1.jpeg", 13.00));

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
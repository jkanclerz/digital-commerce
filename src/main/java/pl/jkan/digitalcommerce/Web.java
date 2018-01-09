package pl.jkan.digitalcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.jkan.digitalcommerce.web.controller.ProductsController;

@SpringBootApplication
@ComponentScan({
    "pl.jkan.digitalcommerce.components",
    "pl.jkan.digitalcommerce.web"
})
public class Web {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Web.class, args);
    }
}

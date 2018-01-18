package pl.jkan.ecommerce.integration.sales.readmodel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import pl.jkan.ecommerce.integration.AdditionalTestConfig;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.ProductDto;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.ProductFinder;
import pl.jkan.ecommerce.sales.readmodel.productcatalog.SQLProductFinder;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdditionalTestConfig.class)
public class SQLProductFinderTest {

    ProductFinder productFinder;

    @Autowired
    JdbcTemplate db;

    @Before
    public void before() {
        productFinder = getProductFinder();
    }

    @Test
    public void itAllowLoadProductFromJpaRepo() {

        thereIsProduct("lego-1", 10, 10.20, "Lego 9030", "nice building blocks", "image1.jpeg");
        thereIsProduct("lego-2", 12, 12.20, "Lego 9230", "nice building blocks", "image2.jpeg");

        List<ProductDto> products = productFinder.all();

        Assert.assertFalse(products.isEmpty());

        ProductDto product = products.get(1);

        Assert.assertEquals("lego-2", product.getId());
        Assert.assertEquals( "image2.jpeg", product.getImage());
        Assert.assertEquals("nice building blocks", product.getDescription());
        Assert.assertEquals("Lego 9230", product.getName());
        Assert.assertTrue(12.20 == product.getPrice());
    }

    private ProductFinder getProductFinder() {
        db.execute("delete from ProductCatalog");
        db.execute("delete from ProductDetails");

        return new SQLProductFinder(db);
    }

    private void thereIsProduct(String id, Integer stockQty, Double price, String name, String desc, String image) {
        db.update(
            "INSERT INTO ProductCatalog(id, stock_qty, price) VALUES (?,?,?)",
            new Object[]{id, stockQty, price}
        );

        db.update(
                "INSERT INTO ProductDetails(id, name, description, image) VALUES (?,?,?,?)",
                new Object[]{id, name, desc, image}
        );
    }
}

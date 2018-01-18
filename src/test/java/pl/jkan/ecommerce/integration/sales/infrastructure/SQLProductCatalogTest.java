package pl.jkan.ecommerce.integration.sales.infrastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.integration.AdditionalTestConfig;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;
import pl.jkan.ecommerce.sales.infrastructure.productcatalog.SQLProductCatalog;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdditionalTestConfig.class)
public class SQLProductCatalogTest {

    ProductCatalog productCatalog;

    @Autowired
    JdbcTemplate db;

    @Before
    public void before() {
        productCatalog = getProductCatalog();
    }

    @Test
    public void itAllowLoadProductFromJpaRepo() {

        thereIsProduct(new Product(Identifier.byString("lego-1"), 10, 10.20));
        thereIsProduct(new Product(Identifier.byString("lego-2"), 10, 12.20));

        Product product = productCatalog.load(new Identifier("lego-1"));

        Assert.assertNotNull(product);
        Assert.assertTrue(product.getId().equals(new Identifier("lego-1")));
        Assert.assertTrue(product.getPrice() == 10.20);
        Assert.assertTrue(product.isInStock());
    }

    private ProductCatalog getProductCatalog() {
        db.execute("delete from ProductCatalog");
        return new SQLProductCatalog(db);
    }

    private void thereIsProduct(Product product) {
        this.productCatalog.add(product);
    }
}

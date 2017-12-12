package pl.jkan.sales;

import org.junit.Test;
import org.junit.Assert;

public class BasketTest {

    @Test
    public void itAllowAddProductToBasket() {
        Product product1 = new Product("lego 8297", 10);
        Basket basket = new Basket();

        basket.add(product1);

        Assert.assertFalse(basket.isEmpty());
    }

    @Test
    public void itIsEmptyWhenNew() {
        Basket basket = new Basket();
        Assert.assertTrue(basket.isEmpty());
    }

    @Test
    public void itAllowAddMultipleProduct() {
        Product product1 = new Product("lego 8297", 10);
        Product product2 = new Product("lego 9398", 10);
        Basket basket = new Basket();

        basket.add(product1);
        basket.add(product2);

        Assert.assertEquals(2, basket.productsCount());
    }

    @Test
    public void itAllowAdd3Product() {
        Product product1 = new Product("lego 8297", 10);
        Product product2 = new Product("lego 9398", 10);
        Product product3 = new Product("lego 42070", 10);
        Basket basket = new Basket();

        basket.add(product1);
        basket.add(product2);
        basket.add(product3);

        Assert.assertEquals(3, basket.productsCount());
    }

    @Test
    public void itAllowAddMultipleProductSameType() {
        Product product1 = new Product("lego 9398", 10);
        Product product2 = new Product("lego 8297", 10);
        Basket basket = new Basket();

        basket.add(product1);
        basket.add(product1);
        basket.add(product1);
        basket.add(product2);

        Assert.assertEquals(2, basket.productsCount());
    }

    @Test
    public void itIncreaseQuantityForAlreadyAddedProduct() {
        Product product1 = new Product("lego 9398", 10);
        Product product2 = new Product("lego 8297", 10);
        Basket basket = new Basket();

        basket.add(product1);
        basket.add(product1);
        basket.add(product1);
        basket.add(product2);

        Assert.assertEquals(2, basket.productsCount());
        Assert.assertEquals(BasketItem.class, basket.getReservedProducts().get(0).getClass());
        Assert.assertEquals(3, (int) basket.getReservedProducts().get(0).getQuantity());
        Assert.assertEquals(1, (int) basket.getReservedProducts().get(1).getQuantity());

    }

    @Test
    public void itDoNotAllowToAddProductThatIsOutOfStock() {
        Product product1 = new Product("lego 9398", 0);
        Basket basket = new Basket();

        try {
            basket.add(product1);
            Assert.fail("Not available in stock exception should be thrown");
        } catch (ProductNotAvailableException e) {
            Assert.assertTrue(true);
        }
    }
}

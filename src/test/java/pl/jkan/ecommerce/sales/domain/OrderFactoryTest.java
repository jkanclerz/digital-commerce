package pl.jkan.ecommerce.sales.domain;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.basket.BasketItem;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderFactory;
import pl.jkan.ecommerce.sales.domain.order.OrderMustContainsItemsException;

import java.util.ArrayList;
import java.util.List;

public class OrderFactoryTest {

    @Test
    public void itVerifyIfThereAreItems() {
        OrderFactory orderFactory = new OrderFactory();

        List<BasketItem> selectedItems = new ArrayList<>();

        try {
            Order order = orderFactory.create(new Identifier("order_1"), selectedItems, exampleClientData());
            Assert.fail("Not allow to create order without items");
        } catch (OrderMustContainsItemsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void itCalculate() {
        OrderFactory orderFactory = new OrderFactory();

        List<BasketItem> selectedItems = new ArrayList<>();

        try {
            Order order = orderFactory.create(new Identifier("order_1"), selectedItems, exampleClientData());
            Assert.fail("Not allow to create order without items");
        } catch (OrderMustContainsItemsException e) {
            Assert.assertTrue(true);
        }
    }

    private ClientData exampleClientData() {
        ClientData client = new ClientData(new Identifier("jakub"), "jakub.kanclerz@gmail.com");

        return client;
    }
}

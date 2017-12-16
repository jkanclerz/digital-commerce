package pl.jkan.ecommerce.sales.domain.order;

import pl.jkan.ecommerce.canonicalmodel.Entity;
import pl.jkan.ecommerce.canonicalmodel.Identifier;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Order implements Entity {
    private Identifier id;

    private List<OrderItem> items;

    public Order(Identifier id, List<OrderItem> items) {
        this.id = id;
        this.items = items;
    }

    public Order(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    public Collection<OrderItem> getItems() {
        return (Collection<OrderItem>) Collections.unmodifiableCollection(items);
    }
}

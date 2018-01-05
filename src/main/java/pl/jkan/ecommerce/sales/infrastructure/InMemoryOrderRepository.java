package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderRepository;

public class InMemoryOrderRepository extends InMemoryRepository<Order> implements OrderRepository {

}

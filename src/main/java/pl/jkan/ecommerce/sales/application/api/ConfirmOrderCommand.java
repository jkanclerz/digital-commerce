package pl.jkan.ecommerce.sales.application.api;

import pl.jkan.ecommerce.canonicalmodel.Identifier;

public class ConfirmOrderCommand {

    private Identifier orderId;

    public ConfirmOrderCommand(Identifier orderId) {
        this.orderId = orderId;
    }

    public Identifier getOrderId() {
        return orderId;
    }
}

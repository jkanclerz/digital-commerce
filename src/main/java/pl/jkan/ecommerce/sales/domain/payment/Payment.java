package pl.jkan.ecommerce.sales.domain.payment;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.order.ClientData;

public class Payment {

    public enum PaymentStatus {
        PENDING,
        CONFIRMED;
    }

    private Identifier id;
    private ClientData clientData;
    private Double amount;
    private PaymentStatus status;

    public Payment(Identifier id, ClientData clientData, Double amount) {
        this.id = id;
        this.clientData = clientData;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    public static Payment createPayment(ClientData clientData, Double amount) {
        return new Payment(Identifier.generateUUID(), clientData, amount);
    }

    public boolean isPending() {
        return status == PaymentStatus.PENDING;
    }

    public Double getValue() {
        return amount;
    }
}

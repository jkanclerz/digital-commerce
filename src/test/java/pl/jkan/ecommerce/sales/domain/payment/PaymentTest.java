package pl.jkan.ecommerce.sales.domain.payment;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.order.ClientData;

public class PaymentTest {
    @Test
    public void itIsCreatedAsPending() {
        Payment p = Payment.createPayment(exampleClientData(), 123.45);

        Assert.assertTrue(p.isPending());
        Assert.assertFalse(p.isConfirmed());
    }

    @Test
    public void itCouldProjectValueIsSmallestUnit() {
        Payment p = Payment.createPayment(exampleClientData(), 123.45);

        Assert.assertTrue(12345 == p.expressValueInSmallestUnit());
    }

    private ClientData exampleClientData() {
        return new ClientData(new Identifier("c_1"), "kanclerj@uek.krakow.pl");
    }
}

package pl.jkan.ecommerce.functional;

import org.junit.Ignore;
import org.junit.Test;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentCommand;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentHandler;

public class VerifingPaymentTest {
    @Test
    @Ignore
    public void itAllowVerifyPayment() {
        VerifyPaymentCommand c = new VerifyPaymentCommand("p_id", "p24_order_id", "checksum");
    }
}

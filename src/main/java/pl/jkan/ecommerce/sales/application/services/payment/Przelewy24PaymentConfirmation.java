package pl.jkan.ecommerce.sales.application.services.payment;

import pl.jkan.ecommerce.sales.domain.payment.Payment;
import pl.jkan.przelewy24.InvalidRequestException;
import pl.jkan.przelewy24.Model.VerifyPaymentData;
import pl.jkan.przelewy24.Przelewy24Api;

public class Przelewy24PaymentConfirmation {
    private Przelewy24Api api;

    public Przelewy24PaymentConfirmation(Przelewy24Api api) {
        this.api = api;
    }

    public void verifyPayment(Payment payment, String checksum, String p24OrderId) {

        try {
            api.verifyPayment(new VerifyPaymentData(
                    payment.getId().toString(),
                    p24OrderId,
                    payment.expressValueInSmallestUnit()
            ));
        } catch (InvalidRequestException e) {
            throw new PaymentVerificationError();
        }
    }
}

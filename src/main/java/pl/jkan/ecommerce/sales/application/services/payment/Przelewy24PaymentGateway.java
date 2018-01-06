package pl.jkan.ecommerce.sales.application.services.payment;

import pl.jkan.przelewy24.Model.ApiResponse;
import pl.jkan.przelewy24.Model.RegisterPaymentData;
import pl.jkan.przelewy24.Przelewy24Api;
import pl.jkan.przelewy24.Przelewy24Properties;
import pl.jkan.ecommerce.sales.domain.payment.Payment;

import java.net.MalformedURLException;
import java.net.URL;

public class Przelewy24PaymentGateway {
    private Przelewy24Api api;

    public Przelewy24PaymentGateway(Przelewy24Api api) {
        this.api = api;
    }

    public String obtainPaymentURL(Payment payment) {

        ApiResponse response = api.registerPayment(new RegisterPaymentData(
                payment.getId().toString().concat(""),
                payment.expressValueInSmallestUnit(),
                payment.getClientData().getEmail(),
                "http://jkan.pl",
                "Zakup w sklepie UEK"
        ));

        return api.getPaymentUrl(response.getValue("token"));
    }
}

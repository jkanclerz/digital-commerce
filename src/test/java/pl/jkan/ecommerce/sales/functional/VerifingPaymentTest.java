package pl.jkan.ecommerce.sales.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.canonicalmodel.events.OrderConfirmed;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentCommand;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentHandler;
import pl.jkan.ecommerce.sales.domain.order.ClientData;
import pl.jkan.ecommerce.sales.domain.order.Order;
import pl.jkan.ecommerce.sales.domain.order.OrderItem;
import pl.jkan.ecommerce.sales.domain.payment.Payment;
import pl.jkan.ecommerce.sales.domain.payment.PaymentConfirmation;
import pl.jkan.ecommerce.sales.domain.payment.exception.PaymentVerificationError;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryOrderRepository;
import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.ecommerce.system.Subscriber;

import java.util.ArrayList;

public class VerifingPaymentTest {

    private InMemoryOrderRepository orderRepository;
    private SpyEventBus eventBus;
    private VerifyPaymentHandler verifyPaymentHandler;
    private FakePaymentConfirmation paymentConfirmation;

    @Before
    public void setUp() {
        this.orderRepository = new InMemoryOrderRepository();
        this.eventBus = new SpyEventBus();
        this.paymentConfirmation = new FakePaymentConfirmation(true);
        this.verifyPaymentHandler = new VerifyPaymentHandler(
                this.paymentConfirmation,
                orderRepository,
                eventBus
        );
    }

    @Test
    public void itInformSystemWhenPaymentVerifiedForOrder() {
        Identifier orderId = thereIsPendingOrderWithPayment("p_id");

        VerifyPaymentCommand c = new VerifyPaymentCommand("p_id", "p24_order_id", "checksum");
        this.verifyPaymentHandler.handle(c);

        Assert.assertNotNull(eventBus.lastEvent);
        Assert.assertNotNull(eventBus.lastEvent.getClass().equals(OrderConfirmed.class));

        OrderConfirmed e = (OrderConfirmed)eventBus.lastEvent;
        Assert.assertTrue(e.getOrderId().equals(orderId));
    }

    @Test
    public void itFinishWithExceptionWhenCantConfirm() {
        Identifier orderId = thereIsPendingOrderWithPayment("p_id");
        this.paymentConfirmation.paymentCantBeConfirmed();

        VerifyPaymentCommand c = new VerifyPaymentCommand("p_id", "p24_order_id", "checksum");

        try {
            this.verifyPaymentHandler.handle(c);
            Assert.fail("Should throw exception");
        } catch (RuntimeException e) {
            Assert.assertNull(eventBus.lastEvent);
        }
    }

    private Identifier thereIsPendingOrderWithPayment(String paymentId) {
        Identifier orderId = Identifier.generateUUID();
        orderRepository.add(
                new Order(
                        orderId,
                        new ArrayList<OrderItem>(),
                        new ClientData(Identifier.generateUUID(), ""),
                        new Payment(
                                new Identifier(paymentId),
                                new ClientData(Identifier.generateUUID(), ""),
                                20.00
                        )
                )
        );

        return orderId;
    }

    class FakePaymentConfirmation implements PaymentConfirmation {
        private Boolean valid;

        public FakePaymentConfirmation(Boolean valid) {
            this.valid = valid;
        }

        public void paymentCantBeConfirmed() {
            this.valid = false;
        }

        @Override
        public void verifyPayment(Payment payment, String checksum, String orderId) throws PaymentVerificationError {
            if (!valid) {
                throw new PaymentVerificationError();
            }
        }
    }

    class SpyEventBus implements EventBus {
        public Object lastEvent;
        @Override
        public void dispatch(Object object) {
            this.lastEvent = object;
        }

        @Override
        public void addEventListener(Class className, Subscriber subscriber) {

        }
    }
}

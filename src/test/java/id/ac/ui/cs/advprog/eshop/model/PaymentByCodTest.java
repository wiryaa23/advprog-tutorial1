package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCodTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testPaymentDataValid() {
        this.paymentData.put("address", "Jalan Pegangsaan Timur No. 56, Jakarta Pusat");
        this.paymentData.put("deliveryFee", "20000");
        PaymentByCodTest payment = new PaymentByCodTest("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.COD.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataEmptyAddress() {
        this.paymentData.put("address", "");
        this.paymentData.put("deliveryFee", "20000");
        PaymentByCodTest payment = new PaymentByCodTest("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.COD.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataEmptyDeliveryFee() {
        this.paymentData.put("address", "Jalan Pegangsaan Timur No. 56, Jakarta Pusat");
        this.paymentData.put("deliveryFee", "");
        PaymentByCodTest payment = new PaymentByCodTest("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.COD.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataEmpty() {
        PaymentByCodTest payment = new PaymentByCodTest("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.COD.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
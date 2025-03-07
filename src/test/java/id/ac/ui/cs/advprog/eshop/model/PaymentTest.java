package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String,String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentWithNullId() {
        assertThrows(NullPointerException.class, () -> {
            new Payment(null, "voucher", "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentWithNullMethod() {
        assertThrows(NullPointerException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb139d79b", null, "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentWithNullStatus() {
        assertThrows(NullPointerException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb139d79b", "voucher", null, paymentData);
        });
    }

    @Test
    void testCreatePaymentWithNullPaymentData() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb139d79b", "voucher", "SUCCESS", null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb139d79b",
                "voucher",
                "SUCCESS",
                paymentData
        );
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "13652556-012a-4c07-b546-54eb139d79b",
                    "by-voucher",
                    "OKEGAS",
                    paymentData
            );
        });
    }

    @Test
    void testCreatePaymentValidMethod() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb139d79b",
                "voucher",
                "SUCCESS",
                paymentData
        );
        assertEquals("voucher", payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        paymentData.put("leaf", "081812345678");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "13652556-012a-4c07-b546-54eb139d79b",
                    "leaf",
                    "SUCCESS",
                    paymentData);
        });
    }

    @Test
    void testSetInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb139d79b",
                "voucher",
                "SUCCESS",
                paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("TEST"));
    }

    @Test
    void testSetRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb139d79b",
                "voucher",
                "SUCCESS",
                paymentData
        );
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateAllValid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(
                "13652556-012a-4c07-b546-54eb139d79b",
                "voucher",
                "SUCCESS",
                paymentData
        );
        assertEquals("13652556-012a-4c07-b546-54eb139d79b", payment.getId());
        assertEquals("voucher", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }
}
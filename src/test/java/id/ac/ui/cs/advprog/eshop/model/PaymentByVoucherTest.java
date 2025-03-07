package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentByVoucherTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String, String>();
    }

    @Test
    void testPaymentDataEmpty() {
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testPaymentDataValid() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataTooShort() {
        this.paymentData.put("voucherCode", "ESHOP");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataNoEshop() {
        this.paymentData.put("voucherCode", "ASTG1234BNYK5678");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentDataNoEightNumbers() {
        this.paymentData.put("voucherCode", "BELUMDEADLINE123");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
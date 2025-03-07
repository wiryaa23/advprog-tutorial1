package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentByCod extends Payment {
    public PaymentByCod(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    public PaymentByCod(String id, String method, Map<String, String> paymentData) {
        super(id, method, PaymentStatus.PENDING.getValue(), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {

        if (paymentData.isEmpty() || paymentData.get("address").isEmpty() || paymentData.get("deliveryFee").isEmpty()) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.paymentData = paymentData;
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }
}
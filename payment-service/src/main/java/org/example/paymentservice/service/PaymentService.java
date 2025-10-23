package org.example.paymentservice.service;

import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {
    Payment createPayment(String orderId);
    Payment updateStatus(String paymentId, PaymentStatus status);
    List<Payment> getPaymentsByUser(Integer userId);
}

package org.example.paymentservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.ResponseDTO;
import org.example.paymentservice.dto.response.OrderResponse;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.enums.PaymentStatus;
import org.example.paymentservice.feign.OrderServiceFeignClient;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderServiceFeignClient orderServiceFeignClient;
    private final ObjectMapper objectMapper;
    @Override
    @Transactional
    public Payment createPayment(String orderId) {
        ResponseDTO responseDTO = orderServiceFeignClient.getOrderById(orderId);
        OrderResponse orderResponse = objectMapper.convertValue(responseDTO.getData(), OrderResponse.class);
        Payment payment = Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .orderId(orderId)
                .userId(orderResponse.getUserId())
                .amount(orderResponse.getTotalPrice())
                .status(PaymentStatus.PENDING)
                .createdAt(new Date())
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public Payment updateStatus(String paymentId, PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByUser(Integer userId) {
        return paymentRepository.findByUserId(userId);
    }
}

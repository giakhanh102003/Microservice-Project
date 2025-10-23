package org.example.paymentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.ResponseDTO;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.enums.PaymentStatus;
import org.example.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;
    @PostMapping("/create/{orderId}")
    public ResponseEntity<?> createPayment(@PathVariable String orderId) {
        Payment payment = paymentService.createPayment(orderId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .msg("Tạo payment thành công")
                .data(objectMapper.valueToTree(payment))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
    @PatchMapping("/{paymentId}/status")
    public ResponseEntity<?> updatePaymentStatus(
            @PathVariable String paymentId,
            @RequestParam PaymentStatus status
    ) {
        Payment updated = paymentService.updateStatus(paymentId, status);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .msg("Cập nhật trạng thái payment thành công")
                .data(objectMapper.valueToTree(updated))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPaymentsByUser(@PathVariable Integer userId) {
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .msg("Danh sách payments của user")
                .data(objectMapper.valueToTree(payments))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}

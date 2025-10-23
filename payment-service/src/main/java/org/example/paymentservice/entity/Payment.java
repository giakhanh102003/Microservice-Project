package org.example.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.paymentservice.enums.PaymentStatus;


import java.util.Date;

@Entity
@Table(name = "PAYMENT_TABLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @Column(name = "PAYMENT_ID")
    private String paymentId;

    @Column(name = "ORDER_ID", nullable = false)
    private String orderId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS", nullable = false)
    private PaymentStatus status;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}

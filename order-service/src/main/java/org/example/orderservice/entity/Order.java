package org.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.example.orderservice.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS_TABLE")
public class Order {

    @Id
    @Column(name = "ORDER_ID", nullable = false, length = 50)
    private String orderId;

    // ⚠️ "USER" là keyword trong Oracle → đổi thành CUSTOMER_ID
    @Column(name = "CUSTOMER_ID", nullable = false, length = 50)
    private Integer userId;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double price;

    // ⚠️ ORDER_STATUS vẫn an toàn vì có prefix, giữ nguyên
    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS", nullable = false, length = 50)
    private OrderStatus status;

    @Column(name = "CREATED_AT", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}

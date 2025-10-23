package org.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDER_ITEM_TABLE")
public class OrderItem {

    @Id
    @Column(name = "ITEM_ID", length = 50)
    private String id;

    @Column(name = "PRODUCT_ID", nullable = false, length = 100)
    private String productId;

    @Column(name = "ITEM_QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "ITEM_SIZE", nullable = false, length = 100)
    private String size;

    @Column(name = "ITEM_COLOR", nullable = false, length = 100)
    private String color;

    @Column(name = "ITEM_PRICE", nullable = false)
    private Double price;

    // ⚠️ ORDER_REF chứa từ "ORDER" → đổi thành ORDER_REFERENCE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_REFERENCE", referencedColumnName = "ORDER_ID")
    private Order order;
}

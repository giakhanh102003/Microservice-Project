package org.example.orderservice.dto.request;

import lombok.Data;
import org.example.orderservice.enums.OrderStatus;

@Data
public class UpdateStatusOrderRequest {
    private OrderStatus status;
}

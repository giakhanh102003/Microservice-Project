package org.example.orderservice.mapper;

import org.example.orderservice.dto.response.OrderResponse;
import org.example.orderservice.entity.Order;
import org.example.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(target = "totalPrice", source = "price")
    @Mapping(target = "items", source = "orderItems", qualifiedByName = "mapItems")
    OrderResponse toResponse(Order order);

    // Chuyển danh sách Order -> danh sách DTO
    List<OrderResponse> toResponseList(List<Order> orders);

    @Named("mapItems")
    default List<OrderResponse.OrderItemResponse> mapItems(List<OrderItem> items) {
        if (items == null) return null;
        return items.stream().map(i -> OrderResponse.OrderItemResponse.builder()
                .productId(i.getProductId())
                .quantity(i.getQuantity())
                .price(i.getPrice())
                .build()
        ).collect(Collectors.toList());
    }
}

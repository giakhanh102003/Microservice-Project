package org.example.orderservice.enums;

public enum OrderStatus {
    PENDING,        // Đơn hàng mới tạo, chưa xử lý
    PROCESSING,     // Đang xử lý (chuẩn bị hàng, đóng gói, ...)
    SHIPPED,        // Đã giao cho đơn vị vận chuyển
    COMPLETED,      // Giao hàng thành công
    CANCELLED       // Bị hủy
}

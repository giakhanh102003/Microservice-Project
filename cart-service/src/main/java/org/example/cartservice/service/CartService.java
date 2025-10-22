package org.example.cartservice.service;

import org.example.cartservice.dto.request.AddItemRequest;
import org.example.cartservice.dto.request.RemoveItemRequest;
import org.example.cartservice.entity.Cart;

public interface CartService {
    Cart getCart(String userId);
    Cart addItem(String userId, AddItemRequest request);
    Cart removeItem(String userId, RemoveItemRequest request);
}

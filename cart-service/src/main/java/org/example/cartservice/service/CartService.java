package org.example.cartservice.service;

import org.example.cartservice.dto.request.AddItemRequest;
import org.example.cartservice.dto.request.RemoveItemRequest;
import org.example.cartservice.entity.Cart;

public interface CartService {
    Cart getCart(Integer userId);
    Cart addItem(Integer userId, AddItemRequest request);
    Cart removeItem(Integer userId, RemoveItemRequest request);
}

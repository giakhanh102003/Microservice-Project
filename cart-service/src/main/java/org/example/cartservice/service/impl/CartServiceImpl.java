package org.example.cartservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cartservice.dto.request.AddItemRequest;
import org.example.cartservice.dto.request.RemoveItemRequest;
import org.example.cartservice.entity.Cart;
import org.example.cartservice.repository.CartRepository;
import org.example.cartservice.service.CartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CART_CACHE_KEY = "CART:";
    @Override
    public Cart getCart(String userId) {
        Cart cart = (Cart) redisTemplate.opsForValue().get(CART_CACHE_KEY + userId);
        if (cart != null) return cart;

        cart = cartRepository.findByUserId(userId).orElse(new Cart(null, userId, new ArrayList<>()));
        redisTemplate.opsForValue().set(CART_CACHE_KEY + userId, cart);
        return cart;
    }

    @Override
    @Transactional
    public Cart addItem(String userId, AddItemRequest request) {
        Cart cart = getCart(userId);

        Optional<Cart.CartItem> existingItem = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(request.getProductId())
                        && i.getSize().equals(request.getSize())
                        && i.getColor().equals(request.getColor()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + request.getQuantity());
        } else {
            Cart.CartItem newItem = Cart.CartItem.builder()
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .size(request.getSize())
                    .color(request.getColor())
                    .build();
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
        redisTemplate.opsForValue().set(CART_CACHE_KEY + userId, cart);
        return cart;
    }

    @Override
    public Cart removeItem(String userId, RemoveItemRequest request) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(i -> i.getProductId().equals(request.getProductId())
                && i.getSize().equals(request.getSize())
                && i.getColor().equals(request.getColor()));
        cartRepository.save(cart);
        redisTemplate.opsForValue().set(CART_CACHE_KEY + userId, cart);
        return cart;
    }

}

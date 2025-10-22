package org.example.cartservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.cartservice.dto.ResponseDTO;
import org.example.cartservice.dto.request.AddItemRequest;
import org.example.cartservice.dto.request.RemoveItemRequest;
import org.example.cartservice.entity.Cart;
import org.example.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ObjectMapper objectMapper;
    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable String userId) {
        Cart cart = cartService.getCart(userId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(cart))
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/{userId}/items")
    public ResponseEntity<?> addItem(@PathVariable String userId, @RequestBody AddItemRequest request) {
        Cart cart = cartService.addItem(userId, request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(cart))
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping
    public ResponseEntity<?> removeItem(@PathVariable String userId, @RequestBody RemoveItemRequest request) {
        Cart cart = cartService.removeItem(userId, request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .msg("item removed")
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}

package org.example.catalogservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.ResponseDTO;
import org.example.catalogservice.dto.size.request.CreateSizeRequest;
import org.example.catalogservice.entity.Size;
import org.example.catalogservice.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createSize(@RequestBody CreateSizeRequest request) {
        Size savedSize = sizeService.createSize(request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(savedSize))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}

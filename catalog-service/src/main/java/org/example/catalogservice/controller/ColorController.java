package org.example.catalogservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.ResponseDTO;
import org.example.catalogservice.dto.color.request.CreateColorRequest;
import org.example.catalogservice.entity.Color;
import org.example.catalogservice.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/create")
    public ResponseEntity<?> createColor(@RequestBody CreateColorRequest request) {
        Color savedColor = colorService.createColor(request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(savedColor))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}

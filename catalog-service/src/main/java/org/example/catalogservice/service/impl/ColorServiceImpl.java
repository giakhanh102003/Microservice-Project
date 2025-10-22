package org.example.catalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.color.request.CreateColorRequest;
import org.example.catalogservice.entity.Color;
import org.example.catalogservice.repository.ColorRepository;
import org.example.catalogservice.service.ColorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    @Override
    public Color createColor(CreateColorRequest request) {
        Color color = new Color();
        color.setName(request.getColorName());
        return colorRepository.save(color);
    }
}

package org.example.catalogservice.service;

import org.example.catalogservice.dto.color.request.CreateColorRequest;
import org.example.catalogservice.entity.Color;

public interface ColorService {
    Color createColor(CreateColorRequest request);
}

package org.example.catalogservice.service;

import org.example.catalogservice.dto.size.request.CreateSizeRequest;
import org.example.catalogservice.entity.Size;

public interface SizeService {
    Size createSize(CreateSizeRequest request);
}

package org.example.catalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.size.request.CreateSizeRequest;
import org.example.catalogservice.entity.Size;
import org.example.catalogservice.repository.SizeRepository;
import org.example.catalogservice.service.SizeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    @Override
    public Size createSize(CreateSizeRequest request) {
        Size size = new Size();
        size.setName(request.getSizeName());
        return sizeRepository.save(size);
    }
}

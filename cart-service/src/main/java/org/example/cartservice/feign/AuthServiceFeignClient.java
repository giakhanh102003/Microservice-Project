package org.example.cartservice.feign;

import org.example.cartservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthServiceFeignClient {
    @GetMapping("/auth/users/{id}/exists")
    ResponseDTO checkUserExists(@PathVariable("id") Integer userId);
}

package org.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Integer code;

    private String msg;

    private JsonNode data;

    private Meta meta;

    Map<String, String> failureMap;

    @Data
    @Builder
    public static class Meta {
        long total;

        int page;

        @JsonProperty("page_of_number")
        int pageOfNumber;
    }
}
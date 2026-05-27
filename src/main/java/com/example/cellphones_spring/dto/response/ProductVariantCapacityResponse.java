package com.example.cellphones_spring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductVariantCapacityResponse {
    private List<String> capacity;

}

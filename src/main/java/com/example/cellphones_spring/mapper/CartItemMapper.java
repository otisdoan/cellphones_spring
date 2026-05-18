package com.example.cellphones_spring.mapper;

import com.example.cellphones_spring.dto.response.CartItemResponse;
import com.example.cellphones_spring.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemResponse toResponse(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        return CartItemResponse.builder()
                .id(cartItem.getId())
                .userId(cartItem.getUser() != null ? cartItem.getUser().getId() : null)
                .productId(cartItem.getProduct() != null ? cartItem.getProduct().getId() : null)
                .variantId(cartItem.getVariant() != null ? cartItem.getVariant().getId() : null)
                .quantity(cartItem.getQuantity())
                .build();
    }
}

package com.example.cellphones_spring.service;

import com.example.cellphones_spring.dto.request.CartItemCreationRequest;
import com.example.cellphones_spring.dto.request.CartItemUpdateRequest;
import com.example.cellphones_spring.dto.response.CartItemResponse;
import com.example.cellphones_spring.entity.CartItem;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductVariant;
import com.example.cellphones_spring.entity.User;
import com.example.cellphones_spring.exception.AppException;
import com.example.cellphones_spring.exception.ErrorCode;
import com.example.cellphones_spring.mapper.CartItemMapper;
import com.example.cellphones_spring.repository.CartItemRepository;
import com.example.cellphones_spring.repository.ProductRepository;
import com.example.cellphones_spring.repository.ProductVariantRepository;
import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CartItemMapper cartItemMapper;

    public List<CartItemResponse> getAll() {
        return cartItemRepository.findAll().stream()
                .map(cartItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CartItemResponse getById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_EXISTED));
        return cartItemMapper.toResponse(cartItem);
    }

    @Transactional
    public CartItemResponse create(CartItemCreationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ProductVariant variant = null;
        if (request.getVariantId() != null) {
            variant = productVariantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
        }

        // Check if an existing CartItem matches this user, product, and variant
        var existingCartItem = cartItemRepository.findByUserAndProductAndVariant(user, product, variant);

        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            cartItem = existingCartItem.get();
            int currentQty = cartItem.getQuantity() != null ? cartItem.getQuantity() : 0;
            int requestQty = request.getQuantity() != null ? request.getQuantity() : 1;
            cartItem.setQuantity(currentQty + requestQty);
        } else {
            cartItem = CartItem.builder()
                    .user(user)
                    .product(product)
                    .variant(variant)
                    .quantity(request.getQuantity() != null ? request.getQuantity() : 1)
                    .build();
        }

        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toResponse(cartItem);
    }

    @Transactional
    public CartItemResponse update(Long id, CartItemUpdateRequest request) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_EXISTED));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            cartItem.setUser(user);
        }

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
            cartItem.setProduct(product);
        }

        if (request.getVariantId() != null) {
            ProductVariant variant = productVariantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_EXISTED));
            cartItem.setVariant(variant);
        }

        if (request.getQuantity() != null) {
            cartItem.setQuantity(request.getQuantity());
        }

        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toResponse(cartItem);
    }

    @Transactional
    public void delete(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new AppException(ErrorCode.CART_ITEM_NOT_EXISTED);
        }
        cartItemRepository.deleteById(id);
    }

    public List<CartItemResponse> getByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId).stream()
                .map(cartItemMapper::toResponse)
                .collect(Collectors.toList());
    }
}

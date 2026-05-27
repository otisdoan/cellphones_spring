package com.example.cellphones_spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    PHONE_EXISTED(1003, "Phone number already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1004, "Email already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS(1008, "Invalid phone number or password", HttpStatus.UNAUTHORIZED),
    INVALID_GOOGLE_TOKEN(1009, "Invalid Google token", HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN(1010, "Invalid or expired refresh token", HttpStatus.UNAUTHORIZED),
    PAGE_SIZE_TOO_LARGE(1011, "Page size must not be greater than 50", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1012, "Category not existed", HttpStatus.NOT_FOUND),
    CATEGORY_SLUG_EXISTED(1013, "Category slug already exists", HttpStatus.BAD_REQUEST),
    BRAND_NOT_EXISTED(1014, "Brand not existed", HttpStatus.NOT_FOUND),
    BRAND_SLUG_EXISTED(1015, "Brand slug already exists", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1016, "Product not existed", HttpStatus.NOT_FOUND),
    PRODUCT_SLUG_EXISTED(1017, "Product slug already exists", HttpStatus.BAD_REQUEST),
    PRODUCT_VARIANT_NOT_EXISTED(1018, "Product variant not existed", HttpStatus.NOT_FOUND),
    PRODUCT_IMAGE_NOT_EXISTED(1019, "Product image not existed", HttpStatus.NOT_FOUND),
    PRODUCT_ATTRIBUTE_NOT_EXISTED(1020, "Product attribute not existed", HttpStatus.NOT_FOUND),
    PRODUCT_REVIEW_NOT_EXISTED(1021, "Product review not existed", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_EXISTED(1022, "Cart item not existed", HttpStatus.NOT_FOUND),
    USER_ADDRESS_NOT_EXISTED(1023, "User address not existed", HttpStatus.NOT_FOUND),
    COUPON_NOT_EXISTED(1024, "Coupon not existed", HttpStatus.NOT_FOUND),
    ORDER_NOT_EXISTED(1025, "Order not existed", HttpStatus.NOT_FOUND),
    ORDER_ITEM_NOT_EXISTED(1026, "Order item not existed", HttpStatus.NOT_FOUND),
    WAREHOUSE_NOT_EXISTED(1027, "Warehouse not existed", HttpStatus.NOT_FOUND),
    INVENTORY_NOT_EXISTED(1028, "Inventory not existed", HttpStatus.NOT_FOUND),
    NOTIFICATION_NOT_EXISTED(1029, "Notification not existed", HttpStatus.NOT_FOUND),
    CHAT_SESSION_NOT_EXISTED(1030, "Chat session not existed", HttpStatus.NOT_FOUND),
    CHAT_MESSAGE_NOT_EXISTED(1031, "Chat message not existed", HttpStatus.NOT_FOUND),
    CHAT_FEEDBACK_NOT_EXISTED(1032, "Chat feedback not existed", HttpStatus.NOT_FOUND),
    CHAT_ANALYTICS_NOT_EXISTED(1033, "Chat analytics not existed", HttpStatus.NOT_FOUND),
    PRODUCT_VARIANT_CAPACITY_EXCEEDED(1034, "Product variant capacity exceeded", HttpStatus.BAD_REQUEST)
    ;

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

}

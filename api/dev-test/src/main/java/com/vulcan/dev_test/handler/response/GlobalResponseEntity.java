package com.vulcan.dev_test.handler.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalResponseEntity {
    public static <T> ResponseEntity<SuccessResponse<T>> successResponse(
            String message,
            String code,
            T data,
            HttpStatus status
    ) {
        return new ResponseEntity<>(
                SuccessResponse.<T>builder()
                        .message(message)
                        .code(code)
                        .data(data)
                        .build(),
                status
        );
    }
}

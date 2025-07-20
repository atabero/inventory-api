package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.productstatuschangelog.ProductStatusChangeLogNotFoundException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductStatusChangeLogHandler {

    @ExceptionHandler(ProductStatusChangeLogNotFoundException.class)
    public ResponseEntity<ApiError> handleProductStatusChangeLogNotFound
            (ProductStatusChangeLogNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorUtil
                        .createError(HttpStatus.NOT_FOUND, "Producto no encontrado", ex.getMessage(), request));
    }
}

package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.product.CannotInactivateProductWithStockException;
import org.atabero.inventory.exception.product.ProductAlreadyDeactivatedException;
import org.atabero.inventory.exception.product.ProductNotFoundException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorUtil.createError(HttpStatus.NOT_FOUND, "Producto no encontrado", ex.getMessage(), request));
    }

    @ExceptionHandler(ProductAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleAlreadyDeactivated(ProductAlreadyDeactivatedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorUtil.createError(HttpStatus.CONFLICT, "Producto ya desactivado", ex.getMessage(), request));
    }

    @ExceptionHandler(CannotInactivateProductWithStockException.class)
    public ResponseEntity<ApiError> handleStockException(CannotInactivateProductWithStockException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorUtil.createError(HttpStatus.CONFLICT, "Stock aún disponible", ex.getMessage(), request));
    }
}

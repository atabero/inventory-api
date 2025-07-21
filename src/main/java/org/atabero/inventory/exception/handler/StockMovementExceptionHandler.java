package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.stockmovemen.InsufficientStockException;
import org.atabero.inventory.exception.stockmovemen.StockReplenishmentNotAllowedException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockMovementExceptionHandler {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ApiError> handleInsufficientStock(InsufficientStockException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.BAD_REQUEST,
                "No hay stock suficiente para realizar la operación",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(StockReplenishmentNotAllowedException.class)
    public ResponseEntity<ApiError> handleStockReplenishmentNotAllowed(StockReplenishmentNotAllowedException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.BAD_REQUEST,
                "No se permite reponer el stock",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}

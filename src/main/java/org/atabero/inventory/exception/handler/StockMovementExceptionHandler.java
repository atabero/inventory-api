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

/**
 * Manejador global de excepciones para errores relacionados con movimientos de stock.
 */
@ControllerAdvice
public class StockMovementExceptionHandler {

    /**
     * Maneja la excepción cuando no hay stock suficiente para realizar una operación.
     *
     * @param ex      La excepción InsufficientStockException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con un mensaje de error y código HTTP 400 BAD REQUEST.
     */
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

    /**
     * Maneja la excepción cuando no se permite reponer el stock.
     *
     * @param ex      La excepción StockReplenishmentNotAllowedException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con un mensaje de error y código HTTP 400 BAD REQUEST.
     */
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

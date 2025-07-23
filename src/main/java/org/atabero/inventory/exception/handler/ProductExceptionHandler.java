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

/**
 * Manejador global de excepciones específicas para operaciones sobre productos.
 * Proporciona respuestas HTTP con mensajes claros para errores comunes relacionados con productos.
 */
@ControllerAdvice
public class ProductExceptionHandler {

    /**
     * Maneja el caso cuando un producto no es encontrado en el sistema.
     *
     * @param ex      La excepción ProductNotFoundException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con un mensaje de error y código HTTP 404 NOT FOUND.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorUtil.createError(HttpStatus.NOT_FOUND, "Producto no encontrado", ex.getMessage(), request));
    }

    /**
     * Maneja el caso cuando se intenta desactivar un producto que ya está desactivado.
     *
     * @param ex      La excepción ProductAlreadyDeactivatedException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con un mensaje de error y código HTTP 409 CONFLICT.
     */
    @ExceptionHandler(ProductAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleAlreadyDeactivated(ProductAlreadyDeactivatedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorUtil.createError(HttpStatus.CONFLICT, "Producto ya desactivado", ex.getMessage(), request));
    }

    /**
     * Maneja el caso cuando se intenta inactivar un producto que aún tiene stock disponible.
     *
     * @param ex      La excepción CannotInactivateProductWithStockException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con un mensaje de error y código HTTP 409 CONFLICT.
     */
    @ExceptionHandler(CannotInactivateProductWithStockException.class)
    public ResponseEntity<ApiError> handleStockException(CannotInactivateProductWithStockException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorUtil.createError(HttpStatus.CONFLICT, "Stock aún disponible", ex.getMessage(), request));
    }
}

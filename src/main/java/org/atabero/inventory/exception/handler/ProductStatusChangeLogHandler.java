package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.productstatuschangelog.ProductStatusChangeLogNotFoundException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador global de excepciones para errores relacionados con logs de cambios de estado de producto.
 */
@ControllerAdvice
public class ProductStatusChangeLogHandler {

    /**
     * Maneja la excepción cuando no se encuentra un registro de cambio de estado de producto.
     *
     * @param ex      La excepción ProductStatusChangeLogNotFoundException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con un mensaje de error y código HTTP 404 NOT FOUND.
     */
    @ExceptionHandler(ProductStatusChangeLogNotFoundException.class)
    public ResponseEntity<ApiError> handleProductStatusChangeLogNotFound
    (ProductStatusChangeLogNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorUtil
                        .createError(HttpStatus.NOT_FOUND, "Producto no encontrado", ex.getMessage(), request));
    }
}

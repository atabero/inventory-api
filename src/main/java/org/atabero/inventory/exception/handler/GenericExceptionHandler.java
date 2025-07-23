package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Manejador global de excepciones genéricas y de validación de argumentos.
 * Proporciona respuestas HTTP adecuadas para errores comunes en la API.
 */
@ControllerAdvice
public class GenericExceptionHandler {

    /**
     * Maneja las excepciones de validación de argumentos de métodos.
     * Devuelve una respuesta con los errores de validación detallados y
     * un código HTTP 400 BAD REQUEST.
     *
     * @param ex      La excepción MethodArgumentNotValidException capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con detalles de los errores y código 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorUtil.createValidationError(request, errors));
    }

    /**
     * Maneja cualquier excepción no controlada o inesperada en la aplicación.
     * Devuelve un error genérico de servidor con código HTTP 500 INTERNAL SERVER ERROR.
     *
     * @param ex      La excepción capturada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con mensaje de error y código 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternal(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiErrorUtil.createError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error interno del servidor",
                        ex.getMessage(),
                        request));
    }
}

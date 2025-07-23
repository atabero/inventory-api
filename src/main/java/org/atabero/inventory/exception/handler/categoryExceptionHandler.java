package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.category.CategoryAlreadyActivatedException;
import org.atabero.inventory.exception.category.CategoryAlreadyDeactivatedException;
import org.atabero.inventory.exception.category.CategoryNotFoundException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Maneja globalmente las excepciones relacionadas con las categorías,
 * devolviendo respuestas HTTP adecuadas según el tipo de error.
 */
@ControllerAdvice
public class categoryExceptionHandler {

    /**
     * Maneja la excepción cuando una categoría no es encontrada.
     *
     * @param ex      La excepción CategoryNotFoundException lanzada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con el error y código HTTP 404 NOT FOUND.
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(HttpStatus.NOT_FOUND, "Categoría no encontrada", ex.getMessage(), request);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    /**
     * Maneja la excepción cuando se intenta desactivar una categoría
     * que ya está desactivada.
     *
     * @param ex      La excepción CategoryAlreadyDeactivatedException lanzada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con el error y código HTTP 409 CONFLICT.
     */
    @ExceptionHandler(CategoryAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleCategoryAlreadyDeactivated(CategoryAlreadyDeactivatedException ex, HttpServletRequest request){
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.CONFLICT,
                "La categoría ya está desactivada",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    /**
     * Maneja la excepción cuando se intenta activar una categoría
     * que ya está activada.
     *
     * @param ex      La excepción CategoryAlreadyActivatedException lanzada.
     * @param request Información de la petición HTTP que causó la excepción.
     * @return ResponseEntity con el error y código HTTP 409 CONFLICT.
     */
    @ExceptionHandler(CategoryAlreadyActivatedException.class)
    public ResponseEntity<ApiError> handleCategoryAlreadyActivated(CategoryAlreadyActivatedException ex, HttpServletRequest request){
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.CONFLICT,
                "La categoría ya está activada",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}

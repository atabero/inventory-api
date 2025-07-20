package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.category.CategoryAlreadyActivatedException;
import org.atabero.inventory.exception.category.CategoryAlreadyDeactivatedException;
import org.atabero.inventory.exception.category.CategoryNotFoundException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class categoryExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(HttpStatus.NOT_FOUND, "Categoría no encontrada", ex.getMessage(), request);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

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

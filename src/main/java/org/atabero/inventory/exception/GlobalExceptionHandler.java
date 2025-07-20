package org.atabero.inventory.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Genericas

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = createApiError(request, fieldErrors);

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor",
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
    // Categoria

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.NOT_FOUND, "Categoría no encontrada", ex.getMessage(), request);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(CategoryAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleCategoryAlreadyDeactivated(CategoryAlreadyDeactivatedException ex, HttpServletRequest request){
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "La categoría ya está desactivada",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(CategoryAlreadyActivatedException.class)
    public ResponseEntity<ApiError> handleCategoryAlreadyActivated(CategoryAlreadyActivatedException ex, HttpServletRequest request){
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "La categoría ya está activada",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    // Productos

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.NOT_FOUND, "Producto no encontrado", ex.getMessage(), request);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(ProductAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleProductAlreadyDeactivated(ProductAlreadyDeactivatedException ex, HttpServletRequest request){
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "El producto no puede ser desactivado con stock disponible",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(ProductAlreadyActivatedException.class)
    public ResponseEntity<ApiError> handleProductAlreadyActivated(ProductAlreadyActivatedException ex, HttpServletRequest request){
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "El producto ya está activada",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(CannotInactivateProductWithStockException.class)
    public ResponseEntity<ApiError> handleCannotInactivateProductWithStock(CannotInactivateProductWithStockException ex, HttpServletRequest request){
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "El producto sufrio un conflicto",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    // provedores

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<ApiError> handleSupplierNotFound(SupplierNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.NOT_FOUND, "Proveedor no encontrado", ex.getMessage(), request);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(SupplierAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleSupplierAlreadyDeactivated(SupplierAlreadyDeactivatedException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "El proveedor ya está desactivado",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(SupplierAlreadyActivatedException.class)
    public ResponseEntity<ApiError> handleSupplierAlreadyActivated(SupplierAlreadyActivatedException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(
                HttpStatus.CONFLICT,
                "El proveedor ya está activado",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }


    // creacion de api Error
    private ApiError createApiError(HttpStatus status, String error, String message, HttpServletRequest request) {
        return new ApiError(
                LocalDateTime.now(),
                status,
                error,
                message,
                request.getRequestURI()
        );
    }

    private static ApiError createApiError(HttpServletRequest request, List<String> fieldErrors) {
        return new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error de validación",
                "Uno o más campos no son válidos",
                request.getRequestURI(),
                fieldErrors
        );
    }
}

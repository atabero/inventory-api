package org.atabero.inventory.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.atabero.inventory.exception.product.ProductAlreadyActivatedException;
import org.atabero.inventory.exception.supplier.SupplierAlreadyActivatedException;
import org.atabero.inventory.exception.supplier.SupplierAlreadyDeactivatedException;
import org.atabero.inventory.exception.supplier.SupplierNotFoundException;
import org.atabero.inventory.util.ApiErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SupplierExceptionHandler {

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<ApiError> handleSupplierNotFound(SupplierNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(HttpStatus.NOT_FOUND, "Proveedor no encontrado", ex.getMessage(), request);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(SupplierAlreadyDeactivatedException.class)
    public ResponseEntity<ApiError> handleSupplierAlreadyDeactivated(SupplierAlreadyDeactivatedException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.CONFLICT,
                "El proveedor ya está desactivado",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(SupplierAlreadyActivatedException.class)
    public ResponseEntity<ApiError> handleSupplierAlreadyActivated(SupplierAlreadyActivatedException ex, HttpServletRequest request) {
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.CONFLICT,
                "El proveedor ya está activado",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(ProductAlreadyActivatedException.class)
    public ResponseEntity<ApiError> handleProductAlreadyActivated(ProductAlreadyActivatedException ex, HttpServletRequest request){
        ApiError apiError = ApiErrorUtil.createError(
                HttpStatus.CONFLICT,
                "El producto ya está activado",
                ex.getMessage(),
                request
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}

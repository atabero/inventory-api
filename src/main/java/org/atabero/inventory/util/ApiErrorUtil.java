package org.atabero.inventory.util;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorUtil {

    public static ApiError createError(HttpStatus status, String error, String message, HttpServletRequest request) {
        return new ApiError(
                LocalDateTime.now(),
                status,
                error,
                message,
                request.getRequestURI()
        );
    }

    public static ApiError createValidationError(HttpServletRequest request, List<String> errors) {
        return new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error de validación",
                "Uno o más campos son inválidos",
                request.getRequestURI(),
                errors
        );
    }
}
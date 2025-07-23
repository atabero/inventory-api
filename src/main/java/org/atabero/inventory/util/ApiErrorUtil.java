package org.atabero.inventory.util;

import jakarta.servlet.http.HttpServletRequest;
import org.atabero.inventory.exception.ApiError;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Utilidad para crear instancias de {@link ApiError} con información estandarizada
 * sobre errores en la API REST, incluyendo detalles como estado HTTP, mensaje
 * de error y la URI de la solicitud.
 */
public class ApiErrorUtil {

    /**
     * Crea un objeto {@link ApiError} con los datos proporcionados.
     *
     * @param status  el estado HTTP asociado al error
     * @param error   una breve descripción del error
     * @param message mensaje detallado del error
     * @param request objeto {@link HttpServletRequest} para obtener la URI de la solicitud
     * @return una instancia de {@link ApiError} con la información del error
     */
    public static ApiError createError(HttpStatus status, String error, String message, HttpServletRequest request) {
        return new ApiError(
                LocalDateTime.now(),
                status,
                error,
                message,
                request.getRequestURI()
        );
    }

    /**
     * Crea un objeto {@link ApiError} específico para errores de validación,
     * incluyendo una lista de mensajes de error detallados.
     *
     * @param request objeto {@link HttpServletRequest} para obtener la URI de la solicitud
     * @param errors  lista con los mensajes de error de validación
     * @return una instancia de {@link ApiError} con la información de validación inválida
     */
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

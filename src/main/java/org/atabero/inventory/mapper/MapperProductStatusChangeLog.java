package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.productstatuschangelog.ProductStatusChangeLogResponseDTO;
import org.atabero.inventory.model.ProductStatusChangeLog;

/**
 * Clase utilitaria para mapear la entidad ProductStatusChangeLog
 * a su correspondiente DTO de respuesta.
 */
public class MapperProductStatusChangeLog {

    /**
     * Convierte una entidad ProductStatusChangeLog en un DTO de respuesta.
     *
     * @param entity La entidad ProductStatusChangeLog a convertir.
     * @return DTO que representa el log de cambio de estado del producto.
     */
    public static ProductStatusChangeLogResponseDTO toResponse(ProductStatusChangeLog entity) {
        return ProductStatusChangeLogResponseDTO.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .previousStatus(entity.getPreviousStatus())
                .newStatus(entity.getNewStatus())
                .reason(entity.getReason())
                .operationStatus(entity.getOperationStatus())
                .operationMessage(entity.getOperationMessage())
                .changedAt(entity.getChangedAt())
                .build();
    }
}

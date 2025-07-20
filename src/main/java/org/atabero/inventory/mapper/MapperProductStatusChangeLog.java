package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.productstatuschangelog.ProductStatusChangeLogResponseDTO;
import org.atabero.inventory.model.ProductStatusChangeLog;

public class MapperProductStatusChangeLog {

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

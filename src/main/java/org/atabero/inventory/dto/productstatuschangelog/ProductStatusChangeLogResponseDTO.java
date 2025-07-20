package org.atabero.inventory.dto.productstatuschangelog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductStatusChangeLogResponseDTO {

    private UUID id;
    private Long productId;
    private ProductStatus previousStatus;
    private ProductStatus newStatus;
    private String reason;
    private OperationStatus operationStatus;
    private String operationMessage;
    private LocalDateTime changedAt;
}

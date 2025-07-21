package org.atabero.inventory.dto.stockmovement;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.atabero.inventory.model.enums.MovementType;
import org.atabero.inventory.model.enums.OperationStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockMovementDTO {

    @NotNull(message = "El id del producto no puede estar vacío")
    private Long idProduct;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    private Integer amount;

    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationStatus operationStatus;

    @Column(length = 500)
    private String operationMessage;

    @Column(nullable = false)
    private LocalDateTime changedAt;
}

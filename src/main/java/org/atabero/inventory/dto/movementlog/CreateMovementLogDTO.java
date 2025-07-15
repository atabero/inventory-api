package org.atabero.inventory.dto.movementlog;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.atabero.inventory.model.enums.MovementType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovementLogDTO {

    @NotNull(message = "El id del producto no puede estar vacío")
    private Long idProduct;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    private Integer amount;

    @NotNull(message = "El tipo de movimiento no puede estar vacío")
    private MovementType movementType;

    private String notes;
}

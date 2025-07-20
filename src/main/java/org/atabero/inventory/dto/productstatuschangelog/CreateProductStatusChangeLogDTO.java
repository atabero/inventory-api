package org.atabero.inventory.dto.productstatuschangelog;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atabero.inventory.model.enums.ProductStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductStatusChangeLogDTO {

    @NotNull(message = "El id del producto no puede estar vacío")
    private Long productId;

    @NotNull(message = "El nuevo estado del producto no puede estar vacío")
    private ProductStatus newProductStatus;

    @Size(max = 255, message = "La razón no puede tener más de 255 caracteres")
    private String reason;
}

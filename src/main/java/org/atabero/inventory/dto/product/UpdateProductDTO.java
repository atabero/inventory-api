package org.atabero.inventory.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDTO {

    @Size(min = 4, message = "El nombre tiene que ser mayor a 4 caracteres")
    private String name;

    @Size(min = 4, max = 21, message = "El código tiene que tener entre 4 y 21 caracteres")
    private String code;

    // descripción opcional sin validación para ser libre

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String description;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
    private BigDecimal price;

    private Long idCategory;

    private Long supplier;

}

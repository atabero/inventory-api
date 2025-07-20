package org.atabero.inventory.dto.product;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDTO {

    // Validación para el nombre del producto
    @NotBlank(message = "El nombre no puede ser nulo")
    @Size(min = 4, message = "El nombre tiene que ser mayor a 4 caracteres")
    private String name;

    // Validación para el código del producto
    @NotBlank(message = "El código no puede estar vacío")
    @Size(min = 4, max = 21, message = "El código tiene que tener entre 4 y 21 caracteres")
    private String code;

    // Validación para la descripción
    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String description;

    // Validación para el precio
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
    private BigDecimal price;

    // Validación para el stock
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer currentStock;

    // Validación para la categoría
    @NotNull(message = "La categoría no puede ser nula")
    private Long idCategory;

    // Validación para el proveedor
    @NotNull(message = "El proveedor no puede ser nulo")
    private Long supplier;
}

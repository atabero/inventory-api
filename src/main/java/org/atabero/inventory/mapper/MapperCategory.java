package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.enums.CategoryStatus;

import java.util.Objects;

/**
 * Utilidad para mapear objetos entre entidades del dominio {@link Category}
 * y sus respectivas clases DTOs: {@link CreateCategoryDTO}, {@link UpdateCategoryDTO}
 * y {@link CategoryResponseDTO}.
 * <p>
 * Esta clase no debe ser instanciada.
 */
public class MapperCategory {

    /**
     * Constructor privado para evitar la instanciación de esta clase utilitaria.
     */
    private MapperCategory() {
    }

    /**
     * Convierte un {@link CreateCategoryDTO} en una entidad {@link Category}.
     * Si la descripción es nula o vacía, se asigna "SIN DESCRIPTION".
     *
     * @param dto el DTO de creación de categoría.
     * @return la entidad {@link Category} construida desde el DTO.
     */
    public static Category toEntity(CreateCategoryDTO dto) {
        String description;
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            description = "SIN DESCRIPTION";
        } else {
            description = dto.getDescription();
        }
        return Category.builder()
                .name(dto.getName())
                .description(description)
                .status(CategoryStatus.ACTIVE)
                .build();
    }

    /**
     * Convierte una entidad {@link Category} en un {@link CategoryResponseDTO}.
     *
     * @param category la entidad de categoría a convertir.
     * @return un DTO con los datos de la categoría.
     */
    public static CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(), category.getName(),
                category.getDescription(), category.getStatus(),
                category.getCreatedDate(), category.getLastModifiedDate()
        );
    }

    /**
     * Actualiza una entidad {@link Category} con los valores proporcionados
     * en un {@link UpdateCategoryDTO}, solo si son diferentes de los actuales.
     *
     * @param dto      el DTO con los nuevos valores.
     * @param category la entidad de categoría a actualizar.
     */
    public static void toUpdateCategory(UpdateCategoryDTO dto, Category category) {
        if (dto.getName() != null && !Objects.equals(category.getName(), dto.getName())) {
            category.setName(dto.getName());
        }
        if (dto.getDescription() != null && !Objects.equals(category.getDescription(), dto.getDescription())) {
            category.setDescription(dto.getDescription());
        }
    }
}

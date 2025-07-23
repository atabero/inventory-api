package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.enums.CategoryStatus;

import java.util.Objects;

/**
 * Clase utilitaria para mapear entre las entidades Category y sus DTOs.
 * Proporciona métodos para convertir DTOs de creación y actualización en entidades,
 * así como para transformar entidades en DTOs de respuesta.
 */
public class MapperCategory {

    private MapperCategory() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Convierte un DTO de creación de categoría a una entidad Category.
     * Si la descripción es nula o vacía, asigna un valor por defecto "SIN DESCRIPTION".
     *
     * @param dto DTO con datos para crear la categoría.
     * @return Entidad Category con los datos del DTO.
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
     * Convierte una entidad Category en un DTO de respuesta.
     *
     * @param category Entidad Category a convertir.
     * @return DTO que representa la categoría.
     */
    public static CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getStatus(),
                category.getCreatedDate(),
                category.getLastModifiedDate()
        );
    }

    /**
     * Actualiza una entidad Category con los datos recibidos en un DTO de actualización.
     * Solo actualiza los campos que sean distintos y no nulos.
     *
     * @param dto      DTO con los nuevos datos para la categoría.
     * @param category Entidad Category que será actualizada.
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

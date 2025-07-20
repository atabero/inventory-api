package org.atabero.inventory.service;

import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.model.Category;

import java.util.List;


/**
 * Interfaz para la gestión de categorías del inventario.
 */
public interface CategoryService {

    /**
     * Obtiene todas las categorías activas.
     *
     * @return lista de categorías
     */
    List<CategoryResponseDTO> findAll();

    /**
     * Busca una categoría por su ID.
     *
     * @param id identificador de la categoría
     * @return DTO de respuesta de la categoría
     */
    CategoryResponseDTO findById(Long id);

    /**
     * Crea una nueva categoría.
     *
     * @param dto datos de creación
     * @return DTO de la categoría creada
     */
    CategoryResponseDTO create(CreateCategoryDTO dto);

    /**
     * Actualiza una categoría existente.
     *
     * @param dto datos de actualización
     * @return DTO actualizado
     */
    CategoryResponseDTO update(Long id, UpdateCategoryDTO dto);

    /**
     * Desactiva (soft delete) una categoría por su ID.
     *
     * @param id identificador de la categoría
     */
    void deactivate(Long id);

    void activate(Long id);

    Category findByIdFull(Long id);
}

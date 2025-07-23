package org.atabero.inventory.service;

import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.model.Category;

import java.util.List;

/**
 * Servicio para manejar las operaciones relacionadas con las categorías.
 */
public interface CategoryService {

    /**
     * Obtiene la lista completa de categorías.
     *
     * @return lista de objetos {@link CategoryResponseDTO} con la información de todas las categorías activas.
     */
    List<CategoryResponseDTO> findAll();

    /**
     * Busca una categoría por su identificador.
     *
     * @param id identificador único de la categoría.
     * @return un objeto {@link CategoryResponseDTO} con la información de la categoría encontrada.
     * @throws RuntimeException si la categoría no existe.
     */
    CategoryResponseDTO findById(Long id);

    /**
     * Crea una nueva categoría con la información proporcionada.
     *
     * @param dto objeto {@link CreateCategoryDTO} con los datos para crear la categoría.
     * @return un objeto {@link CategoryResponseDTO} con la información de la categoría creada.
     */
    CategoryResponseDTO create(CreateCategoryDTO dto);

    /**
     * Actualiza una categoría existente con los datos proporcionados.
     *
     * @param id  identificador único de la categoría a actualizar.
     * @param dto objeto {@link UpdateCategoryDTO} con los datos para actualizar la categoría.
     * @return un objeto {@link CategoryResponseDTO} con la información actualizada de la categoría.
     * @throws RuntimeException si la categoría no existe.
     */
    CategoryResponseDTO update(Long id, UpdateCategoryDTO dto);

    /**
     * Desactiva una categoría, dejándola inactiva en el sistema.
     *
     * @param id identificador único de la categoría a desactivar.
     * @throws RuntimeException si la categoría no existe.
     */
    void deactivate(Long id);

    /**
     * Activa una categoría previamente desactivada.
     *
     * @param id identificador único de la categoría a activar.
     * @throws RuntimeException si la categoría no existe.
     */
    void activate(Long id);

    /**
     * Busca una categoría por su identificador y devuelve la entidad completa.
     *
     * @param id identificador único de la categoría.
     * @return la entidad {@link Category} completa de la categoría encontrada.
     * @throws RuntimeException si la categoría no existe.
     */
    Category findByIdFull(Long id);
}

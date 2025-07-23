package org.atabero.inventory.repository;

import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.enums.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceder y manipular datos de la entidad {@link Category}.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Busca una categoría por su nombre exacto.
     *
     * @param name nombre de la categoría.
     * @return un {@link Optional} que contiene la categoría si existe.
     */
    Optional<Category> findByName(String name);

    /**
     * Busca categorías que contengan el texto dado en el nombre, sin distinguir mayúsculas.
     *
     * @param name parte del nombre a buscar.
     * @return lista de categorías que coinciden parcialmente con el nombre.
     */
    List<Category> findByNameContainingIgnoreCase(String name);

    /**
     * Busca categorías por su estado.
     *
     * @param status estado de la categoría ({@link CategoryStatus}).
     * @return lista de categorías con el estado especificado.
     */
    List<Category> findByStatus(CategoryStatus status);

    /**
     * Busca categorías que contengan el texto dado en el nombre y tengan un estado específico.
     *
     * @param name   parte del nombre a buscar.
     * @param status estado de la categoría.
     * @return lista de categorías que coinciden con el nombre parcial y el estado.
     */
    List<Category> findByNameContainingIgnoreCaseAndStatus(String name, CategoryStatus status);

    /**
     * Devuelve una página de categorías cuyo nombre contenga el texto indicado, sin distinguir mayúsculas.
     *
     * @param name     parte del nombre a buscar.
     * @param pageable objeto para la paginación.
     * @return página de categorías.
     */
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Devuelve una página de categorías según su estado.
     *
     * @param status   estado de la categoría.
     * @param pageable objeto para la paginación.
     * @return página de categorías con el estado especificado.
     */
    Page<Category> findByStatus(CategoryStatus status, Pageable pageable);

    /**
     * Devuelve una página de categorías que coincidan parcialmente con el nombre y tengan un estado específico.
     *
     * @param name     parte del nombre a buscar.
     * @param status   estado de la categoría.
     * @param pageable objeto para la paginación.
     * @return página de categorías.
     */
    Page<Category> findByNameContainingIgnoreCaseAndStatus(String name, CategoryStatus status, Pageable pageable);
}

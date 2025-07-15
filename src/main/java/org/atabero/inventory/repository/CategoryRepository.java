package org.atabero.inventory.repository;

import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.enums.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos de las categorías en el sistema de inventario.
 * Esta interfaz extiende JpaRepository, lo que proporciona operaciones CRUD básicas
 * (crear, leer, actualizar, eliminar) sobre la entidad {@link Category}.
 * Además, define métodos de búsqueda personalizados basados en atributos directos,
 * combinados y con soporte para paginación y ordenación.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // --- Búsquedas por Atributos Directos ---

    /**
     * Busca una categoría por su nombre. Dado que el nombre de las categorías es único,
     * el resultado será un {@link Optional} que contendrá la categoría si existe,
     * o estará vacío si no se encuentra una categoría con ese nombre.
     *
     * @param name El nombre de la categoría que se busca.
     * @return Un {@link Optional<Category>} con la categoría encontrada,
     *         o {@link Optional#empty()} si no existe una categoría con ese nombre.
     */
    Optional<Category> findByName(String name);

    /**
     * Busca todas las categorías cuyo nombre contenga una cadena dada (ignorando
     * mayúsculas y minúsculas). Útil para realizar búsquedas parciales.
     *
     * @param name La cadena a buscar en los nombres de las categorías.
     * @return Una lista de categorías cuyos nombres contienen la cadena dada.
     */
    List<Category> findByNameContainingIgnoreCase(String name);

    /**
     * Busca todas las categorías con un estado específico, como por ejemplo
     * {@link CategoryStatus#ACTIVE} o {@link CategoryStatus#INACTIVE}.
     *
     * @param status El estado de las categorías que se busca.
     * @return Una lista de categorías que tienen el estado especificado.
     */
    List<Category> findByStatus(CategoryStatus status);

    // --- Búsquedas Combinadas ---

    /**
     * Busca todas las categorías cuyo nombre contenga una cadena dada y cuyo estado
     * coincida con el estado especificado.
     *
     * @param name El nombre parcial a buscar en las categorías.
     * @param status El estado de las categorías a buscar.
     * @return Una lista de categorías que cumplen ambos criterios (nombre y estado).
     */
    List<Category> findByNameContainingIgnoreCaseAndStatus(String name, CategoryStatus status);

    // --- Paginación y Ordenación (¡MUY IMPORTANTE!) ---

    /**
     * Busca categorías por nombre, permitiendo paginación y ordenación. El resultado
     * estará dividido en páginas, y podrá ser ordenado según los criterios establecidos
     * en el parámetro {@link Pageable}.
     *
     * @param name El nombre parcial a buscar en las categorías.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de categorías que contienen la cadena dada en su nombre.
     */
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Busca categorías por estado, permitiendo paginación y ordenación. El resultado
     * estará dividido en páginas, y podrá ser ordenado según los criterios establecidos
     * en el parámetro {@link Pageable}.
     *
     * @param status El estado de las categorías que se busca.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de categorías que tienen el estado especificado.
     */
    Page<Category> findByStatus(CategoryStatus status, Pageable pageable);

    /**
     * Busca categorías por nombre y estado, permitiendo paginación y ordenación. El resultado
     * estará dividido en páginas, y podrá ser ordenado según los criterios establecidos
     * en el parámetro {@link Pageable}.
     *
     * @param name El nombre parcial a buscar en las categorías.
     * @param status El estado de las categorías a buscar.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de categorías que cumplen ambos criterios (nombre y estado).
     */
    Page<Category> findByNameContainingIgnoreCaseAndStatus(String name, CategoryStatus status, Pageable pageable);
}

package org.atabero.inventory.repository;

import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.SupplierStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos de los proveedores en el sistema de inventario.
 * Esta interfaz extiende {@link JpaRepository} y proporciona operaciones de búsqueda personalizadas
 * sobre los proveedores, incluyendo búsquedas por nombre, estado y combinaciones de estos,
 * con soporte para paginación y ordenación.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // --- Búsquedas por Atributos Directos ---

    /**
     * Busca un proveedor por su nombre único.
     * Este método devuelve un {@link Optional} que contendrá el proveedor si se encuentra,
     * o estará vacío si no existe un proveedor con ese nombre.
     *
     * @param name El nombre del proveedor.
     * @return Un {@link Optional<Supplier>} con el proveedor encontrado, o {@link Optional#empty()} si no existe.
     */
    Optional<Supplier> findByName(String name);

    /**
     * Busca proveedores cuyo nombre contenga una cadena dada, sin importar mayúsculas o minúsculas.
     * Este tipo de búsqueda es útil para realizar búsquedas parciales o tipo "Google".
     *
     * @param name La cadena a buscar en los nombres de los proveedores.
     * @return Una lista de proveedores cuyos nombres contienen la cadena proporcionada.
     */
    List<Supplier> findByNameContainingIgnoreCase(String name);

    /**
     * Busca proveedores por su estado (ACTIVO, INACTIVO, etc.).
     *
     * @param status El estado del proveedor (por ejemplo, {@link SupplierStatus#ACTIVE}, {@link SupplierStatus#INACTIVE}).
     * @return Una lista de proveedores con el estado especificado.
     */
    List<Supplier> findByStatus(SupplierStatus status);

    // --- Búsquedas Combinadas ---

    /**
     * Busca proveedores cuyo nombre contenga una cadena dada y cuyo estado coincida con el proporcionado.
     *
     * @param name El nombre (o parte del nombre) del proveedor.
     * @param status El estado del proveedor (ACTIVO, INACTIVO, etc.).
     * @return Una lista de proveedores que coinciden con los criterios dados.
     */
    List<Supplier> findByNameContainingIgnoreCaseAndStatus(String name, SupplierStatus status);

    // --- Paginación y Ordenación (Importante para listados grandes) ---

    /**
     * Busca proveedores cuyo nombre contenga una cadena dada, con soporte para paginación y ordenación.
     *
     * @param name La cadena a buscar en los nombres de los proveedores.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de proveedores cuyos nombres contienen la cadena proporcionada, con paginación y ordenación.
     */
    Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Busca proveedores cuyo estado coincida con el estado proporcionado, con soporte para paginación y ordenación.
     *
     * @param status El estado del proveedor (por ejemplo, ACTIVO, INACTIVO).
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de proveedores con el estado especificado, con paginación y ordenación.
     */
    Page<Supplier> findByStatus(SupplierStatus status, Pageable pageable);

    /**
     * Busca proveedores cuyo nombre contenga una cadena dada y cuyo estado coincida con el proporcionado,
     * con soporte para paginación y ordenación.
     *
     * @param name El nombre (o parte del nombre) del proveedor.
     * @param status El estado del proveedor (ACTIVO, INACTIVO).
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de proveedores que coinciden con los criterios dados, con paginación y ordenación.
     */
    Page<Supplier> findByNameContainingIgnoreCaseAndStatus(String name, SupplierStatus status, Pageable pageable);
}

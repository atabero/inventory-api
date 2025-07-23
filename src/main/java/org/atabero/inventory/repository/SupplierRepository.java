package org.atabero.inventory.repository;

import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.SupplierStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceder y gestionar entidades {@link Supplier}.
 * Proporciona métodos personalizados para buscar proveedores según distintos criterios.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Busca un proveedor por su nombre exacto.
     *
     * @param name nombre del proveedor.
     * @return un {@link Optional} que contiene el proveedor si existe.
     */
    Optional<Supplier> findByName(String name);

    /**
     * Busca proveedores cuyo nombre contenga el texto indicado, sin distinguir mayúsculas.
     *
     * @param name parte del nombre a buscar.
     * @return lista de proveedores que coinciden parcialmente con el nombre.
     */
    List<Supplier> findByNameContainingIgnoreCase(String name);

    /**
     * Busca proveedores por su estado.
     *
     * @param status estado del proveedor ({@link SupplierStatus}).
     * @return lista de proveedores con el estado indicado.
     */
    List<Supplier> findByStatus(SupplierStatus status);

    /**
     * Busca proveedores por nombre parcial y estado.
     *
     * @param name   parte del nombre a buscar.
     * @param status estado del proveedor.
     * @return lista de proveedores que coincidan con ambos criterios.
     */
    List<Supplier> findByNameContainingIgnoreCaseAndStatus(String name, SupplierStatus status);

    /**
     * Devuelve una página de proveedores cuyo nombre contenga el texto indicado.
     *
     * @param name     parte del nombre a buscar.
     * @param pageable objeto para paginación.
     * @return página de proveedores.
     */
    Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Devuelve una página de proveedores con el estado especificado.
     *
     * @param status   estado del proveedor.
     * @param pageable objeto para paginación.
     * @return página de proveedores.
     */
    Page<Supplier> findByStatus(SupplierStatus status, Pageable pageable);

    /**
     * Devuelve una página de proveedores cuyo nombre contenga el texto indicado y tengan un estado específico.
     *
     * @param name     parte del nombre a buscar.
     * @param status   estado del proveedor.
     * @param pageable objeto para paginación.
     * @return página de proveedores que coincidan con los filtros.
     */
    Page<Supplier> findByNameContainingIgnoreCaseAndStatus(String name, SupplierStatus status, Pageable pageable);
}

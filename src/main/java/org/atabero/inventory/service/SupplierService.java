package org.atabero.inventory.service;

import org.atabero.inventory.dto.supplier.CreateSupplierDTO;
import org.atabero.inventory.dto.supplier.SupplierResponseDTO;
import org.atabero.inventory.dto.supplier.UpdateSupplierDTO;
import org.atabero.inventory.model.Supplier;

import java.util.List;

/**
 * Servicio para gestionar proveedores.
 */
public interface SupplierService {

    /**
     * Obtiene una lista con todos los proveedores activos.
     *
     * @return lista de {@link SupplierResponseDTO} con los proveedores.
     */
    List<SupplierResponseDTO> findAll();

    /**
     * Busca un proveedor por su identificador.
     *
     * @param id identificador único del proveedor.
     * @return {@link SupplierResponseDTO} con la información del proveedor.
     * @throws RuntimeException si el proveedor no existe.
     */
    SupplierResponseDTO findById(Long id);

    /**
     * Crea un nuevo proveedor con los datos indicados.
     *
     * @param dto objeto {@link CreateSupplierDTO} con la información del nuevo proveedor.
     * @return {@link SupplierResponseDTO} con la información del proveedor creado.
     */
    SupplierResponseDTO create(CreateSupplierDTO dto);

    /**
     * Actualiza un proveedor existente con los datos proporcionados.
     *
     * @param id  identificador único del proveedor a actualizar.
     * @param dto objeto {@link UpdateSupplierDTO} con los datos de actualización.
     * @return {@link SupplierResponseDTO} con la información actualizada del proveedor.
     * @throws RuntimeException si el proveedor no existe.
     */
    SupplierResponseDTO update(Long id, UpdateSupplierDTO dto);

    /**
     * Desactiva un proveedor dado su identificador.
     *
     * @param id identificador único del proveedor.
     */
    void deactivate(Long id);

    /**
     * Activa un proveedor dado su identificador.
     *
     * @param id identificador único del proveedor.
     */
    void activate(Long id);

    /**
     * Obtiene la entidad completa de un proveedor por su identificador.
     *
     * @param id identificador único del proveedor.
     * @return entidad {@link Supplier} completa del proveedor.
     * @throws RuntimeException si el proveedor no existe.
     */
    Supplier findByIdFull(Long id);
}

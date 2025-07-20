package org.atabero.inventory.service;

import org.atabero.inventory.dto.supplier.CreateSupplierDTO;
import org.atabero.inventory.dto.supplier.SupplierResponseDTO;
import org.atabero.inventory.dto.supplier.UpdateSupplierDTO;
import org.atabero.inventory.model.Supplier;

import java.util.List;

public interface SupplierService {

    List<SupplierResponseDTO> findAll();

    SupplierResponseDTO findById(Long id);

    SupplierResponseDTO create(CreateSupplierDTO dto);

    SupplierResponseDTO update(Long id, UpdateSupplierDTO dto);

    void deactivate(Long id);

    void activate(Long id);
    Supplier findByIdFull(Long id);
}

package org.atabero.inventory.service;

import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.supplier.CreateSupplierDTO;
import org.atabero.inventory.dto.supplier.SupplierResponseDTO;
import org.atabero.inventory.dto.supplier.UpdateSupplierDTO;
import org.atabero.inventory.exception.SupplierAlreadyActivatedException;
import org.atabero.inventory.exception.SupplierAlreadyDeactivatedException;
import org.atabero.inventory.exception.SupplierNotFoundException;
import org.atabero.inventory.mapper.MapperSupplier;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.SupplierStatus;
import org.atabero.inventory.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<SupplierResponseDTO> findAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(
                        MapperSupplier::toResponse
                ).toList();
    }

    @Override
    public SupplierResponseDTO findById(Long id) {
        Supplier supplier = findByIdFull(id);
        return MapperSupplier.toResponse(supplier);
    }

    @Override
    public SupplierResponseDTO create(CreateSupplierDTO dto) {
        Supplier supplier = MapperSupplier.toEntity(dto);
        supplier = supplierRepository.save(supplier);
        return MapperSupplier.toResponse(supplier);
    }

    @Override
    public SupplierResponseDTO update(Long id, UpdateSupplierDTO dto) {
        Supplier supplier = findByIdFull(id);
        MapperSupplier.updateSupplier(dto, supplier);
        supplier = supplierRepository.save(supplier);
        return MapperSupplier.toResponse(supplier);
    }

    @Override
    public void deactivate(Long id) {
        Supplier supplier = findByIdFull(id);
        if (supplier.getStatus() == SupplierStatus.INACTIVE) {
            throw new SupplierAlreadyDeactivatedException(id);
        }
        supplier.setStatus(SupplierStatus.INACTIVE);
        supplierRepository.save(supplier);
    }

    @Override
    public void activate(Long id) {
        Supplier supplier = findByIdFull(id);
        if (supplier.getStatus() == SupplierStatus.ACTIVE) {
            throw new SupplierAlreadyActivatedException(id);
        }
        supplier.setStatus(SupplierStatus.ACTIVE);
        supplierRepository.save(supplier);
    }

    private Supplier findByIdFull(Long id) {
        return supplierRepository.findById(id).orElseThrow(
                () -> new SupplierNotFoundException(id)
        );
    }
}

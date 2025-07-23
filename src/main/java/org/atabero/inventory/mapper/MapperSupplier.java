package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.supplier.CreateSupplierDTO;
import org.atabero.inventory.dto.supplier.SupplierResponseDTO;
import org.atabero.inventory.dto.supplier.UpdateSupplierDTO;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.SupplierStatus;

import java.util.Objects;

/**
 * Clase utilitaria para convertir entre entidades Supplier
 * y sus respectivos DTOs.
 */
public class MapperSupplier {

    // Constructor privado para evitar instanciación
    private MapperSupplier() {
    }

    /**
     * Convierte un DTO de creación en una entidad Supplier.
     * Si la información de contacto es nula o vacía, se asigna "No Aplica".
     *
     * @param dto DTO con los datos para crear un proveedor.
     * @return La entidad Supplier creada.
     */
    public static Supplier toEntity(CreateSupplierDTO dto) {
        String contactInfo = "No Aplica";
        if (dto.getContactInfo() != null && !dto.getContactInfo().isEmpty()) {
            contactInfo = dto.getContactInfo();
        }

        return Supplier.builder()
                .name(dto.getName())
                .contactInfo(contactInfo)
                .status(SupplierStatus.ACTIVE)
                .build();
    }

    /**
     * Convierte una entidad Supplier en su DTO de respuesta.
     *
     * @param supplier Entidad Supplier a convertir.
     * @return DTO con los datos del proveedor.
     */
    public static SupplierResponseDTO toResponse(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(), supplier.getName(), supplier.getContactInfo(),
                supplier.getStatus(), supplier.getCreatedDate(), supplier.getLastModifiedDate()
        );
    }

    /**
     * Actualiza los datos de un Supplier a partir de un DTO de actualización,
     * modificando solo los campos que difieren.
     *
     * @param dto      DTO con los datos actualizados.
     * @param supplier Entidad Supplier a actualizar.
     */
    public static void updateSupplier(UpdateSupplierDTO dto, Supplier supplier) {
        if (dto.getName() != null && !Objects.equals(supplier.getName(), dto.getName())) {
            supplier.setName(dto.getName());
        }
        if (dto.getContactInfo() != null && !Objects.equals(supplier.getContactInfo(), dto.getContactInfo())) {
            supplier.setContactInfo(dto.getContactInfo());
        }
    }
}

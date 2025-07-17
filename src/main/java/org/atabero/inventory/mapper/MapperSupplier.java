package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.supplier.CreateSupplierDTO;
import org.atabero.inventory.dto.supplier.SupplierResponseDTO;
import org.atabero.inventory.dto.supplier.UpdateSupplierDTO;
import org.atabero.inventory.model.Supplier;

import java.util.Objects;

/**
 * Clase utilitaria para mapear entre entidades {@link Supplier} y sus DTOs
 * correspondientes: {@link CreateSupplierDTO}, {@link SupplierResponseDTO},
 * y {@link UpdateSupplierDTO}.
 * <p>
 * Esta clase no debe ser instanciada.
 */
public class MapperSupplier {

    /**
     * Constructor privado para prevenir la instanciación de esta clase utilitaria.
     */
    private MapperSupplier() {
    }

    /**
     * Convierte un {@link CreateSupplierDTO} en una entidad {@link Supplier}.
     * Si la información de contacto es nula o vacía, se asigna el valor "No Aplica".
     *
     * @param dto el DTO de creación del proveedor
     * @return una nueva instancia de {@link Supplier} basada en el DTO
     */
    public static Supplier toEntity(CreateSupplierDTO dto) {
        String contactInfo = "No Aplica";
        if (dto.getContactInfo() != null && !dto.getContactInfo().isEmpty()) {
            contactInfo = dto.getContactInfo();
        }

        return Supplier.builder()
                .name(dto.getName())
                .contactInfo(contactInfo)
                .build();
    }

    /**
     * Convierte una entidad {@link Supplier} en un {@link SupplierResponseDTO}.
     *
     * @param supplier la entidad de proveedor a convertir
     * @return un DTO con los datos del proveedor
     */
    public static SupplierResponseDTO toResponse(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(), supplier.getName(), supplier.getContactInfo(),
                supplier.getStatus(), supplier.getCreatedDate(), supplier.getLastModifiedDate()
        );
    }

    /**
     * Actualiza una entidad {@link Supplier} con los datos proporcionados en
     * un {@link UpdateSupplierDTO}, solo si los valores son distintos a los actuales.
     *
     * @param dto      el DTO con los datos actualizados
     * @param supplier la entidad de proveedor a modificar
     */
    public static void UpdateSupplier(UpdateSupplierDTO dto, Supplier supplier) {
        if (dto.getName() != null && !Objects.equals(supplier.getName(), dto.getName())) {
            supplier.setName(dto.getName());
        }
        if (dto.getContactInfo() != null && !Objects.equals(supplier.getContactInfo(), dto.getContactInfo())) {
            supplier.setContactInfo(dto.getContactInfo());
        }
    }
}

package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;

import java.util.Objects;

/**
 * Clase utilitaria para mapear entre las entidades Product y sus DTOs.
 * Proporciona métodos para convertir DTOs de creación y actualización en entidades,
 * así como para transformar entidades en DTOs de respuesta.
 */
public class MapperProduct {

    /**
     * Convierte un DTO de creación de producto en una entidad Product.
     * Asocia la categoría y el proveedor recibidos y establece el estado activo por defecto.
     *
     * @param dto      DTO con datos para crear el producto.
     * @param category Categoría asociada al producto.
     * @param supplier Proveedor asociado al producto.
     * @return Entidad Product con los datos del DTO y asociaciones.
     */
    public static Product toEntity(CreateProductDTO dto, Category category, Supplier supplier){
        return Product.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .currentStock(dto.getCurrentStock())
                .category(category)
                .supplier(supplier)
                .status(ProductStatus.ACTIVE)
                .build();
    }

    /**
     * Convierte una entidad Product en un DTO de respuesta.
     *
     * @param product Entidad Product a convertir.
     * @return DTO que representa el producto.
     */
    public static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getDescription(),
                product.getPrice(),
                product.getCurrentStock(),
                product.getStatus(),
                product.getCategory().getName(),
                product.getSupplier().getName(),
                product.getCreatedDate(),
                product.getLastModifiedDate()
        );
    }

    /**
     * Actualiza una entidad Product con los datos recibidos en un DTO de actualización.
     * Solo actualiza los campos que sean distintos y no nulos.
     * También actualiza la categoría y proveedor si son diferentes.
     *
     * @param dto      DTO con los nuevos datos para el producto.
     * @param product  Entidad Product que será actualizada.
     * @param category Nueva categoría asociada (puede ser null para no modificar).
     * @param supplier Nuevo proveedor asociado (puede ser null para no modificar).
     */
    public static void update(UpdateProductDTO dto, Product product, Category category, Supplier supplier) {
        if (dto.getName() != null && !Objects.equals(dto.getName(), product.getName())) {
            product.setName(dto.getName());
        }
        if (dto.getCode() != null && !Objects.equals(dto.getCode(), product.getCode())) {
            product.setCode(dto.getCode());
        }
        if (dto.getDescription() != null && !Objects.equals(dto.getDescription(), product.getDescription())) {
            product.setDescription(dto.getDescription());
        }

        if (dto.getPrice() != null && dto.getPrice().compareTo(product.getPrice()) != 0) {
            product.setPrice(dto.getPrice());
        }

        if (category != null && !Objects.equals(category, product.getCategory())) {
            product.setCategory(category);
        }

        if (supplier != null && !Objects.equals(supplier, product.getSupplier())) {
            product.setSupplier(supplier);
        }
    }
}

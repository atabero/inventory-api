package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;

import java.util.Objects;

public class MapperProduct {


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

    public static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(), product.getName(), product.getCode(),
                product.getDescription(), product.getPrice(), product.getCurrentStock(),
                product.getStatus(), product.getCategory().getName(), product.getSupplier().getName(),
                product.getCreatedDate(), product.getLastModifiedDate()
        );
    }

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

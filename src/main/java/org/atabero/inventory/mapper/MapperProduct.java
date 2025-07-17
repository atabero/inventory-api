package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;

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
}

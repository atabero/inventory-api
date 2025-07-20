package org.atabero.inventory.service;

import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAll();
    ProductResponseDTO getById(Long id);
    ProductResponseDTO create(CreateProductDTO dto);
    List<ProductResponseDTO> create(List<CreateProductDTO> dtos);
    ProductResponseDTO update(Long id, UpdateProductDTO dto);
    void deactivate(Long id);
    void activate(Long id);
    Product getByIdFull(Long id);
}

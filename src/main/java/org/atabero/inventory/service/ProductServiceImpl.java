package org.atabero.inventory.service;

import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.exception.CannotInactivateProductWithStockException;
import org.atabero.inventory.exception.ProductAlreadyDeactivatedException;
import org.atabero.inventory.exception.ProductNotFoundException;
import org.atabero.inventory.mapper.MapperProduct;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream()
                .map(MapperProduct::toResponse)
                .toList();
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        return MapperProduct.toResponse(getByIdFull(id));
    }

    @Override
    public ProductResponseDTO create(CreateProductDTO dto) {
        Category category = categoryService.findByIdFull(dto.getIdCategory());
        Supplier supplier = supplierService.findByIdFull(dto.getSupplier());
        Product product = MapperProduct.toEntity(dto, category, supplier);
        saveProduct(product);
        return MapperProduct.toResponse(product);
    }

    @Override
    public ProductResponseDTO update(Long id, UpdateProductDTO dto) {
        Product product = getByIdFull(id);
        Category category = resolveCategory(product, dto.getIdCategory());
        Supplier supplier = resolveSupplier(product, dto.getSupplier());

        MapperProduct.update(dto, product, category, supplier);
        saveProduct(product);
        return MapperProduct.toResponse(product);
    }

    @Override
    public void deactivate(Long id) {
        Product product = getByIdFull(id);

        switch (product.getStatus()) {
            case INACTIVE -> throw new ProductAlreadyDeactivatedException(product.getId());

            case ACTIVE -> {
                if (product.getCurrentStock() == 0) {
                    product.setStatus(ProductStatus.INACTIVE);
                    saveProduct(product);
                } else {
                    product.setStatus(ProductStatus.DISCONTINUED);
                    saveProduct(product);
                    throw new CannotInactivateProductWithStockException(
                            "El producto tiene stock y no puede marcarse como INACTIVO. Se ha marcado como DESCONTINUADO."
                    );
                }
            }

            case DISCONTINUED -> {
                if (product.getCurrentStock() == 0) {
                    product.setStatus(ProductStatus.INACTIVE);
                    saveProduct(product);
                } else {
                    throw new CannotInactivateProductWithStockException(
                            "El producto ya está DESCONTINUADO y aún tiene stock. No puede marcarse como INACTIVO."
                    );
                }
            }
        }
    }


    @Override
    public void activate(Long id) {
        // Pendiente de implementación
    }

    @Override
    public Product getByIdFull(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // 🔒 Helpers privados

    private void saveProduct(Product product) {
        productRepository.save(product);
    }


    private Category resolveCategory(Product product, Long newCategoryId) {
        if (newCategoryId != null && !Objects.equals(product.getCategory().getId(), newCategoryId)) {
            return categoryService.findByIdFull(newCategoryId);
        }
        return null;
    }

    private Supplier resolveSupplier(Product product, Long newSupplierId) {
        if (newSupplierId != null && !Objects.equals(product.getSupplier().getId(), newSupplierId)) {
            return supplierService.findByIdFull(newSupplierId);
        }
        return null;
    }
}

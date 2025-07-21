package org.atabero.inventory.service;

import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.exception.product.CannotInactivateProductWithStockException;
import org.atabero.inventory.exception.product.ProductAlreadyDeactivatedException;
import org.atabero.inventory.exception.product.ProductNotFoundException;
import org.atabero.inventory.mapper.MapperProduct;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream()
                .map(MapperProduct::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getById(Long id) {
        return MapperProduct.toResponse(getByIdFull(id));
    }

    @Override
    @Transactional
    public ProductResponseDTO create(CreateProductDTO dto) {
        Category category = categoryService.findByIdFull(dto.getIdCategory());
        Supplier supplier = supplierService.findByIdFull(dto.getSupplier());
        Product product = MapperProduct.toEntity(dto, category, supplier);
        saveProduct(product);
        return MapperProduct.toResponse(product);
    }

    @Override
    @Transactional
    public List<ProductResponseDTO> create(List<CreateProductDTO> dtos) {
        return dtos.stream().map(
                this::create
        ).toList();
    }

    @Override
    @Transactional
    public ProductResponseDTO update(Long id, UpdateProductDTO dto) {
        Product product = getByIdFull(id);
        Category category = resolveCategory(product, dto.getIdCategory());
        Supplier supplier = resolveSupplier(product, dto.getSupplier());

        MapperProduct.update(dto, product, category, supplier);
        saveProduct(product);
        return MapperProduct.toResponse(product);
    }

    @Override
    public Product getByIdFull(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional
    public void modifyStatus(Product product) {
        saveProduct(product);
    }

    @Override
    public void modifyStock(Product product) {
        saveProduct(product);
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

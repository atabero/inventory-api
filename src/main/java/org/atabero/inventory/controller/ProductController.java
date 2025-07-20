package org.atabero.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(
                productService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                productService.getById(id)
        );
    }


    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productService.create(dto)
        );
    }

    @PostMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> create(@RequestBody @Valid List<CreateProductDTO> dtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productService.create(dtos)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO dto) {
        return ResponseEntity.ok(
                productService.update(id, dto)
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        productService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

}

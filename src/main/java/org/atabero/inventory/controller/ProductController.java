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

/**
 * Controlador REST para gestionar productos del inventario.
 * Proporciona endpoints para crear, consultar y actualizar productos.
 */
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Obtiene la lista completa de productos.
     *
     * @return ResponseEntity con la lista de ProductResponseDTO.
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(
                productService.getAll()
        );
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id Identificador del producto.
     * @return ResponseEntity con el ProductResponseDTO correspondiente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                productService.getById(id)
        );
    }

    /**
     * Crea un nuevo producto.
     *
     * @param dto DTO con los datos para crear el producto.
     * @return ResponseEntity con el ProductResponseDTO creado y código 201 Created.
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productService.create(dto)
        );
    }

    /**
     * Crea múltiples productos a la vez.
     *
     * @param dtos Lista de DTOs para crear productos.
     * @return ResponseEntity con la lista de ProductResponseDTO creados y código 201 Created.
     */
    @PostMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> create(@RequestBody @Valid List<CreateProductDTO> dtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productService.create(dtos)
        );
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id  Identificador del producto a actualizar.
     * @param dto DTO con los datos para actualizar el producto.
     * @return ResponseEntity con el ProductResponseDTO actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO dto) {
        return ResponseEntity.ok(
                productService.update(id, dto)
        );
    }
}

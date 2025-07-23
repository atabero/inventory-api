package org.atabero.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.supplier.CreateSupplierDTO;
import org.atabero.inventory.dto.supplier.SupplierResponseDTO;
import org.atabero.inventory.dto.supplier.UpdateSupplierDTO;
import org.atabero.inventory.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de proveedores.
 * Permite operaciones CRUD para proveedores, así como activación y desactivación.
 */
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    /**
     * Obtiene la lista completa de proveedores.
     *
     * @return ResponseEntity con la lista de proveedores.
     */
    @GetMapping()
    public ResponseEntity<List<SupplierResponseDTO>> getAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    /**
     * Obtiene un proveedor por su ID.
     *
     * @param id ID del proveedor.
     * @return ResponseEntity con el proveedor encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    /**
     * Crea un nuevo proveedor.
     *
     * @param dto Datos para crear el proveedor.
     * @return ResponseEntity con el proveedor creado y código HTTP 201.
     */
    @PostMapping()
    public ResponseEntity<SupplierResponseDTO> create(@RequestBody @Valid CreateSupplierDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.create(dto));
    }

    /**
     * Actualiza un proveedor existente.
     *
     * @param id  ID del proveedor a actualizar.
     * @param dto Datos para la actualización.
     * @return ResponseEntity con el proveedor actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateSupplierDTO dto) {
        return ResponseEntity.ok(supplierService.update(id, dto));
    }

    /**
     * Desactiva un proveedor por su ID.
     *
     * @param id ID del proveedor a desactivar.
     * @return ResponseEntity sin contenido (204).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Activa un proveedor previamente desactivado.
     *
     * @param id ID del proveedor a activar.
     * @return ResponseEntity con código HTTP 200.
     */
    @GetMapping("/activate/{id}")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        supplierService.activate(id);
        return ResponseEntity.ok().build();
    }
}

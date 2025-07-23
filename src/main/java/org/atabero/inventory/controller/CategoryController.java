package org.atabero.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para gestionar las categorías del inventario.
 * Proporciona endpoints para crear, consultar, actualizar, activar y desactivar categorías.
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Obtiene la lista completa de categorías.
     *
     * @return ResponseEntity con la lista de CategoryResponseDTO o no content si está vacía.
     */
    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){
        List<CategoryResponseDTO> responseDTOS = categoryService.findAll();
        if(responseDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responseDTOS);
    }

    /**
     * Obtiene una categoría por su identificador.
     *
     * @param idCategory Identificador de la categoría a buscar.
     * @return ResponseEntity con el CategoryResponseDTO correspondiente.
     */
    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long idCategory){
        return ResponseEntity.ok(
                categoryService.findById(idCategory)
        );
    }

    /**
     * Crea una nueva categoría con los datos proporcionados.
     *
     * @param categoryDTO DTO con los datos de la nueva categoría.
     * @return ResponseEntity con el CategoryResponseDTO creado y la ubicación del recurso.
     */
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CreateCategoryDTO categoryDTO){
        CategoryResponseDTO created = categoryService.create(categoryDTO);
        URI location = URI.create("/api/v1/categories/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Actualiza una categoría existente identificada por su ID.
     *
     * @param idCategory Identificador de la categoría a actualizar.
     * @param categoryDTO DTO con los datos a actualizar.
     * @return ResponseEntity con el CategoryResponseDTO actualizado.
     */
    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long idCategory ,@RequestBody @Valid UpdateCategoryDTO categoryDTO ){
        return ResponseEntity.ok(
                categoryService.update(idCategory,categoryDTO)
        );
    }

    /**
     * Desactiva una categoría identificada por su ID.
     *
     * @param idCategory Identificador de la categoría a desactivar.
     * @return ResponseEntity vacío con estado 204 No Content.
     */
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> desactivate(@PathVariable Long idCategory){
        categoryService.deactivate(idCategory);
        return ResponseEntity.noContent().build();
    }

    /**
     * Activa una categoría previamente desactivada por su ID.
     *
     * @param idCategory Identificador de la categoría a activar.
     * @return ResponseEntity vacío con estado 200 OK.
     */
    @GetMapping("/activate/{idCategory}")
    public ResponseEntity<Void> activated(@PathVariable Long idCategory) {
        categoryService.activate(idCategory);
        return ResponseEntity.ok().build();
    }
}

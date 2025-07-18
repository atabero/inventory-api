package org.atabero.inventory.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){
        List<CategoryResponseDTO> responseDTOS = categoryService.findAll();
        if(responseDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long idCategory){
        return ResponseEntity.ok(
                categoryService.findById(idCategory)
        );
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CreateCategoryDTO categoryDTO){
        CategoryResponseDTO created = categoryService.create(categoryDTO);
        URI location = URI.create("/api/v1/categories/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long idCategory ,@RequestBody @Valid UpdateCategoryDTO categoryDTO ){
        return ResponseEntity.ok(
                categoryService.update(idCategory,categoryDTO)
        );
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> desactivate(@PathVariable Long idCategory){
        categoryService.deactivate(idCategory);
        return ResponseEntity.noContent().build();
    }

}

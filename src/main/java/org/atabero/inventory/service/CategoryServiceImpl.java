package org.atabero.inventory.service;


import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.exception.CategoryNotFoundException;
import org.atabero.inventory.mapper.MapperCategory;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(
                        MapperCategory::toResponse
                ).toList();
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return MapperCategory.toResponse(category);
    }

    @Override
    public CategoryResponseDTO create(CreateCategoryDTO dto) {
        return null;
    }

    @Override
    public CategoryResponseDTO update(UpdateCategoryDTO dto) {
        return null;
    }

    @Override
    public void deactivate(Long id) {

    }
}

package org.atabero.inventory.service;


import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.category.CategoryResponseDTO;
import org.atabero.inventory.dto.category.CreateCategoryDTO;
import org.atabero.inventory.dto.category.UpdateCategoryDTO;
import org.atabero.inventory.exception.category.CategoryAlreadyActivatedException;
import org.atabero.inventory.exception.category.CategoryAlreadyDeactivatedException;
import org.atabero.inventory.exception.category.CategoryNotFoundException;
import org.atabero.inventory.mapper.MapperCategory;
import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.enums.CategoryStatus;
import org.atabero.inventory.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(
                        MapperCategory::toResponse
                ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {
        Category category = findByIdFull(id);
        return MapperCategory.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO create(CreateCategoryDTO dto) {
        Category category = MapperCategory.toEntity(dto);
        category = categoryRepository.save(category);
        return MapperCategory.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO update(Long id, UpdateCategoryDTO dto) {
        Category category = findByIdFull(id);
        MapperCategory.toUpdateCategory(dto,category);
        category = categoryRepository.save(category);
        return MapperCategory.toResponse(category);
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        Category category = findByIdFull(id);
        if (category.getStatus() == CategoryStatus.INACTIVE){
            throw new CategoryAlreadyDeactivatedException(id);
        }
        category.setStatus(CategoryStatus.INACTIVE);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void activate(Long id) {
        Category category = findByIdFull(id);
        if (category.getStatus() == CategoryStatus.ACTIVE) {
            throw new CategoryAlreadyActivatedException(id);
        }
        category.setStatus(CategoryStatus.ACTIVE);
        categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByIdFull(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}

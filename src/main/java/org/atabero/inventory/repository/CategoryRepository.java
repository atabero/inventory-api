package org.atabero.inventory.repository;

import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.enums.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByNameContainingIgnoreCase(String name);

    List<Category> findByStatus(CategoryStatus status);

    List<Category> findByNameContainingIgnoreCaseAndStatus(String name, CategoryStatus status);

    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Category> findByStatus(CategoryStatus status, Pageable pageable);

    Page<Category> findByNameContainingIgnoreCaseAndStatus(String name, CategoryStatus status, Pageable pageable);
}

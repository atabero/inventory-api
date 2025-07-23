package org.atabero.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.atabero.inventory.model.enums.CategoryStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa una categoría de productos en el sistema de inventario.
 *
 * Cada categoría puede contener múltiples productos y tiene un estado asociado.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    /**
     * Identificador único de la categoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    /**
     * Nombre único de la categoría.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Descripción opcional de la categoría.
     */
    private String description;

    /**
     * Lista de productos que pertenecen a esta categoría.
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    /**
     * Estado de la categoría (ACTIVO o INACTIVO).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CategoryStatus status = CategoryStatus.ACTIVE;

    /**
     * Fecha de creación de la categoría. Se asigna automáticamente al persistir.
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * Fecha de la última modificación de la categoría.
     */
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    /**
     * Asigna la fecha de creación y modificación antes de persistir.
     */
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.lastModifiedDate = now;
    }

    /**
     * Actualiza la fecha de modificación antes de una actualización.
     */
    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}

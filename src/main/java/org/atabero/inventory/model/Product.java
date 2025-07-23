package org.atabero.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.atabero.inventory.model.enums.ProductStatus;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un producto en el sistema de inventario.
 *
 * Cada producto tiene información como su código, nombre, precio, stock actual,
 * estado y relaciones con categoría, proveedor y movimientos de stock.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Builder
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    /**
     * Nombre del producto.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Código único del producto.
     */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * Descripción opcional del producto.
     */
    private String description;

    /**
     * Precio del producto.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Cantidad actual en stock del producto.
     */
    @Column(nullable = false)
    private Integer currentStock;

    /**
     * Estado actual del producto (ACTIVO o INACTIVO).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

    /**
     * Categoría a la que pertenece el producto.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Proveedor que suministra este producto.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    /**
     * Lista de movimientos de stock asociados a este producto.
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<StockMovement> stockMovements = new ArrayList<>();

    /**
     * Fecha de creación del producto. Se asigna automáticamente.
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * Fecha de la última modificación del producto.
     */
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    /**
     * Asigna las fechas de creación y modificación antes de persistir la entidad.
     */
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.lastModifiedDate = now;
    }

    /**
     * Actualiza la fecha de modificación antes de actualizar la entidad.
     */
    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}

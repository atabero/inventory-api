package org.atabero.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.atabero.inventory.model.enums.SupplierStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa a un proveedor dentro del sistema de inventario.
 *
 * Contiene información sobre el proveedor, su estado, y los productos que suministra.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
@Builder
public class Supplier implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    /**
     * Identificador único del proveedor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplier")
    private Long id;

    /**
     * Nombre único del proveedor.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Información de contacto del proveedor.
     */
    @Column(columnDefinition = "TEXT")
    private String contactInfo;

    /**
     * Lista de productos asociados a este proveedor.
     */
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    /**
     * Estado actual del proveedor (activo, inactivo, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SupplierStatus status = SupplierStatus.ACTIVE;

    /**
     * Fecha en la que se creó el registro.
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * Fecha de la última modificación del registro.
     */
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    /**
     * Establece la fecha de creación y última modificación antes de persistir.
     */
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.lastModifiedDate = now;
    }

    /**
     * Actualiza la fecha de última modificación antes de actualizar el registro.
     */
    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}

package org.atabero.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.ProductStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad que representa el historial de cambios de estado de un producto.
 *
 * Registra el cambio de estado de un producto (por ejemplo, de ACTIVO a INACTIVO),
 * incluyendo información como la razón, el estado de la operación y la fecha del cambio.
 */
@Entity
@Table(name = "product_status_change_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStatusChangeLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del log de cambio.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * ID del producto al que pertenece el cambio de estado.
     */
    @Column(nullable = false)
    private Long productId;

    /**
     * Estado anterior del producto.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus previousStatus;

    /**
     * Nuevo estado asignado al producto.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus newStatus;

    /**
     * Razón del cambio de estado (opcional).
     */
    @Column(length = 500)
    private String reason;

    /**
     * Resultado de la operación que generó el cambio (ej. SUCCESS o ERROR).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationStatus operationStatus;

    /**
     * Mensaje adicional de la operación, útil para errores u observaciones.
     */
    @Column(length = 500)
    private String operationMessage;

    /**
     * Fecha y hora en que se realizó el cambio.
     */
    @Column(nullable = false)
    private LocalDateTime changedAt;

    /**
     * Asigna la fecha y hora actual antes de persistir si no fue definida.
     */
    @PrePersist
    public void prePersist() {
        if (changedAt == null) {
            changedAt = LocalDateTime.now();
        }
    }
}

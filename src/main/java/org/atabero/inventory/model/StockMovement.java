package org.atabero.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.MovementType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad que representa un movimiento de stock en el sistema de inventario.
 *
 * Cada instancia registra un cambio en el inventario de un producto, ya sea por
 * compras, ventas, ajustes, pérdidas, devoluciones, entre otros.
 */
@Entity
@Table(name = "movement_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMovement implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    /**
     * Identificador único del movimiento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Producto al que se le aplicó el movimiento de stock.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Cantidad modificada en el movimiento.
     * Puede ser positiva (entrada) o negativa (salida).
     */
    @Column(nullable = false)
    private Integer quantityChange;

    /**
     * Cantidad anterior del producto antes del movimiento.
     */
    private Integer previousQuantity;

    /**
     * Cantidad nueva del producto después del movimiento.
     */
    private Integer newQuantity;

    /**
     * Tipo de movimiento (ej: PURCHASE, SALE, ADJUSTMENT_NEGATIVE, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType movementType;

    /**
     * Notas adicionales relacionadas con el movimiento (opcional).
     */
    private String notes;

    /**
     * Estado de la operación (ej: SUCCESS, ERROR).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationStatus operationStatus;

    /**
     * Mensaje adicional de la operación (útil en caso de errores).
     */
    @Column(length = 500)
    private String operationMessage;

    /**
     * Fecha y hora en la que se registró el movimiento.
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /**
     * Asigna la fecha y hora actual antes de persistir si no fue definida.
     */
    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}

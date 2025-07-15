package org.atabero.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.atabero.inventory.model.enums.MovementStatus;
import org.atabero.inventory.model.enums.MovementType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movement_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantityChange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType movementType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementStatus movementStatus;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String notes;

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}
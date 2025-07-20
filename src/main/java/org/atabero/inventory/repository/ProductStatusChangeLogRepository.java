package org.atabero.inventory.repository;

import org.atabero.inventory.model.ProductStatusChangeLog;
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.model.enums.OperationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductStatusChangeLogRepository extends JpaRepository<ProductStatusChangeLog, UUID> {

    // Buscar logs por producto
    List<ProductStatusChangeLog> findByProductId(Long productId);

    // Buscar logs por estado previo
    List<ProductStatusChangeLog> findByPreviousStatus(ProductStatus previousStatus);

    // Buscar logs por nuevo estado
    List<ProductStatusChangeLog> findByNewStatus(ProductStatus newStatus);

    // Buscar logs por estado de operación (éxito o error)
    List<ProductStatusChangeLog> findByOperationStatus(OperationStatus operationStatus);

    // Buscar logs dentro de un rango de fechas
    List<ProductStatusChangeLog> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);

    // Combinaciones, ejemplo: por producto y estado de operación
    List<ProductStatusChangeLog> findByProductIdAndOperationStatus(Long productId, OperationStatus operationStatus);

    // Buscar logs donde el producto cambió de un estado a otro
    List<ProductStatusChangeLog> findByPreviousStatusAndNewStatus(ProductStatus previousStatus, ProductStatus newStatus);

    // Contar logs por tipo de operación (SUCCESS, ERROR)
    long countByOperationStatus(OperationStatus operationStatus);
}

package org.atabero.inventory.service;

import org.atabero.inventory.dto.productstatuschangelog.CreateProductStatusChangeLogDTO;
import org.atabero.inventory.dto.productstatuschangelog.ProductStatusChangeLogResponseDTO;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductStatusChangeLogService {

    // Crear un nuevo log
    ProductStatusChangeLogResponseDTO save(CreateProductStatusChangeLogDTO dto);

    // Buscar todos los logs
    List<ProductStatusChangeLogResponseDTO> findAll();

    // Buscar log por id
    ProductStatusChangeLogResponseDTO findById(UUID id);

    // Buscar logs por id de producto
    List<ProductStatusChangeLogResponseDTO> findByProductId(Long productId);

    // Buscar logs por estado previo
    List<ProductStatusChangeLogResponseDTO> findByPreviousStatus(ProductStatus previousStatus);

    // Buscar logs por nuevo estado
    List<ProductStatusChangeLogResponseDTO> findByNewStatus(ProductStatus newStatus);

    // Buscar logs por estado de operación (SUCCESS, ERROR)
    List<ProductStatusChangeLogResponseDTO> findByOperationStatus(OperationStatus operationStatus);

    // Buscar logs en un rango de fechas
    List<ProductStatusChangeLogResponseDTO> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);

    // Buscar logs por producto y estado de operación
    List<ProductStatusChangeLogResponseDTO> findByProductIdAndOperationStatus(Long productId, OperationStatus operationStatus);

    // Buscar logs donde el producto cambió de un estado a otro
    List<ProductStatusChangeLogResponseDTO> findByPreviousStatusAndNewStatus(ProductStatus previousStatus, ProductStatus newStatus);

    // Contar logs por estado de operación
    long countByOperationStatus(OperationStatus operationStatus);
}

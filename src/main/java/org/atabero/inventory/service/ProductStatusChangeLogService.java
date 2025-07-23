package org.atabero.inventory.service;

import org.atabero.inventory.dto.productstatuschangelog.CreateProductStatusChangeLogDTO;
import org.atabero.inventory.dto.productstatuschangelog.ProductStatusChangeLogResponseDTO;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Servicio para gestionar los registros de cambios de estado de productos.
 */
public interface ProductStatusChangeLogService {

    /**
     * Crea un nuevo registro de cambio de estado de producto.
     *
     * @param dto objeto {@link CreateProductStatusChangeLogDTO} con los datos del cambio.
     * @return objeto {@link ProductStatusChangeLogResponseDTO} con la información del registro creado.
     */
    ProductStatusChangeLogResponseDTO save(CreateProductStatusChangeLogDTO dto);

    /**
     * Obtiene todos los registros de cambios de estado.
     *
     * @return lista de objetos {@link ProductStatusChangeLogResponseDTO} con todos los registros.
     */
    List<ProductStatusChangeLogResponseDTO> findAll();

    /**
     * Busca un registro de cambio por su identificador único.
     *
     * @param id UUID del registro.
     * @return objeto {@link ProductStatusChangeLogResponseDTO} correspondiente al registro.
     * @throws RuntimeException si el registro no existe.
     */
    ProductStatusChangeLogResponseDTO findById(UUID id);

    /**
     * Busca los registros de cambio asociados a un producto específico.
     *
     * @param productId identificador del producto.
     * @return lista de objetos {@link ProductStatusChangeLogResponseDTO} para el producto.
     */
    List<ProductStatusChangeLogResponseDTO> findByProductId(Long productId);

    /**
     * Busca los registros de cambio por estado previo del producto.
     *
     * @param previousStatus estado anterior del producto.
     * @return lista de registros que coinciden con el estado previo.
     */
    List<ProductStatusChangeLogResponseDTO> findByPreviousStatus(ProductStatus previousStatus);

    /**
     * Busca los registros de cambio por nuevo estado del producto.
     *
     * @param newStatus nuevo estado del producto.
     * @return lista de registros que coinciden con el nuevo estado.
     */
    List<ProductStatusChangeLogResponseDTO> findByNewStatus(ProductStatus newStatus);

    /**
     * Busca los registros de cambio por estado de operación (éxito o error).
     *
     * @param operationStatus estado de la operación ({@link OperationStatus}).
     * @return lista de registros con el estado de operación indicado.
     */
    List<ProductStatusChangeLogResponseDTO> findByOperationStatus(OperationStatus operationStatus);

    /**
     * Busca los registros de cambio ocurridos dentro de un rango de fechas.
     *
     * @param start fecha y hora inicial del rango.
     * @param end   fecha y hora final del rango.
     * @return lista de registros dentro del rango especificado.
     */
    List<ProductStatusChangeLogResponseDTO> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Busca los registros de cambio por producto y estado de operación.
     *
     * @param productId       identificador del producto.
     * @param operationStatus estado de la operación.
     * @return lista de registros que coinciden con ambos parámetros.
     */
    List<ProductStatusChangeLogResponseDTO> findByProductIdAndOperationStatus(Long productId, OperationStatus operationStatus);

    /**
     * Busca los registros de cambio donde el producto cambió de un estado específico a otro.
     *
     * @param previousStatus estado previo del producto.
     * @param newStatus      nuevo estado del producto.
     * @return lista de registros que coinciden con ambos estados.
     */
    List<ProductStatusChangeLogResponseDTO> findByPreviousStatusAndNewStatus(ProductStatus previousStatus, ProductStatus newStatus);

    /**
     * Cuenta la cantidad de registros por estado de operación.
     *
     * @param operationStatus estado de la operación.
     * @return número total de registros con el estado especificado.
     */
    long countByOperationStatus(OperationStatus operationStatus);
}

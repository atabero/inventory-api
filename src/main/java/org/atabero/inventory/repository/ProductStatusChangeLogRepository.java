package org.atabero.inventory.repository;

import org.atabero.inventory.model.ProductStatusChangeLog;
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.model.enums.OperationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para acceder a los registros de cambio de estado de productos.
 */
public interface ProductStatusChangeLogRepository extends JpaRepository<ProductStatusChangeLog, UUID> {

    /**
     * Busca los logs asociados a un producto por su ID.
     *
     * @param productId ID del producto.
     * @return lista de logs correspondientes al producto.
     */
    List<ProductStatusChangeLog> findByProductId(Long productId);

    /**
     * Busca los logs donde el estado previo del producto coincide con el especificado.
     *
     * @param previousStatus estado anterior del producto.
     * @return lista de logs con ese estado previo.
     */
    List<ProductStatusChangeLog> findByPreviousStatus(ProductStatus previousStatus);

    /**
     * Busca los logs donde el nuevo estado del producto coincide con el especificado.
     *
     * @param newStatus nuevo estado del producto.
     * @return lista de logs con ese nuevo estado.
     */
    List<ProductStatusChangeLog> findByNewStatus(ProductStatus newStatus);

    /**
     * Busca los logs según el estado de la operación (ej. SUCCESS o ERROR).
     *
     * @param operationStatus estado de la operación.
     * @return lista de logs con ese estado de operación.
     */
    List<ProductStatusChangeLog> findByOperationStatus(OperationStatus operationStatus);

    /**
     * Busca los logs cuyo cambio de estado ocurrió dentro de un rango de fechas.
     *
     * @param start fecha/hora de inicio del rango.
     * @param end   fecha/hora de fin del rango.
     * @return lista de logs dentro del rango temporal.
     */
    List<ProductStatusChangeLog> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Busca logs de un producto específico y con un determinado estado de operación.
     *
     * @param productId        ID del producto.
     * @param operationStatus  estado de la operación.
     * @return lista de logs que cumplen ambos criterios.
     */
    List<ProductStatusChangeLog> findByProductIdAndOperationStatus(Long productId, OperationStatus operationStatus);

    /**
     * Busca los logs donde el estado del producto cambió de uno a otro específico.
     *
     * @param previousStatus estado previo del producto.
     * @param newStatus      nuevo estado del producto.
     * @return lista de logs que coincidan con ambos estados.
     */
    List<ProductStatusChangeLog> findByPreviousStatusAndNewStatus(ProductStatus previousStatus, ProductStatus newStatus);

    /**
     * Cuenta cuántos logs existen con un estado de operación determinado.
     *
     * @param operationStatus estado de la operación.
     * @return número de logs con ese estado.
     */
    long countByOperationStatus(OperationStatus operationStatus);
}

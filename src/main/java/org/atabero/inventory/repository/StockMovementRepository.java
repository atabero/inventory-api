package org.atabero.inventory.repository;

import org.atabero.inventory.model.StockMovement;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.MovementType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio de acceso a datos de los registros de movimientos en el sistema de inventario.
 * Esta interfaz extiende {@link JpaRepository} y proporciona operaciones de búsqueda
 * personalizadas sobre los registros de movimientos, incluyendo búsquedas por atributos directos,
 * por fechas y con soporte para paginación y ordenación.
 */
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

    // --- Búsquedas por Atributos Directos ---

    /**
     * Busca los registros de movimiento asociados a un producto específico.
     *
     * @param product El producto relacionado con los movimientos.
     * @return Una lista de registros de movimiento asociados al producto.
     */
    List<StockMovement> findByProduct(Product product);

    /**
     * Busca los registros de movimiento por tipo de movimiento (ENTRY, SALE, etc.).
     *
     * @param movementType El tipo de movimiento que se busca.
     * @return Una lista de registros de movimiento con el tipo especificado.
     */
    List<StockMovement> findByMovementType(MovementType movementType);

    /**
     * Busca los registros de movimiento por estado (COMPLETED, FAILED, etc.).
     *
     * @param operationStatus El estado del movimiento (por ejemplo, COMPLETED o FAILED).
     * @return Una lista de registros de movimiento con el estado especificado.
     */
    List<StockMovement> findByMovementStatus(OperationStatus operationStatus);

    /**
     * Busca los registros de movimiento cuyo campo de notas contenga una cadena dada,
     * ignorando mayúsculas y minúsculas.
     *
     * @param notes La cadena que debe contener el campo de notas.
     * @return Una lista de registros de movimiento que contienen la cadena dada en las notas.
     */
    List<StockMovement> findByNotesContainingIgnoreCase(String notes);

    // --- Búsquedas por Fechas (Timestamp) ---

    /**
     * Busca los registros de movimiento dentro de un rango de fechas específico.
     *
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha final del rango.
     * @return Una lista de registros de movimiento que se encuentran dentro del rango de fechas.
     */
    List<StockMovement> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Busca los registros de movimiento ocurridos después de una fecha específica.
     *
     * @param startDate La fecha de inicio para los movimientos que se buscan.
     * @return Una lista de registros de movimiento que ocurrieron después de la fecha indicada.
     */
    List<StockMovement> findByTimestampAfter(LocalDateTime startDate);

    /**
     * Busca los registros de movimiento ocurridos antes de una fecha específica.
     *
     * @param endDate La fecha final para los movimientos que se buscan.
     * @return Una lista de registros de movimiento que ocurrieron antes de la fecha indicada.
     */
    List<StockMovement> findByTimestampBefore(LocalDateTime endDate);

    // --- Búsquedas Combinadas ---

    /**
     * Busca los registros de movimiento de un producto específico con un tipo de movimiento específico.
     *
     * @param product El producto relacionado con los movimientos.
     * @param movementType El tipo de movimiento (por ejemplo, ENTRY, SALE).
     * @return Una lista de registros de movimiento asociados al producto y tipo indicados.
     */
    List<StockMovement> findByProductAndMovementType(Product product, MovementType movementType);

    /**
     * Busca los registros de movimiento de un producto específico dentro de un rango de fechas.
     *
     * @param product El producto relacionado con los movimientos.
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha final del rango.
     * @return Una lista de registros de movimiento asociados al producto y que se encuentran dentro del rango de fechas.
     */
    List<StockMovement> findByProductAndTimestampBetween(Product product, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Busca los registros de movimiento por tipo y estado.
     *
     * @param movementType El tipo de movimiento (ENTRY, SALE, etc.).
     * @param operationStatus El estado del movimiento (COMPLETED, FAILED, etc.).
     * @return Una lista de registros de movimiento con el tipo y estado indicados.
     */
    List<StockMovement> findByMovementTypeAndMovementStatus(MovementType movementType, OperationStatus operationStatus);

    // --- Paginación y Ordenación (Esenciales para Logs) ---

    /**
     * Busca los registros de movimiento asociados a un producto específico, permitiendo paginación y ordenación.
     *
     * @param product El producto relacionado con los movimientos.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de registros de movimiento asociados al producto, con paginación y ordenación.
     */
    Page<StockMovement> findByProduct(Product product, Pageable pageable);

    /**
     * Busca los registros de movimiento por tipo de movimiento, permitiendo paginación y ordenación.
     *
     * @param movementType El tipo de movimiento que se busca.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de registros de movimiento con el tipo de movimiento especificado, con paginación y ordenación.
     */
    Page<StockMovement> findByMovementType(MovementType movementType, Pageable pageable);

    /**
     * Busca los registros de movimiento dentro de un rango de fechas, permitiendo paginación y ordenación.
     *
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha final del rango.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de registros de movimiento que se encuentran dentro del rango de fechas, con paginación y ordenación.
     */
    Page<StockMovement> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    /**
     * Realiza una búsqueda avanzada de registros de movimiento por producto, tipo de movimiento y rango de fechas,
     * permitiendo paginación y ordenación.
     *
     * @param product El producto relacionado con los movimientos.
     * @param movementType El tipo de movimiento (ENTRY, SALE, etc.).
     * @param startDate La fecha de inicio del rango.
     * @param endDate La fecha final del rango.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de registros de movimiento que coinciden con los criterios, con paginación y ordenación.
     */
    Page<StockMovement> findByProductAndMovementTypeAndTimestampBetween(
            Product product,
            MovementType movementType,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}

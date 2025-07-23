package org.atabero.inventory.repository;

import org.atabero.inventory.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para gestionar los movimientos de stock.
 *
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD básicas.
 */
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {
}

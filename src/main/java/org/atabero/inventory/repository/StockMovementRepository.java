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

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {
}

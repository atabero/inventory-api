package org.atabero.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.productstatuschangelog.CreateProductStatusChangeLogDTO;
import org.atabero.inventory.dto.productstatuschangelog.ProductStatusChangeLogResponseDTO;
import org.atabero.inventory.exception.product.ProductNotFoundException;
import org.atabero.inventory.exception.productstatuschangelog.ProductStatusChangeLogNotFoundException;
import org.atabero.inventory.mapper.MapperProductStatusChangeLog;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.ProductStatusChangeLog;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.repository.ProductStatusChangeLogRepository;
import org.atabero.inventory.service.ProductService;
import org.atabero.inventory.service.ProductStatusChangeLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductStatusChangeLogServiceImpl implements ProductStatusChangeLogService {

    private final ProductStatusChangeLogRepository changeLogRepository;
    private final ProductService productService;

    @Override
    public ProductStatusChangeLogResponseDTO save(CreateProductStatusChangeLogDTO dto) {
        ProductStatusChangeLog changeLog = new ProductStatusChangeLog();
        changeLog.setProductId(dto.getProductId());
        changeLog.setNewStatus(dto.getNewProductStatus());
        changeLog.setReason(dto.getReason());
        changeLog.setChangedAt(LocalDateTime.now());

        try {
            Product product = productService.getByIdFull(dto.getProductId());

            changeLog.setPreviousStatus(product.getStatus());
            product.setStatus(dto.getNewProductStatus());

            productService.modifyStatus(product);

            changeLog.setOperationStatus(OperationStatus.SUCCESS);
            changeLog.setOperationMessage("Operación aprobada correctamente");
        } catch (ProductNotFoundException ex) {
            changeLog.setPreviousStatus(null);
            changeLog.setOperationStatus(OperationStatus.ERROR);
            changeLog.setOperationMessage("Producto no encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            changeLog.setPreviousStatus(null);
            changeLog.setOperationStatus(OperationStatus.ERROR);
            changeLog.setOperationMessage("Error inesperado: " + ex.getMessage());
        }

        changeLogRepository.save(changeLog);
        return MapperProductStatusChangeLog.toResponse(changeLog);
    }


    @Override
    public List<ProductStatusChangeLogResponseDTO> findAll() {
        return changeLogRepository.findAll().stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public ProductStatusChangeLogResponseDTO findById(UUID id) {
        return MapperProductStatusChangeLog.toResponse(changeLogRepository.findById(id)
                .orElseThrow(
                        () -> new ProductStatusChangeLogNotFoundException(id)
                ));
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByProductId(Long productId) {
        return changeLogRepository.findByProductId(productId).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByPreviousStatus(ProductStatus previousStatus) {
        return changeLogRepository.findByPreviousStatus(previousStatus).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByNewStatus(ProductStatus newStatus) {
        return changeLogRepository.findByNewStatus(newStatus).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByOperationStatus(OperationStatus operationStatus) {
        return changeLogRepository.findByOperationStatus(operationStatus).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByChangedAtBetween(LocalDateTime start, LocalDateTime end) {
        return changeLogRepository.findByChangedAtBetween(start, end).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByProductIdAndOperationStatus(Long productId, OperationStatus operationStatus) {
        return changeLogRepository.findByProductIdAndOperationStatus(productId, operationStatus).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public List<ProductStatusChangeLogResponseDTO> findByPreviousStatusAndNewStatus(ProductStatus previousStatus, ProductStatus newStatus) {
        return changeLogRepository.findByPreviousStatusAndNewStatus(previousStatus, newStatus).stream().map(
                MapperProductStatusChangeLog::toResponse
        ).toList();
    }

    @Override
    public long countByOperationStatus(OperationStatus operationStatus) {
        return changeLogRepository.countByOperationStatus(operationStatus);
    }
}

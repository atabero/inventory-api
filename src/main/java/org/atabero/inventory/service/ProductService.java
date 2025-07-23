package org.atabero.inventory.service;

import org.atabero.inventory.dto.product.CreateProductDTO;
import org.atabero.inventory.dto.product.ProductResponseDTO;
import org.atabero.inventory.dto.product.UpdateProductDTO;
import org.atabero.inventory.model.Product;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con los productos.
 */
public interface ProductService {

    /**
     * Obtiene la lista de todos los productos.
     *
     * @return lista de objetos {@link ProductResponseDTO} con la información de los productos.
     */
    List<ProductResponseDTO> getAll();

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id identificador único del producto.
     * @return un objeto {@link ProductResponseDTO} con la información del producto encontrado.
     * @throws RuntimeException si el producto no existe.
     */
    ProductResponseDTO getById(Long id);

    /**
     * Crea un nuevo producto con la información proporcionada.
     *
     * @param dto objeto {@link CreateProductDTO} con los datos para crear el producto.
     * @return un objeto {@link ProductResponseDTO} con la información del producto creado.
     */
    ProductResponseDTO create(CreateProductDTO dto);

    /**
     * Crea múltiples productos a partir de una lista de datos.
     *
     * @param dtos lista de objetos {@link CreateProductDTO} con los datos para crear los productos.
     * @return lista de objetos {@link ProductResponseDTO} con la información de los productos creados.
     */
    List<ProductResponseDTO> create(List<CreateProductDTO> dtos);

    /**
     * Actualiza un producto existente con los datos proporcionados.
     *
     * @param id  identificador único del producto a actualizar.
     * @param dto objeto {@link UpdateProductDTO} con los datos para actualizar el producto.
     * @return un objeto {@link ProductResponseDTO} con la información actualizada del producto.
     * @throws RuntimeException si el producto no existe.
     */
    ProductResponseDTO update(Long id, UpdateProductDTO dto);

    /**
     * Obtiene la entidad completa de un producto por su identificador.
     *
     * @param id identificador único del producto.
     * @return la entidad {@link Product} completa del producto encontrado.
     * @throws RuntimeException si el producto no existe.
     */
    Product getByIdFull(Long id);

    /**
     * Modifica el estado (activo/inactivo) de un producto.
     *
     * @param product instancia de {@link Product} cuyo estado será modificado.
     */
    void modifyStatus(Product product);

    /**
     * Modifica el stock disponible de un producto.
     *
     * @param product instancia de {@link Product} cuyo stock será modificado.
     */
    void modifyStock(Product product);
}

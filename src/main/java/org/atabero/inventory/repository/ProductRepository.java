package org.atabero.inventory.repository;

import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos de los productos en el sistema de inventario.
 * Esta interfaz extiende {@link JpaRepository}, proporcionando operaciones CRUD básicas
 * y métodos de búsqueda personalizados por diversos atributos como código, nombre, precio,
 * estado, categorías, proveedores y combinaciones de estos, con soporte para paginación y ordenación.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    // --- Búsquedas por Atributos Directos ---

    /**
     * Busca un producto por su código único.
     * Este método devuelve un {@link Optional} que contendrá el producto si existe,
     * o estará vacío si no se encuentra un producto con ese código.
     *
     * @param code El código único del producto que se busca.
     * @return Un {@link Optional<Product>} con el producto encontrado, o {@link Optional#empty()} si no existe.
     */
    Optional<Product> findByCode(String code);

    /**
     * Busca productos cuyo nombre contenga una cadena dada, sin importar mayúsculas o minúsculas.
     * Este tipo de búsqueda es útil para realizar búsquedas parciales o tipo "Google".
     *
     * @param name La cadena a buscar en los nombres de los productos.
     * @return Una lista de productos cuyos nombres contienen la cadena proporcionada.
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Busca productos cuyo precio esté dentro de un rango especificado.
     *
     * @param minPrice El precio mínimo del rango.
     * @param maxPrice El precio máximo del rango.
     * @return Una lista de productos cuyo precio se encuentra dentro del rango especificado.
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Busca productos con un stock inferior a un valor dado.
     *
     * @param stock El valor límite para el stock (productos con stock inferior a este valor).
     * @return Una lista de productos cuyo stock es inferior al valor dado.
     */
    List<Product> findByCurrentStockLessThan(Integer stock);

    /**
     * Busca productos por su estado (activo, inactivo, etc.).
     *
     * @param status El estado del producto (por ejemplo, {@link ProductStatus#ACTIVE}, {@link ProductStatus#INACTIVE}).
     * @return Una lista de productos con el estado especificado.
     */
    List<Product> findByStatus(ProductStatus status);

    // --- Búsquedas por Relaciones (Category y Supplier) ---

    /**
     * Busca productos por su categoría.
     *
     * @param category La categoría del producto.
     * @return Una lista de productos que pertenecen a la categoría especificada.
     */
    List<Product> findByCategory(Category category);

    /**
     * Busca productos por su proveedor.
     *
     * @param supplier El proveedor del producto.
     * @return Una lista de productos que pertenecen al proveedor especificado.
     */
    List<Product> findBySupplier(Supplier supplier);

    // --- Búsquedas Combinadas ---

    /**
     * Busca productos de una categoría específica cuyo stock sea inferior a un valor dado.
     *
     * @param category La categoría del producto.
     * @param stock El valor límite para el stock.
     * @return Una lista de productos que pertenecen a la categoría indicada y cuyo stock es inferior al valor dado.
     */
    List<Product> findByCategoryAndCurrentStockLessThan(Category category, Integer stock);

    /**
     * Busca productos cuyo nombre contenga una cadena dada y cuyo estado coincida con el proporcionado.
     *
     * @param name El nombre (o parte del nombre) del producto.
     * @param status El estado del producto (por ejemplo, activo, inactivo).
     * @return Una lista de productos cuyo nombre contiene la cadena dada y cuyo estado coincide con el especificado.
     */
    List<Product> findByNameContainingIgnoreCaseAndStatus(String name, ProductStatus status);

    // --- Paginación y Ordenación (¡MUY IMPORTANTE!) ---

    /**
     * Busca productos cuyo nombre contenga una cadena dada, con soporte para paginación y ordenación.
     *
     * @param name La cadena a buscar en los nombres de los productos.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de productos cuyo nombre contiene la cadena proporcionada, con paginación y ordenación.
     */
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Busca productos pertenecientes a una categoría específica, con soporte para paginación y ordenación.
     *
     * @param category La categoría del producto.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de productos que pertenecen a la categoría indicada, con paginación y ordenación.
     */
    Page<Product> findByCategory(Category category, Pageable pageable);

    /**
     * Busca productos cuyo estado coincida con el estado proporcionado, con soporte para paginación y ordenación.
     *
     * @param status El estado del producto (activo, inactivo, etc.).
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de productos con el estado especificado, con paginación y ordenación.
     */
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    /**
     * Busca productos cuyo nombre contenga una cadena dada y cuyo estado coincida con el proporcionado,
     * con soporte para paginación y ordenación.
     *
     * @param name El nombre (o parte del nombre) del producto.
     * @param status El estado del producto.
     * @param pageable El objeto que contiene los parámetros de paginación y ordenación.
     * @return Una página de productos cuyo nombre contiene la cadena dada y cuyo estado coincide con el proporcionado,
     *         con paginación y ordenación.
     */
    Page<Product> findByNameContainingIgnoreCaseAndStatus(String name, ProductStatus status, Pageable pageable);
}

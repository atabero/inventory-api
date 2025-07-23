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
 * Repositorio para acceder y manipular datos de la entidad {@link Product}.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca un producto por su código exacto.
     *
     * @param code código único del producto.
     * @return un {@link Optional} que contiene el producto si existe.
     */
    Optional<Product> findByCode(String code);

    /**
     * Busca productos cuyo nombre contenga el texto indicado, sin distinguir mayúsculas.
     *
     * @param name parte del nombre a buscar.
     * @return lista de productos que coinciden parcialmente con el nombre.
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Busca productos cuyo precio se encuentre dentro de un rango.
     *
     * @param minPrice precio mínimo.
     * @param maxPrice precio máximo.
     * @return lista de productos en ese rango de precio.
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Busca productos cuyo stock actual sea menor a un valor determinado.
     *
     * @param stock cantidad de stock a comparar.
     * @return lista de productos con stock inferior al valor indicado.
     */
    List<Product> findByCurrentStockLessThan(Integer stock);

    /**
     * Busca productos por su estado.
     *
     * @param status estado del producto ({@link ProductStatus}).
     * @return lista de productos con el estado especificado.
     */
    List<Product> findByStatus(ProductStatus status);

    /**
     * Busca productos pertenecientes a una categoría específica.
     *
     * @param category categoría del producto.
     * @return lista de productos dentro de esa categoría.
     */
    List<Product> findByCategory(Category category);

    /**
     * Busca productos asociados a un proveedor específico.
     *
     * @param supplier proveedor del producto.
     * @return lista de productos del proveedor.
     */
    List<Product> findBySupplier(Supplier supplier);

    /**
     * Busca productos en una categoría específica con stock inferior al valor dado.
     *
     * @param category categoría del producto.
     * @param stock    valor máximo de stock.
     * @return lista de productos que cumplen ambas condiciones.
     */
    List<Product> findByCategoryAndCurrentStockLessThan(Category category, Integer stock);

    /**
     * Busca productos por nombre parcial y estado.
     *
     * @param name   parte del nombre a buscar.
     * @param status estado del producto.
     * @return lista de productos que coincidan con ambos criterios.
     */
    List<Product> findByNameContainingIgnoreCaseAndStatus(String name, ProductStatus status);

    /**
     * Devuelve una página de productos cuyo nombre contenga el texto indicado.
     *
     * @param name     parte del nombre a buscar.
     * @param pageable objeto de paginación.
     * @return página de productos.
     */
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Devuelve una página de productos de una categoría específica.
     *
     * @param category categoría a filtrar.
     * @param pageable objeto de paginación.
     * @return página de productos.
     */
    Page<Product> findByCategory(Category category, Pageable pageable);

    /**
     * Devuelve una página de productos según su estado.
     *
     * @param status   estado del producto.
     * @param pageable objeto de paginación.
     * @return página de productos.
     */
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    /**
     * Devuelve una página de productos por nombre parcial y estado.
     *
     * @param name     parte del nombre a buscar.
     * @param status   estado del producto.
     * @param pageable objeto de paginación.
     * @return página de productos.
     */
    Page<Product> findByNameContainingIgnoreCaseAndStatus(String name, ProductStatus status, Pageable pageable);
}

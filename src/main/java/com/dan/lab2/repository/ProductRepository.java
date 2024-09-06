package com.dan.lab2.repository;

import com.dan.lab2.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * ProductRepository provides the database access for ProductModel entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    /**
     * Finds a product by its name (case-insensitive).
     *
     * @param name the product name
     * @return an Optional containing the product if found
     */
    Optional<ProductModel> findByNameIgnoreCase(String name);

    /**
     * Finds a product by its price.
     *
     * @param price the product price
     * @return an Optional containing the product if found
     */
    Optional<ProductModel> findByPrice(BigDecimal price);

    /**
     * Finds a product by its stock level.
     *
     * @param stock the stock level
     * @return an Optional containing the product if found
     */
    Optional<ProductModel> findByStock(Integer stock);

    /**
     * Checks if a product with the given name exists.
     *
     * @param name the product name
     * @return true if a product with the name exists, false otherwise
     */
    boolean existsByName(String name);
}

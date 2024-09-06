package com.dan.lab2.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * ProductModel represents the product entity in the database.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tb_product")
public class ProductModel {

    /**
     * The unique identifier for a product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * A description of the product.
     */
    private String description;

    /**
     * The price of the product.
     */
    private BigDecimal price;

    /**
     * The stock level of the product
     */

    private Integer stock;
}
package com.dan.lab2.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * UpdatePartialProductDTO is a data transfer object that allows partial updates
 * to a product. Only non-null fields will be updated.
 *
 * @param name        the new name of the product, optional
 * @param description the new description of the product, optional
 * @param price       the new price of the product, must be positive if provided
 * @param stock       the new stock level of the product, must be positive if provided
 */
public record UpdatePartialProductDTO (
        String name,
        String description,
        @Positive(message = "The field cannot be less than 0") BigDecimal price,
        @Positive(message = "The field cannot be less than 0") Integer stock
){
}

package com.dan.lab2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * ProductDTO is a data transfer object that carries product data
 * with validation constraints.
 *
 * @param name        the name of the product, must not be blank
 * @param description the description of the product
 * @param price       the price of the product, must be positive
 * @param stock       the stock level of the product, must be positive
 */
public record ProductDTO(
        @NotBlank String name,
        String description,
        @NotNull @Positive(message = "The field cannot be less than 0") BigDecimal price,
        @NotNull @Positive(message = "The field cannot be less than 0") Integer stock
) {
}

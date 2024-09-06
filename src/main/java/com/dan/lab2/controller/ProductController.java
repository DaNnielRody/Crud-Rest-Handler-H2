package com.dan.lab2.controller;

import com.dan.lab2.dto.ProductDTO;
import com.dan.lab2.dto.UpdatePartialProductDTO;
import com.dan.lab2.model.ProductModel;
import com.dan.lab2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * ProductController handles HTTP requests related to product operations.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    /**
     * Autowired constructor for ProductController.
     *
     * @param productService the service handling product operations
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.service = productService;
    }

    /**
     * Handles POST request to create a new product.
     *
     * @param productDTO the product data
     * @return ResponseEntity with the created product and 201 status
     */
    @PostMapping("/create")
    public ResponseEntity<ProductModel> create(@RequestBody @Valid ProductDTO productDTO) {
        var createdProduct = service.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Handles GET request to retrieve all products.
     *
     * @return ResponseEntity with a list of all products
     */
    @GetMapping
    public ResponseEntity<List<ProductModel>> findAll() {
        var products = service.readAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Handles GET request to retrieve a product by its ID.
     *
     * @param id the product ID
     * @return ResponseEntity with the product found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Long id) {
        var product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Handles GET request to retrieve a product by its name.
     *
     * @param name the product name
     * @return ResponseEntity with the product found
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductModel> findByName(@PathVariable @Valid String name) {
        var product = service.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    /**
     * Handles GET request to retrieve a product by its price.
     *
     * @param price the product price
     * @return ResponseEntity with the product found
     */
    @GetMapping("/price/{price}")
    public ResponseEntity<ProductModel> findByPrice(@PathVariable @Valid BigDecimal price) {
        var product = service.getProductByPrice(price);
        return ResponseEntity.ok(product);
    }

    /**
     * Handles GET request to retrieve a product by its stock level.
     *
     * @param stock the product stock level
     * @return ResponseEntity with the product found
     */
    @GetMapping("/stock/{stock}")
    public ResponseEntity<ProductModel> findByStock(@PathVariable int stock) {
        var product = service.getProductByStock(stock);
        return ResponseEntity.ok(product);
    }

    /**
     * Handles PUT request to update a product by its ID.
     *
     * @param id         the product ID
     * @param productDTO the updated product data
     * @return ResponseEntity with the updated product
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO){
        var updatedProduct = service.update(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Handles PATCH request to partially update a product by its ID.
     *
     * @param id                  the product ID
     * @param partialProductDTO    the partial product data to update
     * @return ResponseEntity with the updated product
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductModel> updatePartial(@PathVariable Long id, @RequestBody @Valid UpdatePartialProductDTO partialProductDTO) {
        var partialUpdatedProduct = service.updatePartial(id, partialProductDTO);
        return ResponseEntity.ok(partialUpdatedProduct);
    }

    /**
     * Handles DELETE request to delete a product by its ID.
     *
     * @param id the product ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

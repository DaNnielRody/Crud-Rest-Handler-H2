package com.dan.lab2.service;

import com.dan.lab2.dto.ProductDTO;
import com.dan.lab2.dto.UpdatePartialProductDTO;
import com.dan.lab2.model.ProductModel;
import com.dan.lab2.repository.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ProductService provides business logic for managing products, including
 * CRUD operations and validation.
 */
@Service
public class ProductService {

    private final ProductRepository repository;

    /**
     * Constructs the ProductService with a ProductRepository.
     *
     * @param productRepository the repository to interact with product data
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    /**
     * Creates a new product and saves it to the repository.
     *
     * @param productDTO the data of the product to create
     * @return the created ProductModel
     * @throws EntityExistsException if a product with the same name already exists
     */
    @Transactional
    public ProductModel createProduct(ProductDTO productDTO) {
        checkIfExistsByName(productDTO.name());

        var productEntity = ProductModel.builder()
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .stock(productDTO.stock())
                .build();

        return repository.save(productEntity);
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all products
     */
    public List<ProductModel> readAllProducts() {
        return repository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return the ProductModel with the specified ID
     * @throws NoSuchElementException if no product is found with the given ID
     */
    public ProductModel getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name the product name
     * @return the ProductModel with the specified name
     * @throws NoSuchElementException if no product is found with the given name
     */
    public ProductModel getProductByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Product not found with name: " + name));
    }

    /**
     * Retrieves a product by its price.
     *
     * @param price the product price
     * @return the ProductModel with the specified price
     * @throws NoSuchElementException if no product is found with the given price
     */
    public ProductModel getProductByPrice(BigDecimal price) {
        return repository.findByPrice(price)
                .orElseThrow(() -> new NoSuchElementException("Product not found with price: " + price));
    }

    /**
     * Retrieves a product by its stock level.
     *
     * @param stock the stock level
     * @return the ProductModel with the specified stock level
     * @throws NoSuchElementException if no product is found with the given stock level
     */
    public ProductModel getProductByStock(Integer stock) {
        return repository.findByStock(stock)
                .orElseThrow(() -> new NoSuchElementException("Product not found with stock: " + stock));
    }

    /**
     * Updates a product's information by its ID.
     *
     * @param id         the product ID
     * @param productDTO the new product data
     * @return the updated ProductModel
     * @throws EntityExistsException if a product with the new name already exists
     */
    @Transactional
    public ProductModel update(Long id, ProductDTO productDTO) {
        checkIfExistsByName(productDTO.name());

        var product = getProductById(id);
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        product.setStock(productDTO.stock());

        return repository.save(product);
    }

    /**
     * Partially updates a product's information by its ID.
     *
     * @param id                  the product ID
     * @param productUpdateDTO     the new product data for partial update
     * @return the updated ProductModel
     * @throws EntityExistsException if a product with the new name already exists
     */
    @Transactional
    public ProductModel updatePartial(Long id, UpdatePartialProductDTO productUpdateDTO) {
        var product = getProductById(id);

        if (productUpdateDTO.name() != null && !productUpdateDTO.name().isBlank()) {
            checkIfExistsByName(productUpdateDTO.name());
            product.setName(productUpdateDTO.name());
        }

        if (productUpdateDTO.description() != null && !productUpdateDTO.description().isBlank()) {
            product.setDescription(productUpdateDTO.description());
        }

        if (productUpdateDTO.price() != null) {
            product.setPrice(productUpdateDTO.price());
        }

        if (productUpdateDTO.stock() != null) {
            product.setStock(productUpdateDTO.stock());
        }

        return repository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID
     * @throws NoSuchElementException if no product is found with the given ID
     */
    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Product not found: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Check if the product exists by name.
     * @param name of the product
     * @throws EntityExistsException if no product exist with the given name
     */

    private void checkIfExistsByName(String name) {
        if(repository.existsByName(name)){
            throw new EntityExistsException("Product already exists with the name: " + name);
        }
    }
}

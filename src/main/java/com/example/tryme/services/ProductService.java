package com.example.tryme.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tryme.Model.Product;
import com.example.tryme.Repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createProduct(String name, Integer caloriesPer100g) {
        Product product = new Product(name, caloriesPer100g);
        productRepository.save(product);
        return "Product created with ID: " + product.getId();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public String updateProduct(Long id, String name, Integer caloriesPer100g) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(name);
        product.setCaloriesPer100g(caloriesPer100g);
        productRepository.save(product);
        return "Product updated";
    }

    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
        return "Product deleted";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findByNameContainingIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
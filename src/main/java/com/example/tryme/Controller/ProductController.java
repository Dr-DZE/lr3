package com.example.tryme.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.tryme.Model.Product;
import com.example.tryme.services.CaloriesService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final CaloriesService caloriesService;

    @Autowired
    public ProductController(CaloriesService caloriesService) {
        this.caloriesService = caloriesService;
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam String name, @RequestParam Integer caloriesPer100g) {
        return caloriesService.createProduct(name, caloriesPer100g);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return caloriesService.getProduct(id);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam String name, @RequestParam Integer caloriesPer100g) {
        return caloriesService.updateProduct(id, name, caloriesPer100g);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return caloriesService.deleteProduct(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return caloriesService.getAllProducts();
    }
}

// Пример запроса: http://localhost:8080/api/meals/calculate?productCount=2&food=яблоко&food=груша&gram=100&gram=150
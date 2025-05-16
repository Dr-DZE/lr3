package com.example.tryme.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tryme.Model.Product;
import com.example.tryme.services.CaloriesService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CaloriesService caloriesService;

    @Autowired
    public ProductController(CaloriesService caloriesService) {
        this.caloriesService = caloriesService;
    }

	@GetMapping("/CalculateCalories") 
	public List<String> get(@RequestParam Integer productCount,  
        @RequestParam String[] food, 
		@RequestParam Integer[] gram) 
    {
		return caloriesService.calculateCalories(productCount, food, gram);
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

// Пример запроса: http://localhost:8080/products/calculate?productCount=2&food=яблоко&food=груша&gram=100&gram=150
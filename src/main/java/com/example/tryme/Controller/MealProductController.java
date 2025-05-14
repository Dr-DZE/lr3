package com.example.tryme.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.tryme.Model.MealProduct;
import com.example.tryme.services.CaloriesService;

import java.util.List;

@RestController
@RequestMapping("/api/meal-products")
public class MealProductController {
    private final CaloriesService caloriesService;

    @Autowired
    public MealProductController(CaloriesService caloriesService) {
        this.caloriesService = caloriesService;
    }

    @PostMapping("/create")
    public String createMealProduct(@RequestParam Integer grams, @RequestParam Long mealId, @RequestParam Long productId) {
        return caloriesService.createMealProduct(grams, mealId, productId);
    }

    @GetMapping("/{id}")
    public MealProduct getMealProduct(@PathVariable Long id) {
        return caloriesService.getMealProduct(id);
    }

    @PutMapping("/update/{id}")
    public String updateMealProduct(@PathVariable Long id, @RequestParam Integer grams) {
        return caloriesService.updateMealProduct(id, grams);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMealProduct(@PathVariable Long id) {
        return caloriesService.deleteMealProduct(id);
    }

    @GetMapping("/")
    public List<MealProduct> getAllMealProducts() {
        return caloriesService.getAllMealProducts();
    }
}
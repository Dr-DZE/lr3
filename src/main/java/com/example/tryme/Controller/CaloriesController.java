package com.example.tryme.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tryme.services.CaloriesService;

@RestController
@RequestMapping("/api/meals")
public class CaloriesController {
    private final CaloriesService caloriesService;

    @Autowired
    public CaloriesController(CaloriesService caloriesService) {
        this.caloriesService = caloriesService;
    }

    @GetMapping("/calculate")
    public List<String> calculateCalories(
            @RequestParam Integer productCount,
            @RequestParam String[] food,
            @RequestParam Integer[] gram) {
        return caloriesService.calculateCalories(productCount, food, gram);
    }
    // http://localhost:8080/api/meals/calculate?productCount=2&food=%D1%8F%D0%B9&food=%D0%B2%D1%8B&gram=10&gram=10

    @GetMapping("/create")
    public String createMeal(@RequestParam String mealName) {
        return caloriesService.createMeal(mealName);
    }
    // http://localhost:8080/api/meals/create?mealName=Завтрак

    @GetMapping("/add-product")
    public String addProductToMeal(
            @RequestParam Long mealId,
            @RequestParam String productName,
            @RequestParam Integer grams) {
        return caloriesService.addProductToMeal(mealId, productName, grams);
    }
    // http://localhost:8080/api/meals/add-product?mealId=1&productName=Яблоко&grams=150
}
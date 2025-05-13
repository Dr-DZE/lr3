package com.example.tryme.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    // Пример запроса: http://localhost:8080/api/meals/calculate?productCount=2&food=яблоко&food=груша&gram=100&gram=150
}
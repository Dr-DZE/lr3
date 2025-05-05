package com.example.tryme.Controller;

import com.example.tryme.services.CaloriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calories")
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
}
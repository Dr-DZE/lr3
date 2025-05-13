package com.example.tryme.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.tryme.Model.Meal;
import com.example.tryme.services.CaloriesService;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final CaloriesService caloriesService;

    @Autowired
    public MealController(CaloriesService caloriesService) {
        this.caloriesService = caloriesService;
    }

    // Создание нового блюда
    @PostMapping("/create")
    public String createMeal(@RequestParam String mealName) {
        return caloriesService.createMeal(mealName);
    }
    // Пример запроса: http://localhost:8080/api/meals/create?mealName=Завтрак

    // Получение блюда по ID
    @GetMapping("/{id}")
    public Meal getMeal(@PathVariable Long id) {
        return caloriesService.getMeal(id);
    }
    // Пример запроса: http://localhost:8080/api/meals/1

    // Обновление блюда по ID
    @GetMapping("/update/{id}")
    public String updateMeal(@PathVariable Long id, @RequestParam String newName) {
        return caloriesService.updateMeal(id, newName);
    }
    // Пример запроса: http://localhost:8080/api/meals/update/1?newName=Ужин

    // Удаление блюда по ID
    @DeleteMapping("/delete/{id}")
    public String deleteMeal(@PathVariable Long id) {
        return caloriesService.deleteMeal(id);
    }
    // Пример запроса: http://localhost:8080/api/meals/delete/1
}
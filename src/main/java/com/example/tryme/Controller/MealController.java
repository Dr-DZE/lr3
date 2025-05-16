package com.example.tryme.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tryme.Model.Meal;
import com.example.tryme.services.CaloriesService;

    @RestController
    @RequestMapping("/meals")
    public class MealController {
        private final CaloriesService caloriesService;

        @Autowired
        public MealController(CaloriesService caloriesService) {
            this.caloriesService = caloriesService;
        }

        @PostMapping("/create")
        public String createMeal(@RequestParam String mealName) {
            return caloriesService.createMeal(mealName);
        }
        // Пример запроса: http://localhost:8080/meals/create?mealName=Завтрак

        @GetMapping("/{id}")
        public Meal getMeal(@PathVariable Long id) {
            return caloriesService.getMeal(id);
        }
        // Пример запроса: http://localhost:8080/api/meals/1

        @GetMapping("/update/{id}")
        public String updateMeal(@PathVariable Long id, @RequestParam String newName) {
            return caloriesService.updateMeal(id, newName);
        }
        // Пример запроса: http://localhost:8080/api/meals/update/1?newName=Ужин

        @DeleteMapping("/delete/{id}")
        public String deleteMeal(@PathVariable Long id) {
            return caloriesService.deleteMeal(id);
        }
        // Пример запроса: http://localhost:8080/api/meals/delete/1
    }
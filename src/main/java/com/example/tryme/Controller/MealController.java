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

import com.example.tryme.Model.Meal;
import com.example.tryme.services.CacheService;
import com.example.tryme.services.MealService;

@RestController
@RequestMapping("/meals")
public class MealController {
    private final MealService mealService;
    private final CacheService cacheService;

    @Autowired
    public MealController(MealService mealService, CacheService cacheService) {
        this.mealService = mealService;
        this.cacheService = cacheService;
    }

    @GetMapping("/by-product")
    public List<Meal> getMealsByProduct(@RequestParam String productName) {
        List<Meal> cachedMeals = cacheService.getFromCache("mealsByProduct", productName);
        if (cachedMeals != null) {
            return cachedMeals;
        }
        List<Meal> meals = mealService.findMealsByProductName(productName);
        cacheService.putToCache("mealsByProduct", productName, meals);
        return meals;
    }

    @PostMapping("/create")
    public String createMeal(@RequestParam String mealName) {
        cacheService.clearCache("mealsByProduct");
        return mealService.createMeal(mealName);
    }

    @GetMapping("/{id}")
    public Meal getMeal(@PathVariable Long id) {
        return mealService.getMeal(id);
    }

    @PutMapping("/update/{id}")
    public String updateMeal(@PathVariable Long id, @RequestParam String newName) {
        cacheService.clearCache("mealsByProduct");
        return mealService.updateMeal(id, newName);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMeal(@PathVariable Long id) {
        cacheService.clearCache("mealsByProduct");
        return mealService.deleteMeal(id);
    }

    @GetMapping("/")
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }
}
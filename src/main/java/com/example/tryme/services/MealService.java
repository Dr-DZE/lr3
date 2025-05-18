package com.example.tryme.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tryme.Model.Meal;
import com.example.tryme.Repository.MealRepository;

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

        public List<Meal> findMealsByProductName(String productName) {
        return mealRepository.findMealsByProductName(productName);
    }

    public String createMeal(String mealName) {
        Meal meal = new Meal(mealName);
        mealRepository.save(meal);
        return "Meal '" + mealName + "' created with ID: " + meal.getId();
    }

    public Meal getMeal(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    public String updateMeal(Long id, String newName) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        meal.setName(newName);
        mealRepository.save(meal);
        return "Meal updated to '" + newName + "'";
    }

    public String deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        mealRepository.delete(meal);
        return "Meal deleted";
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
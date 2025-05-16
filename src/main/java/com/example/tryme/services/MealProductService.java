package com.example.tryme.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tryme.Model.MealProduct;
import com.example.tryme.Repository.MealProductRepository;

@Service
public class MealProductService {
    private final MealProductRepository mealProductRepository;

    public MealProductService(MealProductRepository mealProductRepository) {
        this.mealProductRepository = mealProductRepository;
    }

    public String createMealProduct(Integer grams, Long mealId, Long productId, 
                                  MealService mealService, ProductService productService) {
        var meal = mealService.getMeal(mealId);
        var product = productService.getProduct(productId);
        
        MealProduct mealProduct = new MealProduct(grams, meal, product);
        mealProductRepository.save(mealProduct);
        return "MealProduct created with ID: " + mealProduct.getId();
    }

    public MealProduct getMealProduct(Long id) {
        return mealProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MealProduct not found"));
    }

    public String updateMealProduct(Long id, Integer grams) {
        MealProduct mealProduct = mealProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MealProduct not found"));
        mealProduct.setGrams(grams);
        mealProductRepository.save(mealProduct);
        return "MealProduct updated";
    }

    public String deleteMealProduct(Long id) {
        MealProduct mealProduct = mealProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MealProduct not found"));
        mealProductRepository.delete(mealProduct);
        return "MealProduct deleted";
    }

    public List<MealProduct> getAllMealProducts() {
        return mealProductRepository.findAll();
    }
}
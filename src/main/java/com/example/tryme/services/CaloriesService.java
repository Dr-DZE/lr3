package com.example.tryme.services;

import com.example.tryme.Model.*;
import com.example.tryme.Repository.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.util.*;

@Service
public class CaloriesService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ProductRepository productRepository;
    private final MealRepository mealRepository;
    private final MealProductRepository mealProductRepository;

    public CaloriesService(ProductRepository productRepository, 
                         MealRepository mealRepository,
                         MealProductRepository mealProductRepository) {
        this.productRepository = productRepository;
        this.mealRepository = mealRepository;
        this.mealProductRepository = mealProductRepository;
    }

    private String sendPostRequest(String query) {
        String url = "https://calculat.ru/wp-content/themes/EmptyCanvas/db123.php";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("term", query);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        
        return response.getBody();
    }

    private String getNameFromWeb(String query, Integer[] caloriesIn100, Integer numberOfFood) {    
        try {
            String body = this.sendPostRequest(query);
            ObjectMapper objectMapper = new ObjectMapper();
            String response = "";
        
            JsonNode jsonNode = objectMapper.readTree(body);
            JsonNode match = jsonNode.get("results").get(0);

            response += match.get("text").asText();
            response += " / cal/100g: ";
            caloriesIn100[numberOfFood] = match.get("cal").asInt();
            response += match.get("cal").asText();
            
            Product product = new Product(match.get("text").asText(), match.get("cal").asInt());
            productRepository.save(product);
            
            return response;
        } catch (JsonProcessingException e) {
            return "Error processing response";
        }
    }

    public List<String> calculateCalories(Integer productCount, String[] food, Integer[] gram) {
        List<String> listOfProducts = new ArrayList<>();
        Integer[] caloriesIn100 = new Integer[productCount];
        Integer totalCalories = 0;

        Meal meal = new Meal("Meal " + new Date());
        mealRepository.save(meal);

        for (int i = 0; i < productCount; i++) {
            String temp = gram[i] + "g." + " " + this.getNameFromWeb(food[i], caloriesIn100, i);
            totalCalories += caloriesIn100[i] * gram[i] / 100;
            listOfProducts.add(temp);

            Product product = productRepository.findByNameContainingIgnoreCase(food[i]).get(0);
            MealProduct mealProduct = new MealProduct(gram[i], meal, product);
            mealProductRepository.save(mealProduct);
        }

        listOfProducts.add("Total calories: " + totalCalories);
        return listOfProducts;
    }

    public String createMeal(String mealName) {
        Meal meal = new Meal(mealName);
        mealRepository.save(meal);
        return "Meal '" + mealName + "' created with ID: " + meal.getId();
    }

    public String addProductToMeal(Long mealId, String productName, Integer grams) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        
        List<Product> products = productRepository.findByNameContainingIgnoreCase(productName);
        if (products.isEmpty()) {
            return "Product not found. Please add it first using the calculate endpoint.";
        }
        Product product = products.get(0);
        
        MealProduct mealProduct = new MealProduct(grams, meal, product);
        mealProductRepository.save(mealProduct);
        
        int calories = product.getCaloriesPer100g() * grams / 100;
        
        return String.format("Added %dg of %s (%d kcal) to meal '%s'",
                grams, product.getName(), calories, meal.getName());
    }
}
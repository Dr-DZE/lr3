package com.example.tryme.services;

import com.example.tryme.Model.Product;
import com.example.tryme.Repository.ProductRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaloriesService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ProductRepository productRepository;

    public CaloriesService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
            
            // Сохраняем продукт в базу данных
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

        for (int i = 0; i < productCount; i++) {
            String temp = gram[i] + "g." + " " + this.getNameFromWeb(food[i], caloriesIn100, i);
            totalCalories += caloriesIn100[i] * gram[i] / 100;
            listOfProducts.add(temp);
        }

        listOfProducts.add("Total calories: " + totalCalories);
        return listOfProducts;
    }
}
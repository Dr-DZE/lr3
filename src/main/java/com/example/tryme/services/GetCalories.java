package com.example.tryme.services;

import java.util.ArrayList;
import java.util.List;

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

@Service
public class GetCalories {

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendPostRequest(String query) {
        String url = "https://calculat.ru/wp-content/themes/EmptyCanvas/db123.php";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // Set body parameters
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("term", query);

        // Create the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        // comment
        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        
        return response.getBody();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public String GetNameFromWeb(String query) {    

        try {
            String body = this.sendPostRequest(query);
            ObjectMapper objectMapper = new ObjectMapper();
            String response = "";
        
            JsonNode jsonNode = objectMapper.readTree(body);
            JsonNode match = jsonNode.get("results").get(0);
            System.out.println(match);
            response += match.get("text").asText();
            response += " / cal/100g: ";
            response += match.get("cal").asText();
            return response;
        
        } catch (JsonProcessingException e) {
            return "lol somethig went wrong";
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public int GetCaloriesFromWeb(String query) {    

        try {
            String body = this.sendPostRequest(query);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(body);
                JsonNode match = jsonNode.get("results").get(0);
                return match.get("cal").asInt();
            } catch (final JsonProcessingException e) {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
        
    public List<String> Show(Integer ProductList, String[] food, Integer[] gram){
        List<String> list = new ArrayList<>();

        for (int i = 0; i < ProductList; i++){
            String temp = gram[i] + "g." + " " + this.GetNameFromWeb(food[i]) + " total calories: " + (this.GetCaloriesFromWeb(food[i]) * gram[i] / 100) + "\n";
            list.add(temp);
        }

        return list;
    }
}

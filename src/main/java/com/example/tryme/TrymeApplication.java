package com.example.tryme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tryme.services.GetCalories;

@SpringBootApplication
@RestController
public class TrymeApplication {
	
	@Autowired
	private GetCalories calculator;
	
	public static void main(String[] args) {
		SpringApplication.run(TrymeApplication.class, args);
	}

	@GetMapping("/CalculateCalories") 
	public String get(@RequestParam Integer ProductList,  
			@RequestParam String[] food, 
			@RequestParam Integer[] gram) {
		return calculator.Show(ProductList, food, gram);
	}


}

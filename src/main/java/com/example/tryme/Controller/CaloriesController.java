package com.example.tryme.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tryme.services.CaloriesService;

@RestController
public class CaloriesController 
{

	@Autowired
	private CaloriesService calculator;

	@GetMapping("/CalculateCalories") 
	public List<String> get(@RequestParam Integer productCount,  
        @RequestParam String[] food, 
		@RequestParam Integer[] gram) 
    {
		return calculator.show(productCount, food, gram);
	}

}

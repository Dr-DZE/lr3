package com.example.tryme.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tryme.services.GetCalories;

@RestController
public class Restcontrol 
{

	@Autowired
	private GetCalories calculator;

	@GetMapping("/CalculateCalories") 
	public List<String> get(@RequestParam Integer productList,  
        @RequestParam String[] food, 
		@RequestParam Integer[] gram) 
    {
		return calculator.show(productList, food, gram);
	}

}

package com.example.tryme.Repository;

import com.example.tryme.Model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
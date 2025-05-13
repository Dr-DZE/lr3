package com.example.tryme.Model;

import jakarta.persistence.*;

@Entity
public class MealProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer grams;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public MealProduct() {}

    public MealProduct(Integer grams, Meal meal, Product product) {
        this.grams = grams;
        this.meal = meal;
        this.product = product;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getGrams() { return grams; }
    public void setGrams(Integer grams) { this.grams = grams; }
    public Meal getMeal() { return meal; }
    public void setMeal(Meal meal) { this.meal = meal; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
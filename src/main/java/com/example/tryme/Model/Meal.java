package com.example.tryme.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<MealProduct> products;

    public Meal() {}

    public Meal(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<MealProduct> getProducts() { return products; }
    public void setProducts(List<MealProduct> products) { this.products = products; }
}
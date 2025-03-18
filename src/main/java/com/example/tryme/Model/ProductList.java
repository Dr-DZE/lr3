package com.example.tryme.Model;

public class ProductList {

    private Integer productCount;
    private String[] food;
    private Integer[] gram;
    private Integer[] caloriesIn100;

    public ProductList() { }

    public ProductList(Integer productCount, String[] food, Integer[] gram, Integer[] caloriesIn100)
    {
        this.productCount = productCount;
        this.food = food;
        this.gram = gram;
        this.caloriesIn100 = caloriesIn100;
    }

    public void setProductCount(Integer productCount)
    {
        this.productCount = productCount;
    }

    public void setFood(String[] food)
    {
        this.food = food;
    }

    public void setGram(Integer[] gram)
    {
        this.gram = gram;
    }

    public void setCaloriesIn100(Integer[] caloriesIn100)
    {
        this.caloriesIn100 = caloriesIn100;
    }

    public Integer getProductCount()
    {
        return productCount;
    }

    public String[] getFood()
    {
        return food;
    }

    public Integer[] getGram()
    {
        return gram;
    }

    public Integer[] getCaloriesIn100()
    {
        return caloriesIn100;
    }
}

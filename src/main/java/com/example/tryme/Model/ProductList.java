package com.example.tryme.Model;

public class ProductList {

    private Integer productNumber;
    private String[] food;
    private Integer[] gram;
    private Integer[] caloriesIn100;

    public ProductList() { }

    public ProductList(Integer productNumber, String[] food, Integer[] gram, Integer[] caloriesIn100)
    {
        this.productNumber = productNumber;
        this.food = food;
        this.gram = gram;
        this.caloriesIn100 = caloriesIn100;
    }

    public void setProductNumber(Integer productNumber)
    {
        this.productNumber = productNumber;
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

    public Integer getProductNumber()
    {
        return productNumber;
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

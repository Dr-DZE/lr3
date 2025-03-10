package com.example.tryme.Model;

public class ProductList {

    private Integer productList;
    private String[] food;
    private Integer[] gram;

    public ProductList() { }

    public ProductList(Integer productList, String[] food, Integer[] gram)
    {
        this.productList = productList;
        this.food = food;
        this.gram = gram;
    }

    public void setProductList(Integer productList)
    {
        this.productList = productList;
    }

    public void setFood(String[] food)
    {
        this.food = food;
    }

    public void setGram(Integer[] gram)
    {
        this.gram = gram;
    }


    public Integer getProductList()
    {
        return productList;
    }

    public String[] getFood()
    {
        return food;
    }

    public Integer[] getGram()
    {
        return gram;
    }

}

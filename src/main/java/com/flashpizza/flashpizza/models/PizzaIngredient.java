package com.flashpizza.flashpizza.models;


public class PizzaIngredient{

    private String id;
    private String id_pizza;
    private String id_ingredient;
    public PizzaIngredient(String id, String id_pizza, String id_ingredient) {
        this.id = id;
        this.id_pizza = id_pizza;
        this.id_ingredient = id_ingredient;
    }
    public PizzaIngredient() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId_pizza() {
        return id_pizza;
    }
    public void setId_pizza(String id_pizza) {
        this.id_pizza = id_pizza;
    }
    public String getId_ingredient() {
        return id_ingredient;
    }
    public void setId_ingredient(String id_ingredient) {
        this.id_ingredient = id_ingredient;
    }
}
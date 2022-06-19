package com.flashpizza.flashpizza.models;

import java.io.Console;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.event.PrintJobListener;

public class Ingredient {

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient(){

    }

    public Ingredient(String id,String name){
        this.id = id;
        this.name = name;
    }

    public boolean isInPizza(String idPizza) throws SQLDataException {
        try {
            PizzaIngredientAPI pizzaIngredientAPI = new PizzaIngredientAPI();
            ArrayList<PizzaIngredient> list_pizzaIngredients = pizzaIngredientAPI.get_pizzaIngredients(idPizza);
            for (PizzaIngredient pizzaIngredient : list_pizzaIngredients) {
                if(pizzaIngredient.getId_ingredient().equals(this.id)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }

    }
}
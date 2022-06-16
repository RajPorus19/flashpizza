package com.flashpizza.flashpizza.models;


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
}
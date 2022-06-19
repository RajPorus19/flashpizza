package com.flashpizza.flashpizza.models;


public class State{

    private String id;
    private String name;
    public State(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public State() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
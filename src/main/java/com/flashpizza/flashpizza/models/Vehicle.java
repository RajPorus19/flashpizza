package com.flashpizza.flashpizza.models;


public class Vehicle{

    private String id;
    private String state_id;
    private String type_id;
    private String matriculation;
    public Vehicle(String id, String state_id,String type_id, String matriculation) {
        this.id = id;
        this.state_id = state_id;
        this.type_id = type_id;
        this.matriculation = matriculation;
    }
    public Vehicle() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getState_id() {
        return state_id;
    }
    public void setState_id(String state_id) {
        this.state_id = state_id;
    }
    public String getType_id() {
        return type_id;
    }
    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
    public String getMatriculation() {
        return matriculation;
    }
    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

}
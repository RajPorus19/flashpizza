package com.flashpizza.flashpizza.models;


public class Messenger{

    private String id;
    private String state_id;
    private String vehicule_id;
    private String name;
    private String phone_number;

    public Messenger() {
    }

    public Messenger(String id, String state_id, String vehicule_id, String name, String phone_number) {
        this.id = id;
        this.state_id = state_id;
        this.vehicule_id = vehicule_id;
        this.name = name;
        this.phone_number = phone_number;
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

    public String getVehicule_id() {
        return vehicule_id;
    }

    public void setVehicule_id(String vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }



}
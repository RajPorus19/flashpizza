package com.flashpizza.flashpizza.models;


public class Order{

    private String id;
    private String user_id;
    private String state_id;
    private String messenger_id;
    private String order_date;
    private String delivery_date;
    private String price;
    public Order(String id, String user_id, String state_id, String messenger_id, String order_date,
            String delivery_date, String price) {
        this.id = id;
        this.user_id = user_id;
        this.state_id = state_id;
        this.messenger_id = messenger_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.price = price;
    }
    public Order() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getState_id() {
        return state_id;
    }
    public void setState_id(String state_id) {
        this.state_id = state_id;
    }
    public String getMessenger_id() {
        return messenger_id;
    }
    public void setMessenger_id(String messenger_id) {
        this.messenger_id = messenger_id;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getDelivery_date() {
        return delivery_date;
    }
    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
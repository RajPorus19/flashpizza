package com.flashpizza.flashpizza.models;


public class OrderLine{

    private String id;
    private String order_id;
    private String pizza_id;
    private String size_id;
    private String quantity;
    private String price;
    public OrderLine(String id, String order_id, String pizza_id, String size_id, String quantity, String price) {
        this.id = id;
        this.order_id = order_id;
        this.pizza_id = pizza_id;
        this.size_id = size_id;
        this.quantity = quantity;
        this.price = price;
    }
    public OrderLine() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public String getPizza_id() {
        return pizza_id;
    }
    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }
    public String getSize_id() {
        return size_id;
    }
    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }


}
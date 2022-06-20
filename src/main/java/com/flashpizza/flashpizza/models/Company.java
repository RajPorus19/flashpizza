package com.flashpizza.flashpizza.models;

public class Company {
    private String revenue;
    private String best_customer;
    private String average_order_price;
    public Company(String revenue, String best_customer,String average_order_price) {
        this.revenue = revenue;
        this.best_customer = best_customer;
        this.average_order_price = average_order_price;
    }
    public String get_revenue() {return revenue;}
    public String get_best_customer() {return best_customer;}
    public String get_average_order_price() {return average_order_price;}
}

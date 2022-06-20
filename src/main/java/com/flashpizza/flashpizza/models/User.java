package com.flashpizza.flashpizza.models;


public class User {

    private String id;


    private String firstname;
    private String password;
    private String email;
    private String phone_number;
    private String balance;

    public User(){

    }

    public User(String id,String firstname, String password, String email, String phone_number, String balance){
        this.id = id;
        this.firstname = firstname;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.balance = balance;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

    public String getFirstname(){
        return this.firstname;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getBalance() {return balance;}
    public void setBalance(String balance) {
        this.balance = balance;
    }

}
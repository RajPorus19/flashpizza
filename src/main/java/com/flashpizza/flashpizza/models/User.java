package com.flashpizza.flashpizza.models;


public class User {

    private String id;


    private String username;
    private String password;
    private String email;
    private String phone_number;

    public User(){

    }

    public User(String id,String username, String password, String email, String phone_number){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
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

    public void setUsername(String username) {
		this.username = username;
	}

    public String getUsername(){
        return this.username;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

}
package com.flashpizza.flashpizza.models;


public class User {

    private String username;
    private String password;
    private String email;
    private String phone_number;


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

}
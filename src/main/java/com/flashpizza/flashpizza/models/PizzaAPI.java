package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class PizzaAPI{
	
	Database db = new Database();
	private ArrayList<Pizza> pizzas_list;
	
	public PizzaAPI() throws SQLException {
		this.pizzas_list = get_pizzas();
	}


	
	public ArrayList<Pizza> get_pizzas() throws SQLException {
		
    	ResultSet res = db.query_db("select * from pizza");
    	ArrayList<Pizza> pizzas_list = new ArrayList<Pizza>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String name = res.getString("name");
    			String price = res.getString("price");

				Pizza pizza = new Pizza(id,name,price);
    			pizzas_list.add(pizza);
    		}
    	}

		return pizzas_list;
	}

	public void addPizza(Pizza pizza) throws SQLException{
		db.update_db("INSERT INTO pizza (name,price) VALUES (\""+pizza.getName()+"\",'"
		+ pizza.getPrice()+"\"");
	}
	public void deletepizza(String id){
		db.update_db("DELETE from pizza WHERE id="+id);
	}
	public Pizza getPizza(String id) throws SQLException{
		ResultSet res = db.query_db("select * from pizza where id="+id);
		while(res.next()) {
			String name = res.getString("name");
			String price = res.getString("price");
		
			Pizza pizza = new Pizza(id,name,price);
			return pizza;
		}
		return null;
	}

	public void save(Pizza pizza) throws SQLException{
		String sql = "UPDATE pizza SET ";
		sql += "name = '" + pizza.getName() + "',";
		sql += "price = '" + pizza.getPrice() + "' ";
		sql += "WHERE id=" + pizza.getId();
		db.update_db(sql);
	}
}

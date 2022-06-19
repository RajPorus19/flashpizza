package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class PizzaIngredientAPI{
	
	Database db = new Database();

	public PizzaIngredientAPI() throws SQLException{

	}

	public ArrayList<PizzaIngredient> get_pizzaIngredients(String pizzaId) throws SQLException {
		
    	ResultSet res = db.query_db("select * from pizza_ingredient where pizza_id="+pizzaId);
    	ArrayList<PizzaIngredient> pizzaIngredients_list = new ArrayList<PizzaIngredient>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String id_pizza = res.getString("pizza_id");
    			String id_ingredient = res.getString("ingredient_id");

				PizzaIngredient pizzaIngredient = new PizzaIngredient(id,id_pizza,id_ingredient);
    			pizzaIngredients_list.add(pizzaIngredient);
    		}
    	}

		return pizzaIngredients_list;
	}

	public void addPizzaIngredient(PizzaIngredient pizzaIngredient) throws SQLException{
		String sql = "INSERT INTO pizza_ingredient (pizza_id,ingredient_id) VALUES ("+pizzaIngredient.getId_pizza()+","
		+ pizzaIngredient.getId_ingredient()+") " 
		+ "ON DUPLICATE KEY UPDATE pizza_id="+pizzaIngredient.getId_pizza()+
		", ingredient_id="+pizzaIngredient.getId_ingredient();
		db.update_db(sql);
	}
	public void deletepizzaIngredient(String pizzaId, String ingredientId){
		db.update_db("DELETE from pizza_ingredient WHERE pizza_id="+pizzaId+" and ingredient_id="+ingredientId);
	}
	public PizzaIngredient getPizzaIngredient(String id) throws SQLException{
		ResultSet res = db.query_db("select * from pizza_ingredient where id="+id);
		while(res.next()) {
			String id_pizza = res.getString("pizza_id");
			String id_ingredient = res.getString("ingredient_id");
		
			PizzaIngredient pizzaIngredient = new PizzaIngredient(id,id_pizza,id_ingredient);
			return pizzaIngredient;
		}
		return null;
	}


	public void save(PizzaIngredient pizzaIngredient) throws SQLException{
		String sql = "UPDATE pizza SET ";
		sql += "pizza_id = '" + pizzaIngredient.getId_pizza() + "',";
		sql += "ingredient_id = '" + pizzaIngredient.getId_ingredient() + "' ";
		sql += "WHERE id=" + pizzaIngredient.getId();
		db.update_db(sql);
	}
}

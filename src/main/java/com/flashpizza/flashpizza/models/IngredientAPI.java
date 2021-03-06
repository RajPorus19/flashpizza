package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class IngredientAPI {
	
	Database db = new Database();
	private ArrayList<Ingredient> ingredients_list;
	
	public IngredientAPI() throws SQLException {
		this.ingredients_list = this.get_ingredients();
	}


	
	public ArrayList<Ingredient> get_ingredients() throws SQLException {
		
    	ResultSet res = db.query_db("select * from ingredient");
    	ArrayList<Ingredient> ingredient_list = new ArrayList<Ingredient>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String name = res.getString("name");
    		
				Ingredient ingredient = new Ingredient(id,name);
    			ingredient_list.add(ingredient);
    		}
    	}

		return ingredient_list;
	}

	public Ingredient getIngredient(String id) throws SQLException{
		ResultSet res = db.query_db("select * from ingredient where id="+id);
		while(res.next()) {
			String name = res.getString("name");
		
			Ingredient ingredient= new Ingredient(id,name);
			return ingredient;
		}
		return null;
	}

	public void addIngredient(Ingredient ingredient) throws SQLException{
		db.update_db("INSERT INTO ingredient (name) VALUES (\""+ingredient.getName()+"\")");
	}


	public void save(Ingredient ingredient) throws SQLException{
		String sql = "UPDATE ingredient SET ";
		sql += "name = \"" + ingredient.getName() + "\" ";
		sql += "WHERE id=" + ingredient.getId();
		db.update_db(sql);
	}

    public void deleteIngredient(String id) {
		db.update_db("DELETE from ingredient WHERE id="+id);
    }

}

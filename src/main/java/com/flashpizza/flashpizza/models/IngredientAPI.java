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

}

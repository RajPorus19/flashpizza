package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class PizzaSizeAPI {
	
	Database db = new Database();
	
	public PizzaSizeAPI() throws SQLException {
	}


	
	public ArrayList<PizzaSize> get_pizzasize_list() throws SQLException {
		
    	ResultSet res = db.query_db("select * from pizza_size");
    	ArrayList<PizzaSize> pizzasize_list = new ArrayList<PizzaSize>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String name = res.getString("name");
				String coefficient = Double.toString(res.getDouble("coefficient"));
    		
				PizzaSize pizzasize = new PizzaSize(id,name,coefficient);
				pizzasize_list.add(pizzasize);
    		}
    	}

		return pizzasize_list;
	}

	public PizzaSize getPizzaSize(String id) throws SQLException{
		ResultSet res = db.query_db("select * from pizza_size where id="+id);
		while(res.next()) {
			String name = res.getString("name");
			String coefficient = Double.toString(res.getDouble("coefficient"));
			PizzaSize pizzasize = new PizzaSize(id,name,coefficient);
			return pizzasize;
		}
		return null;
	}

	public void add(PizzaSize pizzasize) throws SQLException{
		db.update_db("INSERT INTO ingredient (name,coefficient) VALUES (\""+pizzasize.getName()+"\","+pizzasize.getCoefficient()+")");
	}


}

package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.flashpizza.flashpizza.Database;

public class UserAPI {
	
	Database db = new Database();
	private ArrayList<String> users_list;
	
	public UserAPI() throws SQLException {
		this.users_list = get_users();
	}


	
	public ArrayList<String> get_users() throws SQLException {
		
    	ResultSet res = db.query_db("select * from user");
    	ArrayList<String> users_list = new ArrayList<String>();
    	if(res != null) {
        	while(res.next()) {
    			String id = "id : "+res.getInt("id");
    			String name  = "username : "+res.getString("username");
    			String line = id + " " + name;
    			users_list.add(line);
    		}
    	}
    	else {
    		users_list.add("No data fetched");
    	}

		return users_list;
	}

	public void addUser(User user) throws SQLException{
		db.update_db("INSERT INTO user (username,phone_number,email,password,balance) VALUES (\""+user.getUsername()+"\",'"
		+ user.getPhone_number()+"','"
		+user.getEmail()+"','"
		+user.getPassword()+"',0)");
	}
}

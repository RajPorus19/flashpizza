package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flashpizza.flashpizza.Database;

public class UserAPI {
	
	Database db = new Database();
	private ArrayList<User> users_list;
	
	public UserAPI() throws SQLException {
		this.users_list = get_users();
	}


	
	public ArrayList<User> get_users() throws SQLException {
		
    	ResultSet res = db.query_db("select * from user");
    	ArrayList<User> users_list = new ArrayList<User>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String username  = res.getString("username");
    			String password  = res.getString("password");
    			String email  = res.getString("email");
    			String phone_number  = res.getString("phone_number");
    		
				User user = new User(id,username,password,email,phone_number);
    			users_list.add(user);
    		}
    	}

		return users_list;
	}

	public void addUser(User user) throws SQLException{
		db.update_db("INSERT INTO user (username,phone_number,email,password,balance) VALUES (\""+user.getUsername()+"\",'"
		+ user.getPhone_number()+"','"
		+user.getEmail()+"','"
		+user.getPassword()+"',0)");
	}
	public void deleteUser(String id){
		db.update_db("DELETE from user WHERE id="+id);
	}
	public User getUser(String id) throws SQLException{
		ResultSet res = db.query_db("select * from user where id="+id);
		while(res.next()) {
			String username  = res.getString("username");
			String password  = res.getString("password");
			String email  = res.getString("email");
			String phone_number  = res.getString("phone_number");
		
			User user = new User(id,username,password,email,phone_number);
			return user;
		}
		return null;
	}

	public void save(User user) throws SQLException{
		String sql = "UPDATE user SET ";
		sql += "username = '" + user.getUsername() + "',";
		sql += "email = '" + user.getEmail() + "',";
		sql += "phone_number = '" + user.getPhone_number() + "',";
		sql += "password = '" + user.getPassword() + "'";
		sql += "WHERE id=" + user.getId();
		db.update_db(sql);
	}
	public String get_current_order_id(String userId) throws SQLException{
		ResultSet res = db.query_db("select * from order where user_id="+userId);
    	ResultSet tmpForBasket = db.query_db("select id,name from order_state");
    	ArrayList<User> users_list = new ArrayList<User>();
		String basketId = "1";
		Boolean noBasketFound = true;
    	if(tmpForBasket != null) {
        	while(tmpForBasket.next()) {
				String id = Integer.toString(tmpForBasket.getInt("id"));
				basketId = id;
    			String name = tmpForBasket.getString("name");
				if(name.equals("Basket")){
					noBasketFound = false;
					break;
				}
    		}
			if(noBasketFound){
				db.update_db("INSET INTO order_state (name) VALUES ('basket')");
				return get_current_order_id(userId);
			}
    	}
		else{
			db.update_db("INSET INTO order_state (name) VALUES ('basket')");
			return get_current_order_id(userId);
		}
		if(res!=null){
        	while(res.next()) {
				String id = Integer.toString(tmpForBasket.getInt("id"));
				String state_id= Integer.toString(tmpForBasket.getInt("state_id"));
				basketId = id;
				if(state_id.equals(basketId)){
					return id;
				}
			}
		}

		String sql = "insert into order (user_id) VALUES (+"+userId+")";
		db.update_db(sql);
		return get_current_order_id(userId);
	}
}

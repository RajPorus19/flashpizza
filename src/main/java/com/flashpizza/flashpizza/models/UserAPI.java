package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    			String firstname  = res.getString("firstname");
    			String password  = res.getString("password");
    			String email  = res.getString("email");
    			String phone_number  = res.getString("phone_number");
    		
				User user = new User(id,firstname,password,email,phone_number);
    			users_list.add(user);
    		}
    	}

		return users_list;
	}

	public void addUser(User user) throws SQLException{
		db.update_db("INSERT INTO user (firstname,phone_number,email,password,balance) VALUES (\""+user.getFirstname()+"\",'"
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
			String firstname  = res.getString("firstname");
			String password  = res.getString("password");
			String email  = res.getString("email");
			String phone_number  = res.getString("phone_number");
		
			User user = new User(id,firstname,password,email,phone_number);
			return user;
		}
		return null;
	}

	public void save(User user) throws SQLException{
		String sql = "UPDATE user SET ";
		sql += "firstname = '" + user.getFirstname() + "',";
		sql += "email = '" + user.getEmail() + "',";
		sql += "phone_number = '" + user.getPhone_number() + "',";
		sql += "password = '" + user.getPassword() + "'";
		sql += "WHERE id=" + user.getId();
		db.update_db(sql);
	}
	public String unwrapped_get_current_order_id(String userId) throws SQLException{
		String basketId = "1";
		String sql = "select id from order WHERE user_id="+userId+" and state_id="+basketId;
		System.out.println(sql);
		ResultSet res = db.query_db(sql);
		if(res!=null){
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
				return id;
			}
		}
		return null;
	}
	public String get_current_order_id(String userId) throws SQLException{
		String res = unwrapped_get_current_order_id(userId);
		if(res == null){
			Database ndb = new Database();
			String sql = "insert into order (user_id) VALUES ("+userId+")";
			System.out.println(sql);
		}
		res = unwrapped_get_current_order_id(userId);
		return res;
	};
	public void addOrderline(String userId,String pizza_id, String size_id,String quantity) throws SQLException{
		String  orderId = get_current_order_id(userId);
		String sql = "insert into order_line (order_id,pizza_id,size_id,quantity) VALUES ("
		+ orderId + "," + pizza_id + "," + quantity +")";
		System.out.print(sql);
		db.update_db(sql);
	}
	public ArrayList<OrderLine> getOrderLines(String userId) throws SQLException{
		String orderId = get_current_order_id(userId);
		ResultSet res = db.query_db("select * from order_line where order_id="+orderId);
		ArrayList<OrderLine> list_order_lines = new ArrayList<OrderLine>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
				String order_id = Integer.toString(res.getInt("order_id"));
				String pizza_id= Integer.toString(res.getInt("pizza_id"));
				String size_id = Integer.toString(res.getInt("size_id"));
				String quantity= Integer.toString(res.getInt("quantity"));
				String price= Double.toString(res.getDouble("price"));
    		
				OrderLine orderLine = new OrderLine(id,order_id,pizza_id,size_id,quantity,price);
    			list_order_lines.add(orderLine);
    		}
    	}
		return list_order_lines;

	}
}

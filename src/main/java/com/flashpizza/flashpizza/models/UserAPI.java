package com.flashpizza.flashpizza.models;

import java.math.BigDecimal;
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
				Double balance= res.getDouble("balance");
				String balanceStr = Double.toString(balance);
    		
				User user = new User(id,firstname,password,email,phone_number, balanceStr);
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
			String balance  = res.getString("balance");
		
			User user = new User(id,firstname,password,email,phone_number,balance);
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

	public void addBalance(String userId, String amount) {
		String sql = "insert into transaction (user_id, amount) values (" + userId +","+ amount+")";
		System.out.print(sql);
		db.update_db(sql);
	}
}

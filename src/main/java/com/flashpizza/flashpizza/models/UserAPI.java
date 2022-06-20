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

	public void createOrder(String id) throws SQLException{
		String sql = "INSERT INTO flashpizza.order (user_id) VALUES ("+id+")";
		System.out.println(sql);
		db.update_db(sql);
	}
	public void addOrderLine(String userId, String orderId, OrderLine orderline) throws SQLException{
		String sql = "INSERT INTO  order_line (order_id, pizza_id, size_id, quantity) VALUES ("+
		orderId + "," + orderline.getPizza_id() + "," + orderline.getSize_id() + "," + orderline.getQuantity()
		+")";
		db.update_db(sql);
	}

	public ArrayList<Order> getOrders(String userId) throws SQLException{
		String sql = "SELECT * FROM flashpizza.order where user_id=" + userId;
		ResultSet res = db.query_db(sql);
		ArrayList<Order> order_list = new ArrayList<Order>();
		while(res.next()) {
			String id = Integer.toString(res.getInt("id"));
			String state_id = Integer.toString(res.getInt("state_id"));
			Double price = res.getDouble("price");

			String strPrice = Double.toString(price);
		
			Order order = new Order();
			order.setId(id);
			order.setState_id(state_id);
			order.setPrice(strPrice);
			order_list.add(order);
		}
		return order_list;
	}

	public void addBalance(String userId, String amount) {
		String sql = "insert into transaction (user_id, amount) values (" + userId +","+ amount+")";
		System.out.print(sql);
		db.update_db(sql);
	}

	public String get_revenue() throws SQLException{
		String  sql = "select revenue from revenue";
		ResultSet res = db.query_db(sql);
		if(res==null) return null;
		while(res.next()) {
			Double rev = res.getDouble("revenue");
			String  revenue = Double.toString(rev);
			return revenue;
		}
		return null;
	}

	public String getAvgPrice() throws SQLException{
		String sql = """
			SELECT AVG(o.price) as \"avg_order\"
			FROM flashpizza.order o
				""";
		ResultSet res = db.query_db(sql);
		if(res==null) return null;
		while(res.next()) {
			Double rev = res.getDouble("avg_order");
			String  revenue = Double.toString(rev);
			return revenue;
		}
		return null;
	}

	public String bestClients() throws SQLException{
		String sql = """
			SELECT u.firstname as \"firstname\", COUNT(*) as \"no_order\", SUM(o.price) as \"total_price\"
			FROM flashpizza.order o
			JOIN flashpizza.user u on u.id = o.user_id
			GROUP BY o.user_id
			HAVING SUM(o.price) > 
			(SELECT AVG(o.price) as \"Moyenne des commandes\"
			FROM flashpizza.order o);
				""";
		ResultSet res = db.query_db(sql);
		if(res==null) return null;
		String  revenue = "";
		while(res.next()) {
			String rev = res.getString("firstname");
			revenue += rev + "; ";
		}
		return revenue;
	}

	public String  customerExp() throws SQLException{
		String sql = """
			SELECT *
FROM customer_expanses as c
WHERE c.expanses = (SELECT Max(expanses) FROM customer_expanses)	
				""";
		ResultSet res = db.query_db(sql);
		if(res==null) return null;
		String  revenue = "";
		while(res.next()) {
			String rev = res.getString("firstname");
			revenue += rev + "; ";
		}
		return revenue;
	}
}

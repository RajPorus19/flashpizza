package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flashpizza.flashpizza.Database;

public class OrderAPI{
	
	Database db = new Database();
	private ArrayList<Order> orders_list;
	
	public OrderAPI() throws SQLException {
		this.orders_list = get_orders();
	}


	
	public ArrayList<Order> get_orders() throws SQLException {
    	ResultSet res = db.query_db("select * from order");
    	ArrayList<Order> orders_list = new ArrayList<>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String user_id = res.getString("user_id");
    			String state_id = res.getString("state_id");
    			String messenger_id = res.getString("messenger_id");
    			String order_date = res.getString("order_date");
    			String delivery_date = res.getString("delivery_date");
    			String price = res.getString("price");

				Order order = new Order(id, user_id, state_id, messenger_id, order_date, delivery_date, price);
    			orders_list.add(order);
    		}
    	}

		return orders_list;
	}

	public void addOrder(Order order) throws SQLException{
		db.update_db("INSERT INTO order (user_id, state_id, messenger_id, order_date, delivery_date, price)) VALUES (\""+order.getUser_id()+"\",'"
		+ order.getState_id()+"','"
		+ order.getMessenger_id()+"','"
		+ order.getOrder_date()+"','"
		+ order.getDelivery_date()+"','"
		+order.getPrice()+"')");
	}
	public void deleteorder(String id){
		db.update_db("DELETE from order WHERE id="+id);
	}
	public Order getOrder(String id) throws SQLException{
		ResultSet res = db.query_db("select * from order where id="+id);
		while(res.next()) {
			String user_id = res.getString("user_id");
			String state_id = res.getString("state_id");
			String messenger_id = res.getString("messenger_id");
			String order_date = res.getString("order_date");
			String delivery_date = res.getString("delivery_date");
			String price = res.getString("price");

			Order order = new Order(id, user_id, state_id, messenger_id, order_date, delivery_date, price);
			return order;
		}
		return null;
	}

	public String getBasketInfo(String orderId) throws SQLException{
		String sql = """
SELECT m.name as \"Livreur\", vt.name as \"Type vehicule\", u.firstname as \"Nom client\", o.order_date as \"Date de commande\", p.name as \"Pizza\", ol.quantity as \"Quantité\", ol.price as \"Prix\", o.price as \"Prix total\"
FROM flashpizza.order o
LEFT JOIN flashpizza.messenger m on m.id = o.messenger_id
LEFT JOIN flashpizza.user u on u.id = o.user_id
LEFT JOIN flashpizza.vehicle v on v.id = m.vehicle_id
LEFT JOIN flashpizza.vehicle_type vt on vt.id = v.type_id
LEFT JOIN flashpizza.order_line ol on ol.order_id = o.id
LEFT JOIN flashpizza.pizza p on p.id = ol.pizza_id
WHERE o.id =""" + orderId;
		ResultSet res = db.query_db(sql);
		String returnString = "";
		while(res.next()) {
			String pizzaName = res.getString("Pizza");
			int qte = res.getInt("Quantité");
			String qteStr = Integer.toString(qte);
			returnString += qteStr + " " + pizzaName + "(s), ";
		}
		return returnString;
	}

	public void save(Order order) throws SQLException{
		String sql = "UPDATE order SET ";
		sql += "user_id= '" + order.getUser_id() + "',";
		sql += "state_id = '" + order.getState_id() + "' ";
		sql += "messenger_id = '" + order.getMessenger_id() + "' ";
		sql += "order_date= '" + order.getOrder_date() + "' ";
		sql += "delivery_date = '" + order.getDelivery_date() + "' ";
		sql += "price = '" + order.getOrder_date() + "' ";
		sql += "WHERE id=" + order.getId();
		db.update_db(sql);
	}
}

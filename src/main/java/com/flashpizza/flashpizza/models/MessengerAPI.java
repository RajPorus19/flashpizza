package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class MessengerAPI{
	
	Database db = new Database();
	private ArrayList<Messenger> messengers_list;
	
	public MessengerAPI() throws SQLException {
		this.messengers_list = get_messngers();
	}


	
	public ArrayList<Messenger> get_messngers() throws SQLException {
		
    	ResultSet res = db.query_db("select * from order");
    	ArrayList<Messenger> messengers_list = new ArrayList<>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String state_id = res.getString("state_id");
    			String vehicule_id = res.getString("vehicule_id");
    			String name = res.getString("name");
    			String phone_number = res.getString("phone_nummber");

				Messenger messenger = new Messenger(id,state_id,vehicule_id,name,phone_number);
    			messengers_list.add(messenger);
    		}
    	}

		return messengers_list;
	}

	public void addMessenger(Messenger messenger) throws SQLException{
		db.update_db("INSERT INTO user (name,state_id,vehicule_id,phone_number) VALUES (\""+messenger.getName()+"\",'"
		+ messenger.getState_id()+"','"
		+ messenger.getVehicule_id()+"','"
		+messenger.getPhone_number()+"')");
	}
	public void deletemessenger(String id){
		db.update_db("DELETE from messenger WHERE id="+id);
	}
	public Messenger getMessenger(String id) throws SQLException{
		ResultSet res = db.query_db("select * from messenger where id="+id);
		while(res.next()) {
			String state_id = res.getString("state_id");
			String vehicule_id = res.getString("vehicule_id");
			String name = res.getString("name");
			String phone_number = res.getString("phone_nummber");
			Messenger messenger = new Messenger(id,state_id,vehicule_id,name,phone_number);
			return messenger;
		}
		return null;
	}

	public void save(Messenger messenger) throws SQLException{
		String sql = "UPDATE messenger SET ";
		sql += "name = '" + messenger.getName() + "',";
		sql += "state_id = '" + messenger.getState_id() + "' ";
		sql += "vehicule_id = '" + messenger.getVehicule_id() + "' ";
		sql += "phone_number = '" + messenger.getPhone_number() + "' ";
		sql += "WHERE id=" + messenger.getId();
		db.update_db(sql);
	}
}

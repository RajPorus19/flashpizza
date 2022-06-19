package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class VehicleAPI{
	
	Database db = new Database();
	private ArrayList<Vehicle> vehicle_list;
	
	public VehicleAPI() throws SQLException {
		this.vehicle_list = get_vehicles();
	}


	
	public ArrayList<Vehicle> get_vehicles() throws SQLException {
		
    	ResultSet res = db.query_db("select * from vehicle");
    	ArrayList<Vehicle> vehicles_list = new ArrayList<Vehicle>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String state_id = res.getString("state_id");
    			String type_id = res.getString("type_id");
    			String matriculation = res.getString("matriculation");


				Vehicle vehicle = new Vehicle(id,state_id,type_id,matriculation);
				vehicle_list.add(vehicle);
    		}
    	}

		return vehicles_list;
	}

	public void addVehicle(Vehicle vehicle) throws SQLException{
		String sql = "INSERT INTO vehicle (id,state_id,type_id,matriculation) VALUES ("+
		vehicle.getId()+ "," +
		vehicle.getState_id()+","+
		vehicle.getType_id()+",'"+
		vehicle.getMatriculation()+"')";

		System.out.print(sql);

		db.update_db(sql);
	}
	public void deletevehicle(String id){
		db.update_db("DELETE from vehicle WHERE id="+id);
	}
	public Vehicle getVehicle(String id) throws SQLException{
		ResultSet res = db.query_db("select * from vehicle where id="+id);
		while(res.next()) {
			String state_id = res.getString("state_id");
			String type_id = res.getString("type_id");
			String matriculation = res.getString("matriculation");


			Vehicle vehicle = new Vehicle(id,state_id,type_id,matriculation);
			return vehicle;
		}
		return null;
	}

	public void save(Vehicle vehicle) throws SQLException{
		String sql = "UPDATE vehicle SET ";
		sql += "state_id = " + vehicle.getState_id() + ",";
		sql += "type_id=" + vehicle.getType_id() + ",";
		sql += "vehicle_id = '" + vehicle.getMatriculation() + "' ";
		sql += "WHERE id=" + vehicle.getId();
		db.update_db(sql);
	}
}

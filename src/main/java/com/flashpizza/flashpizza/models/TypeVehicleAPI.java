package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class TypeVehicleAPI{
	
	Database db = new Database();
	
	public TypeVehicleAPI() throws SQLException {
	}


	
	public ArrayList<TypeVehicle> get_typeVehicles() throws SQLException {
		
    	ResultSet res = db.query_db("select * from vehicle_type");
    	ArrayList<TypeVehicle> typeVehicles_list = new ArrayList<TypeVehicle>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String name = res.getString("name");


				TypeVehicle typeVehicle = new TypeVehicle(id,name);
				typeVehicles_list.add(typeVehicle);
    		}
    	}

		return typeVehicles_list;
	}

	public void addVehicleType(TypeVehicle typevehicle) throws SQLException{
		String sql = "INSERT INTO vehicle_type (name) VALUES ('"+
		typevehicle.getName()+"')";

		System.out.print(sql);

		db.update_db(sql);
	}
	public void deletetypevehicle(String id){
		db.update_db("DELETE from vehicle_type WHERE id="+id);
	}
	public TypeVehicle getTypeVehicle(String id) throws SQLException{
		ResultSet res = db.query_db("select * from vehicle_type where id="+id);
		while(res.next()) {
			String name = res.getString("name");

			TypeVehicle typeVehicle = new TypeVehicle(id,name);
			return typeVehicle;
		}
		return null;
	}

	public void save(TypeVehicle vehicletype) throws SQLException{
		String sql = "UPDATE vehicle_type SET ";
		sql += "name = '" + vehicletype.getName() + "' ";
		sql += "WHERE id=" + vehicletype.getId();
		db.update_db(sql);
	}
}

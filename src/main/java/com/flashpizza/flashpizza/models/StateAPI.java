package com.flashpizza.flashpizza.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.flashpizza.flashpizza.Database;

public class StateAPI{
	
	Database db = new Database();
	
	public StateAPI() throws SQLException {
	}


	
	public ArrayList<State> get_states() throws SQLException {
		
    	ResultSet res = db.query_db("select * from state");
    	ArrayList<State> state_list = new ArrayList<State>();
    	if(res != null) {
        	while(res.next()) {
				String id = Integer.toString(res.getInt("id"));
    			String name = res.getString("name");


				State state = new State(id,name);
				state_list.add(state);
    		}
    	}

		return state_list;
	}

	public void addState(State state) throws SQLException{
		String sql = "INSERT INTO state (name) VALUES ('"+
		state.getName()+"')";

		System.out.print(sql);

		db.update_db(sql);
	}
	public void deleteState(String id){
		db.update_db("DELETE from state WHERE id="+id);
	}
	public State getState(String id) throws SQLException{
		ResultSet res = db.query_db("select * from state where id="+id);
		while(res.next()) {
			String name = res.getString("name");

			State state = new State(id,name);
			return state;
		}
		return null;
	}

	public void save(State state) throws SQLException{
		String sql = "UPDATE state SET ";
		sql += "name = '" + state.getName() + "' ";
		sql += "WHERE id=" + state.getId();
		db.update_db(sql);
	}
}

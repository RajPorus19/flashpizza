package com.flashpizza.flashpizza;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	Connection connection;
	public void connect_db() {
		// default MYSQL port
		String url = "jdbc:mysql://localhost:3306/flashpizza?serverTimezone=UTC";
		String username="root";
		String password="password";
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			this.connection = con;

			/*
			ResultSet resultSet = statement.executeQuery("select * from test_table");
			while(resultSet.next()) {
				System.out.println("id : "+resultSet.getInt("id"));
				System.out.println("name : "+resultSet.getString("name"));
			}
			*/
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ResultSet query_db(String query){
		try {
			Statement statement = this.connection.createStatement();
			ResultSet res = statement.executeQuery(query);
			// return the result of the query
			return res;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// in case the query fails
		return null;
	}
	

}

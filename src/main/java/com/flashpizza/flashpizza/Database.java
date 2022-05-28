package com.flashpizza.flashpizza;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public static void get_test_table() {
		// default mysql port
		String url = "jdbc:mysql://localhost:3306/flashpizza?serverTimezone=UTC";
		String username="root";
		String password="password";
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			Statement statement = con.createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from test_table");
			while(resultSet.next()) {
				System.out.println("id : "+resultSet.getInt("id"));
				System.out.println("name : "+resultSet.getString("name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}

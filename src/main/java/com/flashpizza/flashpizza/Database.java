package com.flashpizza.flashpizza;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public Connection connection;
	public Database(){
		this.connect_db();
		this.init_db();
	}
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

	public void init_db(){
		String createUserTable = """
			CREATE TABLE IF NOT EXISTS `flashpizza`.`user` (
			`id` INT NOT NULL AUTO_INCREMENT,
			`phone_number` VARCHAR(15) NOT NULL,
			`username` VARCHAR(16) NOT NULL,
			`email` VARCHAR(255) NULL,
			`password` VARCHAR(32) NOT NULL,
			`create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
			PRIMARY KEY (`id`))
		""";
		this.update_db(createUserTable);
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

	public boolean update_db(String update){
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(update);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// in case the query fails
		return false;
	}
	

}

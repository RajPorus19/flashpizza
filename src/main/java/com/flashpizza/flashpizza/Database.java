package com.flashpizza.flashpizza;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.mysql.cj.jdbc.DatabaseMetaData;


public class Database {
	public String user="root";
	public String password="password";
	public String port="3306";
	public String JDBCurl = "jdbc:mysql://localhost:"+port+"/flashpizza?serverTimezone=UTC";
	public String sql = """

		-- MySQL Script generated by MySQL Workbench
		-- Wed Jun  8 13:54:12 2022
		-- Model: New Model    Version: 1.0
		-- MySQL Workbench Forward Engineering
		
		SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
		SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
		SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
		
		-- -----------------------------------------------------
		-- Schema flashpizza
		-- -----------------------------------------------------
		
		-- -----------------------------------------------------
		-- Schema flashpizza
		-- -----------------------------------------------------
		CREATE SCHEMA IF NOT EXISTS `flashpizza` DEFAULT CHARACTER SET utf8 ;
		USE `flashpizza` ;
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`user`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`user` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `phone_number` VARCHAR(15) NOT NULL,
		  `username` VARCHAR(16) NOT NULL,
		  `email` VARCHAR(255) NULL,
		  `password` VARCHAR(32) NOT NULL,
		  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
		  `balance` DECIMAL(18,2) NOT NULL DEFAULT 0,
		  PRIMARY KEY (`id`));
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`order_state`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`order_state` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `name` VARCHAR(45) NOT NULL,
		  PRIMARY KEY (`id`))
		ENGINE = InnoDB;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`state`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`state` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `available` TINYINT NOT NULL,
		  PRIMARY KEY (`id`))
		ENGINE = InnoDB;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`messenger`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`messenger` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `state_id` INT NOT NULL,
		  `name` VARCHAR(45) NULL,
		  `phone_number` VARCHAR(15) NOT NULL,
		  PRIMARY KEY (`id`),
		  CONSTRAINT `fk_messenger_state1`
			FOREIGN KEY (`state_id`)
			REFERENCES `flashpizza`.`state` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION)
		ENGINE = InnoDB;
		
		CREATE INDEX `fk_messenger_state1_idx` ON `flashpizza`.`messenger` (`state_id` ASC) VISIBLE;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`vehicle_type`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`vehicle_type` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `name` VARCHAR(45) NOT NULL,
		  PRIMARY KEY (`id`))
		ENGINE = InnoDB;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`vehicle`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`vehicle` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `state_id` INT NOT NULL,
		  `type_id` INT NOT NULL,
		  `matriculation` VARCHAR(45) NOT NULL,
		  PRIMARY KEY (`id`),
		  CONSTRAINT `fk_vehicle_state1`
			FOREIGN KEY (`state_id`)
			REFERENCES `flashpizza`.`state` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_vehicle_vehicle_type1`
			FOREIGN KEY (`type_id`)
			REFERENCES `flashpizza`.`vehicle_type` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION)
		ENGINE = InnoDB;
		
		CREATE INDEX `fk_vehicle_state1_idx` ON `flashpizza`.`vehicle` (`state_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_vehicle_vehicle_type1_idx` ON `flashpizza`.`vehicle` (`type_id` ASC) VISIBLE;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`order`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`order` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `user_id` INT NOT NULL,
		  `state_id` INT NOT NULL,
		  `messenger_id` INT NOT NULL,
		  `vehicle_id` INT NOT NULL,
		  `order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		  `delivery_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
		  `price` DECIMAL(5,2) NULL,
		  PRIMARY KEY (`id`),
		  CONSTRAINT `fk_order_user`
			FOREIGN KEY (`user_id`)
			REFERENCES `flashpizza`.`user` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_order_order_state1`
			FOREIGN KEY (`state_id`)
			REFERENCES `flashpizza`.`order_state` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_order_messenger1`
			FOREIGN KEY (`messenger_id`)
			REFERENCES `flashpizza`.`messenger` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_order_vehicle1`
			FOREIGN KEY (`vehicle_id`)
			REFERENCES `flashpizza`.`vehicle` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION)
		ENGINE = InnoDB;
		
		CREATE INDEX `fk_order_user_idx` ON `flashpizza`.`order` (`user_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_order_order_state1_idx` ON `flashpizza`.`order` (`state_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_order_messenger1_idx` ON `flashpizza`.`order` (`messenger_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_order_vehicle1_idx` ON `flashpizza`.`order` (`vehicle_id` ASC) VISIBLE;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`pizza`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`pizza` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `name` VARCHAR(45) NOT NULL,
		  `price` DECIMAL(4,2) NOT NULL,
		  PRIMARY KEY (`id`))
		ENGINE = InnoDB;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`pizza_size`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`pizza_size` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `name` VARCHAR(45) NOT NULL,
		  `coefficient` DECIMAL(3,2) NOT NULL DEFAULT 1.00,
		  PRIMARY KEY (`id`))
		ENGINE = InnoDB;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`order_line`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`order_line` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `order_id` INT NOT NULL,
		  `pizza_id` INT NOT NULL,
		  `size_id` INT NOT NULL,
		  `quantity` INT NOT NULL DEFAULT 1,
		  `price` DECIMAL(5,2) NULL,
		  PRIMARY KEY (`id`),
		  CONSTRAINT `fk_order_line_order1`
			FOREIGN KEY (`order_id`)
			REFERENCES `flashpizza`.`order` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_order_line_pizza1`
			FOREIGN KEY (`pizza_id`)
			REFERENCES `flashpizza`.`pizza` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_order_line_pizza_size1`
			FOREIGN KEY (`size_id`)
			REFERENCES `flashpizza`.`pizza_size` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION)
		ENGINE = InnoDB;
		
		CREATE INDEX `fk_order_line_order1_idx` ON `flashpizza`.`order_line` (`order_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_order_line_pizza1_idx` ON `flashpizza`.`order_line` (`pizza_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_order_line_pizza_size1_idx` ON `flashpizza`.`order_line` (`size_id` ASC) VISIBLE;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`ingredient`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`ingredient` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `name` VARCHAR(45) NOT NULL,
		  PRIMARY KEY (`id`))
		ENGINE = InnoDB;
		
		
		-- -----------------------------------------------------
		-- Table `flashpizza`.`pizza_ingredient`
		-- -----------------------------------------------------
		CREATE TABLE IF NOT EXISTS `flashpizza`.`pizza_ingredient` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `pizza_id` INT NOT NULL,
		  `ingredient_id` INT NOT NULL,
		  PRIMARY KEY (`id`),
		  CONSTRAINT `fk_pizza_ingredient_pizza1`
			FOREIGN KEY (`pizza_id`)
			REFERENCES `flashpizza`.`pizza` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION,
		  CONSTRAINT `fk_pizza_ingredient_ingredient1`
			FOREIGN KEY (`ingredient_id`)
			REFERENCES `flashpizza`.`ingredient` (`id`)
			ON DELETE NO ACTION
			ON UPDATE NO ACTION)
		ENGINE = InnoDB;
		
		CREATE INDEX `fk_pizza_ingredient_pizza1_idx` ON `flashpizza`.`pizza_ingredient` (`pizza_id` ASC) VISIBLE;
		
		CREATE INDEX `fk_pizza_ingredient_ingredient1_idx` ON `flashpizza`.`pizza_ingredient` (`ingredient_id` ASC) VISIBLE;
		
		
		SET SQL_MODE=@OLD_SQL_MODE;
		SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
		SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
		
		  

			""";

	public Database() throws SQLException{
	}
	public Connection connect_db() {
		// default MYSQL port
		
		
		try {
			Connection con = DriverManager.getConnection(this.JDBCurl,this.user,this.password);

			/*
			ResultSet resultSet = statement.executeQuery("select * from test_table");
			while(resultSet.next()) {
				System.out.println("id : "+resultSet.getInt("id"));
				System.out.println("name : "+resultSet.getString("name"));
			}
			*/
			return con;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public void create_db() throws SQLException {
		String nonSchemaURL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
        Connection con = DriverManager.getConnection(nonSchemaURL, this.user, this.password);

        
        Statement stmt = con.createStatement();

        String sql = "CREATE SCHEMA IF NOT EXISTS flashpizza";
        stmt.executeUpdate(sql);
	}

	public void init_db() throws SQLException{
		//Registering the Driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//Initialize the script runner
		ScriptRunner sr = new ScriptRunner(this.connect_db());
		try (//Creating a reader object
			Reader reader = new StringReader(this.sql)) {
			//Running the script
			sr.runScript(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet query_db(String query){
		try {
			Statement statement = this.connect_db().createStatement();
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
			Statement statement = this.connect_db().createStatement();
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

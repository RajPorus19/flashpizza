package com.flashpizza.flashpizza.models;

import com.flashpizza.flashpizza.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyAPI {
    Database db = new Database();
    Company company;
    public CompanyAPI() throws SQLException {
        company = new Company(get_revenue(), get_best_customer(), get_average_order_price());
    }
    public String get_revenue() throws SQLException {
        ResultSet res = db.query_db("select * from revenue");
        if(res != null) {
            res.next();
            return res.getString("revenue");
        }
        return "0";
    }
    public String get_best_customer() throws SQLException {
        ResultSet res = db.query_db("select * from best_customer");
        if(res != null) {
            res.next();
            return res.getString("firstname");
        }
        return "N/A";
    }

    public String get_average_order_price() throws SQLException {
        ResultSet res = db.query_db("SELECT AVG(o.price) as avg FROM `order` o");
        if(res != null) {
            res.next();
            return res.getString("avg");
        }
        return "N/A";
    }
}

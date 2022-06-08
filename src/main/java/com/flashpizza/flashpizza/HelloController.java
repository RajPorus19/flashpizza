package com.flashpizza.flashpizza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flashpizza.flashpizza.models.UserAPI;

@Controller
public class HelloController {

    private static final String PATH = "/error";

    @GetMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    public String getErrorPath() {
        return PATH;
    }

    @GetMapping("/")
	public String index(Model model) throws SQLException {
    	UserAPI userAPI = new UserAPI();
    	ArrayList<String> users = userAPI.get_users();
    	model.addAttribute("users", users);
		return "users";
	}
    @GetMapping("/about")
	public String about() {
		return "about page";
	}
    @GetMapping("/test")
	public String test() {
		return "test page";
	}
}
package com.flashpizza.flashpizza;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    public String getErrorPath() {
        return PATH;
    }

    @GetMapping("/")
	public String index() {
    	Database.get_test_table();
		return "database is working ! page";
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
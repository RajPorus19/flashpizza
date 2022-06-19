package com.flashpizza.flashpizza;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flashpizza.flashpizza.models.Ingredient;
import com.flashpizza.flashpizza.models.IngredientAPI;
import com.flashpizza.flashpizza.models.Pizza;
import com.flashpizza.flashpizza.models.PizzaAPI;
import com.flashpizza.flashpizza.models.User;
import com.flashpizza.flashpizza.models.UserAPI;

@Controller
public class HelloController {


    @GetMapping("/error")
    public String error() {
        return "error";
    }

	@GetMapping("/")
	public String home(){
		return "home";
	}

	@GetMapping("/staff")
	public String staff(){
		return "staff";
	}
	

    @GetMapping("/users")
	public String users(Model model) throws SQLException {
    	UserAPI userAPI = new UserAPI();
    	ArrayList<User> users = userAPI.get_users();
    	model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("/deleteusers")
	public String delete_user(@RequestParam("usersChecked") ArrayList<String> usersChecked) throws SQLException {
		UserAPI userAPI = new UserAPI();
		for (String id : usersChecked) {
			userAPI.deleteUser(id);
		}
		
		return "deleteuser";
	}

	@GetMapping("/adduser")
	public String addUsers(Model model) throws SQLException {
    	model.addAttribute("user", new User());
		return "input_user";
	}
	@PostMapping("/adduser")
	public String displayUser(@ModelAttribute User user,Model model) throws SQLException {
    	model.addAttribute("user", user);
		UserAPI userAPI = new UserAPI();
		userAPI.addUser(user) ;
		return "display_user";
	}


	@GetMapping("/useredit/{id}")
	public String editUser(@PathVariable int id, Model model) throws SQLException{
		String strId = Integer.toString(id);
		UserAPI userAPI = new UserAPI();
		User currentUser = userAPI.getUser(strId);

		model.addAttribute("user",currentUser);
		return "edit_user";
	}
	@PostMapping("/useredited/{id}")
	public String saveUser(@PathVariable int id,@ModelAttribute User user,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	model.addAttribute("user", user);
		UserAPI userAPI = new UserAPI();
		user.setId(strId);
		userAPI.save(user);
		return "edited_user";
	}

    @GetMapping("/ingredients")
	public String ingredients(Model model) throws SQLException {
    	IngredientAPI ingredientAPI = new IngredientAPI();
    	ArrayList<Ingredient> ingredients = ingredientAPI.get_ingredients();
    	model.addAttribute("ingredients", ingredients);
		return "ingredients";
	}

	@GetMapping("/addingredient")
	public String addIngredient(Model model) throws SQLException {
    	model.addAttribute("ingredient", new Ingredient());
		return "input_ingredient";
	}

	@PostMapping("/addingredient")
	public String displayIngredient(@ModelAttribute Ingredient ingredient,Model model) throws SQLException {
    	model.addAttribute("ingredient", ingredient);
		IngredientAPI ingredientAPI = new IngredientAPI();
		ingredientAPI.addIngredient(ingredient) ;
		return "display_ingredient";
	}

	@PostMapping("/deleteingredients")
	public String delete_ingredient(@RequestParam("ingredientsChecked") ArrayList<String> ingredientsChecked) throws SQLException {
		IngredientAPI ingredientAPI = new IngredientAPI();
		for (String id : ingredientsChecked) {
			ingredientAPI.deleteIngredient(id);
		}
		
		return "deleteingredients";
	}

	// TODO
	@GetMapping("/ingredientedit/{id}")
	public String editIngredient(@PathVariable int id, Model model) throws SQLException{
		String strId = Integer.toString(id);
		IngredientAPI ingredientAPI = new IngredientAPI();
		Ingredient currentIngredient = ingredientAPI.getIngredient(strId);

		model.addAttribute("ingredient",currentIngredient);
		return "edit_ingredient";
	}
	@PostMapping("/ingredientedited/{id}")
	public String saveIngredient(@PathVariable int id,@ModelAttribute Ingredient ingredient,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	model.addAttribute("ingredient", ingredient);
		IngredientAPI ingredientAPI = new IngredientAPI();
		ingredient.setId(strId);
		ingredientAPI.save(ingredient);
		return "edited_ingredient";
	}

    @GetMapping("/pizzas")
	public String pizzas(Model model) throws SQLException {
		PizzaAPI pizzaAPI = new PizzaAPI();
    	ArrayList<Pizza> pizzas = pizzaAPI.get_pizzas();
    	model.addAttribute("pizzas", pizzas);
		return "pizzas";
	}
}
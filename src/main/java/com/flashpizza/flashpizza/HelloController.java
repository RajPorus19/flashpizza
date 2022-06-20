package com.flashpizza.flashpizza;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flashpizza.flashpizza.models.Ingredient;
import com.flashpizza.flashpizza.models.IngredientAPI;
import com.flashpizza.flashpizza.models.Messenger;
import com.flashpizza.flashpizza.models.MessengerAPI;
import com.flashpizza.flashpizza.models.Order;
import com.flashpizza.flashpizza.models.OrderAPI;
import com.flashpizza.flashpizza.models.OrderLine;
import com.flashpizza.flashpizza.models.Pizza;
import com.flashpizza.flashpizza.models.PizzaAPI;
import com.flashpizza.flashpizza.models.PizzaIngredient;
import com.flashpizza.flashpizza.models.PizzaIngredientAPI;
import com.flashpizza.flashpizza.models.PizzaSizeAPI;
import com.flashpizza.flashpizza.models.State;
import com.flashpizza.flashpizza.models.StateAPI;
import com.flashpizza.flashpizza.models.TypeVehicle;
import com.flashpizza.flashpizza.models.TypeVehicleAPI;
import com.flashpizza.flashpizza.models.User;
import com.flashpizza.flashpizza.models.UserAPI;
import com.flashpizza.flashpizza.models.Vehicle;
import com.flashpizza.flashpizza.models.VehicleAPI;

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
	public String staff(Model model) throws SQLException {
		UserAPI userAPI = new UserAPI();
		ArrayList<User> users = userAPI.get_users();
		model.addAttribute("users", users);
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
		ArrayList<Pizza> best_pizzas = pizzaAPI.get_best_pizza();
		model.addAttribute("best_pizzas", best_pizzas);
		ArrayList<Pizza> worst_pizzas = pizzaAPI.get_worst_pizza();
		model.addAttribute("worst_pizzas", worst_pizzas);
		return "pizzas";
	}

	@GetMapping("/addpizza")
	public String addPizza(Model model) throws SQLException {
    	model.addAttribute("pizza", new Pizza());
		return "input_pizza";
	}

	@PostMapping("/addpizza")
	public String displayPizza(@ModelAttribute Pizza pizza,Model model) throws SQLException {
    	model.addAttribute("pizza", pizza);
		PizzaAPI pizzaAPI = new PizzaAPI();
		pizzaAPI.addPizza(pizza) ;
		return "display_pizza";
	}

	@GetMapping("/pizzaedit/{id}")
	public String editPizza(@PathVariable int id, Model model) throws SQLException{
		String strId = Integer.toString(id);
		PizzaAPI pizzaAPI = new PizzaAPI();
		Pizza currentPizza = pizzaAPI.getPizza(strId);

		model.addAttribute("pizza",currentPizza);
		return "edit_pizza";
	}
	@PostMapping("/pizzaedited/{id}")
	public String savePizza(@PathVariable int id,@ModelAttribute Pizza pizza,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	model.addAttribute("pizza", pizza);
		PizzaAPI pizzaAPI = new PizzaAPI();
		pizza.setId(strId);
		pizzaAPI.save(pizza);
		return "edited_pizza";
	}


    @GetMapping("/pizzas/{id}/ingredients")
	public String pizzasIngredients(@PathVariable int id,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	IngredientAPI ingredientAPI = new IngredientAPI();
    	PizzaAPI pizzaAPI = new PizzaAPI();
		Pizza pizza = pizzaAPI.getPizza(strId);
    	ArrayList<Ingredient> ingredients = ingredientAPI.get_ingredients();
    	model.addAttribute("ingredients", ingredients);
    	model.addAttribute("pizza", pizza);
		return "pizzaIngredients";
	}
	@PostMapping("/updatepizzaingredient/{id}")
	public String delete_pizzaIngredients(@PathVariable int id,@RequestParam("ingredientsChecked") ArrayList<String> ingredientsChecked, Model model) throws SQLException {
		String strIdPizza = Integer.toString(id);
		PizzaIngredientAPI pizzaIngredientAPI = new PizzaIngredientAPI();
		for (String idIngredient : ingredientsChecked) {
			PizzaIngredient pizzaIngredient = new PizzaIngredient();
			pizzaIngredient.setId_ingredient(idIngredient);
			pizzaIngredient.setId_pizza(strIdPizza);
			pizzaIngredientAPI.addPizzaIngredient(pizzaIngredient);
		}
		ArrayList<PizzaIngredient> allPizzaIngredients = pizzaIngredientAPI.get_pizzaIngredients(strIdPizza);
		for (PizzaIngredient pizzaIngredient : allPizzaIngredients) {
			if(!ingredientsChecked.contains(pizzaIngredient.getId_ingredient())){
				pizzaIngredientAPI.deletepizzaIngredient(strIdPizza, pizzaIngredient.getId_ingredient());
			}
		}

		PizzaAPI pizzaAPI = new PizzaAPI();
    	ArrayList<Pizza> pizzas = pizzaAPI.get_pizzas();
    	model.addAttribute("pizzas", pizzas);
		return "pizzas";
	}

    @GetMapping("/messengers")
	public String messengers(Model model) throws SQLException {
		MessengerAPI messengerAPI = new MessengerAPI();
    	ArrayList<Messenger> messengers = messengerAPI.get_messengers();
    	model.addAttribute("messengers", messengers);
		return "messengers";
	}

	@GetMapping("/addmessenger")
	public String addMessenger(Model model) throws SQLException {
    	model.addAttribute("messenger", new Messenger());
		return "input_messenger";
	}

	@PostMapping("/addmessenger")
	public String displayMessenger(@ModelAttribute Messenger messenger,Model model) throws SQLException {
    	model.addAttribute("messenger", messenger);
		MessengerAPI messengerAPI = new MessengerAPI();
		messengerAPI.addMessenger(messenger);
		return "display_messenger";
	}

    @GetMapping("/vehicles")
	public String vehicles(Model model) throws SQLException {
		VehicleAPI vehicleAPI = new VehicleAPI();
    	ArrayList<Vehicle> vehicles = vehicleAPI.get_vehicles();
    	model.addAttribute("vehicles", vehicles);
		return "vehicles";
	}

	@GetMapping("/addvehicle")
	public String addVehicle(Model model) throws SQLException {
    	model.addAttribute("vehicle", new Vehicle());
		TypeVehicleAPI typeVehicleAPI = new TypeVehicleAPI();
		StateAPI stateAPI = new StateAPI();
    	ArrayList<TypeVehicle> typevehicles = typeVehicleAPI.get_typeVehicles();
    	ArrayList<State> states = stateAPI.get_states();
    	model.addAttribute("typevehicles", typevehicles);
    	model.addAttribute("states", states);
		return "input_vehicle";
	}

	@PostMapping("/addevehicle")
	public String displayVehicle(@ModelAttribute Vehicle vehicle,Model model,@RequestParam("typevehiclesChecked") ArrayList<String> typevehiclesChecked,@RequestParam("statesChecked") ArrayList<String> statesChecked) throws SQLException {
    	model.addAttribute("vehicle", vehicle);
		String stateId = "null";
		String typeId = "null";
		for (String string : statesChecked) {
			stateId = string;
		}
		for (String string : typevehiclesChecked) {
			typeId = string;
		}
		VehicleAPI vehicleAPI = new VehicleAPI();
		vehicle.setState_id(stateId);
		vehicle.setType_id(typeId);
		vehicleAPI.addVehicle(vehicle);
		return "display_vehicle";
	}


    @GetMapping("/typevehicles")
	public String typevehicles(Model model) throws SQLException {
		TypeVehicleAPI typeVehicleAPI = new TypeVehicleAPI();
    	ArrayList<TypeVehicle> typevehicles = typeVehicleAPI.get_typeVehicles();
    	model.addAttribute("typevehicles", typevehicles);
		return "typevehicles";
	}

	@GetMapping("/addtypevehicle")
	public String addTypeVehicle(Model model) throws SQLException {
    	model.addAttribute("typevehicle", new State());
		return "input_typevehicle";
	}

	@PostMapping("/addtypevehicle")
	public String displayTypeVehicle(@ModelAttribute TypeVehicle typeVehicle,Model model) throws SQLException {
    	model.addAttribute("typevehicle", typeVehicle);
		TypeVehicleAPI typeVehicleAPI = new TypeVehicleAPI();
		typeVehicleAPI.addVehicleType(typeVehicle) ;
		return "display_typevehicle";
	}

	@PostMapping("/deletetypevehicles")
	public String delete_typevehicle(@RequestParam("typevehiclesChecked") ArrayList<String> typevehiclesChecked) throws SQLException {
		TypeVehicleAPI typevehicleAPI = new TypeVehicleAPI();
		for (String id : typevehiclesChecked) {
			typevehicleAPI.deletetypevehicle(id);
		}
		
		return "deletetypevehicle";
	}

	@GetMapping("/typevehicle/{id}")
	public String edittypevehicle(@PathVariable int id, Model model) throws SQLException{
		String strId = Integer.toString(id);
		TypeVehicleAPI typevehicleAPI = new TypeVehicleAPI();
		TypeVehicle currentvehicletype = typevehicleAPI.getTypeVehicle(strId);

		model.addAttribute("typevehicle",currentvehicletype);
		return "edit_typevehicle";
	}

	@PostMapping("/typevehicle/{id}")
	public String saveTypeVehicle(@PathVariable int id,@ModelAttribute TypeVehicle typevehicle,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	model.addAttribute("typevehicle", typevehicle);
		TypeVehicleAPI typevehicleAPI = new TypeVehicleAPI();
		typevehicle.setId(strId);
		typevehicleAPI.save(typevehicle);
		return "edited_typevehicle";
	}

    @GetMapping("/states")
	public String state(Model model) throws SQLException {
		StateAPI stateAPI = new StateAPI();
    	ArrayList<State> states = stateAPI.get_states();
    	model.addAttribute("states", states);
		return "states";
	}

	@GetMapping("/addstate")
	public String addstate(Model model) throws SQLException {
    	model.addAttribute("state", new State());
		return "input_state";
	}

	@PostMapping("/addstate")
	public String displayState(@ModelAttribute State state,Model model) throws SQLException {
    	model.addAttribute("typevehicle", state);
		StateAPI stateAPI = new StateAPI();
		stateAPI.addState(state);
		return "display_state";
	}

	@GetMapping("/state/{id}")
	public String editstate(@PathVariable int id, Model model) throws SQLException{
		String strId = Integer.toString(id);
		StateAPI stateAPI = new StateAPI();
		State currentState = stateAPI.getState(strId);

		model.addAttribute("state",currentState);
		return "edit_state";
	}

	@PostMapping("/state/{id}")
	public String saveState(@PathVariable int id,@ModelAttribute State state,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	model.addAttribute("state", state);
		StateAPI stateAPI = new StateAPI();
		state.setId(strId);
		stateAPI.save(state);
		return "edited_state";
	}

	@PostMapping("/deletestate")
	public String delete_state(@RequestParam("statesChecked") ArrayList<String> stateChecked) throws SQLException {
		StateAPI stateAPI = new StateAPI();
		for (String id : stateChecked) {
			stateAPI.deleteState(id);
		}
		
		return "deletestate";
	}


    @GetMapping("/customers")
	public String customers(Model model) throws SQLException {
    	UserAPI userAPI = new UserAPI();
    	ArrayList<User> users = userAPI.get_users();
    	model.addAttribute("users", users);
		return "customers";
	}

	@PostMapping("/customer/{id}")
	public String addBalance(@PathVariable int id,@ModelAttribute User user,Model model) throws SQLException {
		//model.addAttribute("user", user);
		UserAPI userAPI = new UserAPI();
		userAPI.addBalance(user.getId(), user.getBalance());
		return "addbalance";
	}

    @GetMapping("/customer/{id}")
	public String customer(@PathVariable int id,Model model) throws SQLException {
		String customerId = Integer.toString(id);
		UserAPI userAPI = new UserAPI();
		User user = userAPI.getUser(Integer.toString(id));
		user.setId(customerId);
		model.addAttribute("user", user);
		return "customer";
	}

    @GetMapping("/customer/{id}/basket")
	public String basket(@PathVariable int id,Model model) throws SQLException {
		String strId = Integer.toString(id);
    	UserAPI userAPI = new UserAPI();
    	//ArrayList<OrderLine> orderlines = userAPI.getOrderLines(strId);
    	//model.addAttribute("orderlines", orderlines);
		return "basket";
	}

    @GetMapping("/customer/{user_id}/buypizza/{order_id}/order/{pizza_id}")
	public String orderPizza(@PathVariable int user_id, @PathVariable int pizza_id, @PathVariable int order_id, Model model) throws SQLException{
    	UserAPI userAPI = new UserAPI();
		String userId = Integer.toString(user_id);
		String orderId = Integer.toString(order_id);
		//String orderId = userAPI.get_current_order_id(userId);
		String pizzaId= Integer.toString(pizza_id);
		OrderLine orderline = new OrderLine();
		orderline.setPizza_id(pizzaId);
		//orderline.setOrder_id(orderId);
		PizzaSizeAPI pizzaSizeAPI = new PizzaSizeAPI();
		orderline.setSize_id("1");
    	model.addAttribute("orderline", orderline);
    	model.addAttribute("userId", userId);
    	model.addAttribute("orderId", orderId);
    	model.addAttribute("pizzaId", pizzaId);
    	model.addAttribute("sizes", pizzaSizeAPI.get_pizzasize_list());
		return "input_order_line";
	}

    @PostMapping("/customer/{user_id}/buypizza/{order_id}/order/{pizza_id}")
	public String PostorderPizza(@PathVariable int user_id, @PathVariable int pizza_id, @PathVariable int order_id, Model model, @ModelAttribute OrderLine orderline,@RequestParam("sizesChecked") ArrayList<String> sizesChecked) throws SQLException{
    	UserAPI userAPI = new UserAPI();
		String userId = Integer.toString(user_id);
		String orderId= Integer.toString(order_id);
		//String orderId = userAPI.get_current_order_id(userId);
		String pizzaId= Integer.toString(pizza_id);
		orderline.setPizza_id(pizzaId);
		String size = "1";
		for (String string : sizesChecked) {
			size=string;
		}
		orderline.setSize_id(size);
		userAPI.addOrderLine(userId, orderId, orderline);
		//orderline.setOrder_id(orderId);
    	model.addAttribute("orderline", orderline);
    	model.addAttribute("userId", userId);
    	model.addAttribute("pizzaId", pizzaId);
		return "display_orderline";
	}


    @GetMapping("/customer/{user_id}/buypizza/{order_id}")
	public String orderPizzas(@PathVariable int user_id, @PathVariable int order_id, Model model) throws SQLException{

		String userId = Integer.toString(user_id);
		String orderId = Integer.toString(order_id);

		PizzaAPI pizzaAPI = new PizzaAPI();
		ArrayList<Pizza> list_pizza = pizzaAPI.get_pizzas();

    	model.addAttribute("orderId", orderId);
    	model.addAttribute("userId", userId);
    	model.addAttribute("pizzas", list_pizza);
		return "buy_pizzas";
	}
    @PostMapping("/customer/{user_id}/createorder")
	public String createOrder(@PathVariable int user_id, Model model) throws SQLException{
		String userId = Integer.toString(user_id);
    	UserAPI userAPI = new UserAPI();
		userAPI.createOrder(userId);
		return "created_order";
	}

    @GetMapping("/customer/{user_id}/vieworder")
	public String viewOrder(@PathVariable int user_id, Model model) throws SQLException{
		String userId = Integer.toString(user_id);
    	UserAPI userAPI = new UserAPI();
		ArrayList<Order> order_list = userAPI.getOrders(userId);
		OrderAPI orderAPI = new OrderAPI();
		model.addAttribute("orders", order_list);
		model.addAttribute("userId", userId);
		model.addAttribute("orderAPI", orderAPI);
		return "list_orders";
	}
}
/**
 * Main class of the myFoodora application. It stores the data of every registered user,
 * of the current policy for delivery, the fees inherent to the system (serviceFee, markupPercentage,
 * and deliveryCost) and provides several methods to perform the main operations of the application.
 * It allows a customer to place an order to a restaurant, and finds a courier to deliver it according to
 * the chosen delivery policy. It also allows modification of restaurant menus, stores the past orders,
 * (a manager can also see statistics of the application and its users) and notifies customers who
 * agreed to receive notifications.
 * Finally, it allows login to the system and takes into account who is logged on.
 * A user with username ceo and password 123456789 is created by default.
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.System;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.cs.group15.myFoodora.exception.WrongUserException;
import fr.cs.group15.myFoodora.policies.DeliveryPolicy;
import fr.cs.group15.myFoodora.policies.FairOccupationPolicy;
import fr.cs.group15.myFoodora.policies.FastestDelivery;
import fr.cs.group15.myFoodora.restaurantComponents.Dessert;
import fr.cs.group15.myFoodora.restaurantComponents.Dish;
import fr.cs.group15.myFoodora.restaurantComponents.FullMeal;
import fr.cs.group15.myFoodora.restaurantComponents.HalfMeal;
import fr.cs.group15.myFoodora.restaurantComponents.MainDish;
import fr.cs.group15.myFoodora.restaurantComponents.Meal;
import fr.cs.group15.myFoodora.restaurantComponents.Menu;
import fr.cs.group15.myFoodora.restaurantComponents.Starter;
import fr.cs.group15.myFoodora.users.*;


public class Core implements Observable {
	private List<Restaurant> registeredRestaurants;
	private List<Customer> registeredCustomers;
	private ArrayList<Courier> registeredCouriers;
	private List<Manager> registeredManagers;

	private HashMap<String, User> users = new HashMap<String, User>();

	private List<Order> ordersToComplete;
	private List<Order> completedOrders;

	private double serviceFee;
	private double markupPercentage;
	private double deliveryCost;

	private DeliveryPolicy dpolicy;

	private User current_user;
	protected Order current_order;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * Initializes a core system. It is supposed to be called only once by the CLUI.
	 */
	public Core() {

		this.registeredRestaurants = new ArrayList<Restaurant>();
		this.registeredCouriers = new ArrayList<Courier>();
		this.registeredCustomers = new ArrayList<Customer>();
		this.registeredManagers = new ArrayList<Manager>();

		Manager ceo = new Manager("mathias", "thirion", "ceo", "123456789");
		users.put("ceo", ceo);
		registeredManagers.add(ceo);

		this.ordersToComplete = new ArrayList<Order>();
		this.completedOrders = new ArrayList<Order>();
		
		this.dpolicy = new FastestDelivery();
	}

	/*
	 * Method used in every other method to warn the user that he cannot execute this command
	 */
	public static void unauthorizedCommand() {
		throw new WrongUserException("You are not allowed to execute this command");
	}

	/**
	 * This method allows for a manager logged in to register new Users
	 * 
	 * @param user the user to add to the system
	 */
	public void registerUser(User user) {
		if (current_user instanceof Manager) {
			if (user instanceof Customer) {
				Customer user_customer = (Customer) user;
				registeredCustomers.add(user_customer);
			} else if (user instanceof Courier) {
				Courier user_courier = (Courier) user;
				registeredCouriers.add(user_courier);
			} else if (user instanceof Restaurant) {
				Restaurant user_restaurant = (Restaurant) user;
				registeredRestaurants.add(user_restaurant);
			} else if (user instanceof Manager) {
				Manager user_manager = (Manager) user;
				registeredManagers.add(user_manager);
			}
			users.put(user.getUsername(), user);
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * Method for a customer to register by himself without the help of a Manager
	 * 
	 * @param user user (customer) wanting to register
	 */
	public void register(User user) {
		if (current_user == null) {
			if (user instanceof Customer) {
				Customer user_customer = (Customer) user;
				registeredCustomers.add(user_customer);
				users.put(user.getUsername(), user);
				logIn(user_customer.getUsername(), user_customer.getPassword());
			} else {
				unauthorizedCommand();
			}
		}
	}
	
	/**
	 * Sets the number of a courier or a customer
	 * @param num the phone number
	 */
	public void setPhone(String num) {
		if ((current_user instanceof Customer)) {
			((Customer) current_user).setPhone(num);
		} else if ((current_user instanceof Courier)) {
			((Courier) current_user).setPhone(num);
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * Sets the mail address of a customer
	 * @param mail
	 */
	public void setEmail(String mail) {
		if ((current_user instanceof Customer)) {
			((Customer) current_user).setEmail(mail);
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Sets the preferences about notifications of a customer
	 * @param notif a boolean telling if they want to be notified or not
	 */
	public void setPreference(boolean notif) {
		if ((current_user instanceof Customer)) {
			((Customer) current_user).setWantNotif(notif);
			registerObserver((Observer) current_user);
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * Method to remove a User from the system
	 * 
	 * @param user user to remove
	 */
	public void removeUser(User user) {
		if (current_user instanceof Manager) {
			if (users.containsKey(user.getUsername())) {
				users.remove(user.getUsername());
				if (user instanceof Courier) {
					Courier user_courier = (Courier) user;
					this.registeredCouriers.remove(user_courier);
				} else if (user instanceof Customer) {
					Customer user_customer = (Customer) user;
					removeObserver(user_customer);
					this.registeredCustomers.remove(user_customer);
				} else if (user instanceof Restaurant) {
					Restaurant user_restaurant = (Restaurant) user;
					this.registeredRestaurants.remove(user_restaurant);
				} else if (user instanceof Manager) {
					Manager user_manager = (Manager) user;
					this.registeredManagers.remove(user_manager);
				}
			}
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * Method to log in the system not matter which type of user wants to
	 * 
	 * @param username username of the user wanting to log in
	 * @param password password of the user wanting to log in
	 * @return text to confirm the success of the operation
	 */
	public void logIn(String username, String password) {
		if (current_user == null) {
			if (users.containsKey(username)) {
				if (users.get(username).getPassword().equals(password)) {
					current_user = users.get(username);
					if (current_user instanceof Customer) {
						System.out.println("Logged in as a Customer (" + current_user.getName() + ")");
						Customer cust = (Customer) current_user;
						for (Message m : cust.getInBox()) {
							System.out.println("You have a message :" + m.getContent() + " (sent at " + m.getDate() + ")");
						}
						System.out.println("Available restaurants :");
						for (Restaurant rest : registeredRestaurants) {
							System.out.println(rest.getName());
						}
					} else if (current_user instanceof Courier) {
						System.out.println("Logged in as a Courier (" + current_user.getName() + ") located at " + ((Courier) current_user).getPosition());
						Courier courier = (Courier) current_user;
						if (courier.getReceivedOrders().size()>0) {
							System.out.println("You received these orders :");
							for(Order order : courier.getReceivedOrders()) {
								System.out.println("Order " + order.getID() + " at "+ order.getRestaurant() + " for " + order.getCustomer());
							}
						}
						
					} else if (current_user instanceof Restaurant) {
						System.out.println("Logged in as a Restaurant (" + current_user.getName() + ")");
						showMenuItem(current_user.getName());
					} else if (current_user instanceof Manager) {
						System.out.println("Logged in as a Manager (" + current_user.getName() + ")");
					} 
				} else {
					throw new IllegalArgumentException("Wrong password");
				}
			} else {
				throw new IllegalArgumentException("Username does not exist");
			}
		} else {
			throw new IllegalAccessError("Another user is already logged-in");
		}
	}

	/**
	 * Method to log out the system
	 * 
	 * @return username of the user logging out
	 */
	public String logOut() {
		if (current_user !=null) {
			String result = current_user.getUsername();
			current_user = null;
			current_order = null;
			return result +  " successfully logged out.";
		} else {
			return "No one is logged in";
		}
	}
	
	/**
	 * Adds a dish to the currently logged in restaurant menu.
	 * @param dishName
	 * @param dishCategory
	 * @param diet
	 * @param glutenFree
	 * @param price
	 */
	public String addDishRestaurantMenu(String dishName, String dishCategory, String diet, boolean glutenFree, double price) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).addDishRestaurantMenu(dishName, price, diet, dishCategory, glutenFree);
			return "Successfully added " + dishName + " to " + current_user.getName() + " menu.";
		} else {
			throw new WrongUserException("Log in as a restaurant to add a dish to the menu.");
		}
	}
	
	/**
	 * Adds a dish to the currently logged in restaurant menu. Defaults the diet to standard and not gluten free.
	 * @param dishName
	 * @param dishCategory
	 * @param price
	 */
	public String addDishRestaurantMenu(String dishName, String dishCategory, double price) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).addDishRestaurantMenu(dishName, price, dishCategory);
			return "Successfully added " + dishName + " to " + current_user.getName() + " menu.";
		} else {
			throw new WrongUserException("Log in as a restaurant to add a dish to the menu.");
		}
	}
	
	/**
	 * Removes a dish from the currently logged in restaurant menu.
	 * @param dishName
	 */
	public String removeDishRestaurantMenu(String dishName) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).removeDish(dishName);
			return "Successfully removed " + dishName + " from " + current_user.getName() + " menu.";
		} else {
			throw new WrongUserException("Log in as a restaurant to add a dish to the menu.");
		}
	}
	
	/**
	 * Creates a meal and adds it to the logged in restaurant menu
	 * @param mealName the name of the meal
	 * @param mealType the type of meal to create (fullmeal or halfmeal)
	 */
	public String createMeal(String mealName, String mealType) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).createMeal(mealName, mealType);
			return "Successfully added " + mealName + " to " + current_user.getName() + " menu.";
		} else {
			throw new WrongUserException("Log in as a restaurant to add a meal to the menu.");
		}
	}
	
	/**
	 * Adds a dish to a meal.
	 * @param dishName the name of the dish
	 * @param mealName the name of the meal
	 */
	public String addDishToMeal(String dishName, String mealName) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).addDishToMeal(dishName, mealName);
			return "Successfully added " + dishName + " to " + mealName + ".";
		} else {
			throw new WrongUserException("Log in as a restaurant to add a dish to a meal.");
		}
	}
	
	/**
	 * Displays the content of the given meal
	 * @param mealName the name of the meal
	 */
	public void showMeal(String mealName) {
		if (current_user instanceof Restaurant) {
			Meal meal = ((Restaurant) current_user).getRestaurantMenu().getMealByName(mealName);
			System.out.println("The meal " + meal.getName() + " contains :");
			for (Dish dish : meal.getMealContent()) {
				System.out.println(dish);
			}
		} else {
			throw new WrongUserException("Log in as a restaurant to add a dish to a meal.");
		}
	}
	
	/**
	 * Removes a dish from a meal.
	 * @param dishName the name of the dish
	 * @param mealName the name of the meal
	 */
	public String removeDishFromMeal(String dishName, String mealName) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).removeDishFromMeal(dishName, mealName);
			return "Successfully removed " + dishName + " to " + mealName + ".";
		} else {
			throw new WrongUserException("Log in as a restaurant to add a dish to a meal.");
		}
	}
	
	/**
	 * Deletes a meal from the logged in restaurant menu
	 * @param mealName the name of the meal
	 */
	public String deleteMeal(String mealName) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).deleteMeal(mealName);
			return "Successfully deleted " + mealName;
		} else {
			throw new WrongUserException("Log in as a restaurant to add a meal to the menu.");
		}
	}

	/**
	 * Method to change the delivery policy to fastest
	 */
	public void setDeliveryPolicyToFastest() {
		if (current_user instanceof Manager) {
			this.dpolicy = new FastestDelivery();
			System.out.println("Delivery Policy set to fastest !");
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * Method to change the delivery policy to fair occupation
	 */
	public void setDeliveryPolicyToFairOccupation() {
		if (current_user instanceof Manager) {
			this.dpolicy = new FairOccupationPolicy();
			System.out.println("Delivery Policy set to fair-occupation");
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * Method for a Courier to change his state (on/off Duty)
	 * 
	 * @param onDuty boolean to tell the system if the courier is off (false) or on (true) duty
	 */
	public void changeState(boolean onDuty) {
		if (current_user instanceof Courier) {
			Courier user_courier = (Courier) current_user;
			user_courier.setOnDuty(onDuty);
			if (!onDuty) {
				ordersToComplete.addAll(user_courier.getReceivedOrders());
				user_courier.getReceivedOrders().clear();
			}
			treatOrders();
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Returns the restaurant corresponding to the string name given, and null if the restaurant doesn't exist
	 * @param restaurantName the name of the restaurant we are looking for
	 * @return the Restaurant object corresponding to the given name
	 */
	public Restaurant getRestaurantByName(String restaurantName) {
		for (Restaurant r : registeredRestaurants) {
			if (r.getName().equalsIgnoreCase(restaurantName)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Method to create an order for the logged in client.
	 * @param restaurantName the name of the restaurant where the order is created
	 * @return an order object for the restaurant given and the client logged in
	 */
	public Order createOrder(String restaurantName) {
		if (!(current_user instanceof Customer)) {
			throw new IllegalArgumentException("Wrong user type. Please log in as a customer to create an order.");
		}
		Restaurant restaurant = this.getRestaurantByName(restaurantName);
		if (restaurant == null) {
			throw new IllegalArgumentException("This restaurant doesn't exist. Try another name.");
		}
		return new Order(restaurant, (Customer) current_user);
	}
	
	/**
	 * Adds an item to the current order of the user logged in.
	 * @param itemName the string name of the item to add (whether a meal or a dish)
	 * @return a string explanation of what was added
	 */
	public String addItem2Order(String itemName) {
		if (!(current_user instanceof Customer)) {
			throw new IllegalArgumentException("You must register as a customer.");
		}
		if (current_order == null) {
			throw new IllegalArgumentException("You must create an order first.");
		}
		Menu restaurantMenu = current_order.getRestaurant().getRestaurantMenu();
		Meal m = restaurantMenu.getMealByName(itemName);
		if (!(m == null)) {
			current_order.addMeal(m);
			return "Successfully added " + m.getName() + " to your order.";
		} else {
			Dish d = restaurantMenu.getDishByName(itemName);
			if (!(d == null)) {
				current_order.addDish(d);
				return "Successfully added " + d.getName() + " to your order.";
			}
		}
		throw new IllegalArgumentException("This item is not in the menu.");
	}
	
	/**
	 * Ends the current order of the logged in customer and allows the system to treat it and allow it to a courier.
	 */
	public void endOrder() {
		if (current_user instanceof Customer) {
			double finalPrice = current_order.getPrice();
			current_order.setOrderPrice(finalPrice + this.serviceFee);
			current_order.setOrderProfit(finalPrice*this.markupPercentage + this.serviceFee - this.deliveryCost);
			int points = (int) Math.round(finalPrice/10);
			current_order.getCustomer().addPointsCustomer(points);
			
			System.out.println("You ordered :");
			for(Dish d : current_order.getDishes()) {
				System.out.println(d.getName());
			}
			for (Meal m : current_order.getMeals()) {
				System.out.println(m.getName());
			}
			
			System.out.println("You must pay " + current_order.getOrderPrice() + "€.");

			current_order.getRestaurant().addOrder(current_order);
			ordersToComplete.add(current_order);
			current_order = null;
			treatOrders();
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Treats all the orders received by the system, and give them to a courier if some are available. Is called when an order is sent to the system, or when a courier accepts or refuses orders.
	 */
	public void treatOrders() {
		while (ordersToComplete.size() > 0) {
			Order order = this.ordersToComplete.remove(0);
			
			ArrayList<Courier> listCouriers = this.dpolicy.applyDeliveryPolicy(this.registeredCouriers, order.getRestaurant().getLocation());
			
			Courier courier = listCouriers.get(0);
			courier.addOrder(order);
			
			if (listCouriers.isEmpty()) {
				System.out.println("No courier is available. Try again later");
				return;
			}
		}
	}
	
	/**
	 * Takes an order from the list of orders of the logged in restaurant and returns the courier allocated by the delivery policy (used only for tests)
	 * @param ID the id of the order to allocate
	 * @return the courier allocated
	 */
	public Courier findDeliverer(int ID) {
		if (current_user instanceof Restaurant) {
			for (Order order : ((Restaurant) current_user).getDeliveredOrders()) {
				if(order.getID() == ID && order.getRestaurant().getName().equals(current_user.getName())) {
					ArrayList<Courier> listCouriers = this.dpolicy.applyDeliveryPolicy(this.registeredCouriers, ((Restaurant) current_user).getLocation());
					return listCouriers.get(0);
				}
			}
			throw new IllegalArgumentException("This order doesn't exist or was not created at your restaurant");
		} else {
			throw new WrongUserException("Log in as a restaurant.");
		}
	}
	
	/**
	 * Method for the logged in courier to accept a chosen order with its ID (shown when the courier logs in)
	 * @param orderID the Id of the order the courier takes
	 */
	public void acceptOrder(int orderID) {
		if (current_user instanceof Courier) {
			ArrayList<Order> refusedOrders =  ((Courier) current_user).acceptOrder(orderID);
			this.ordersToComplete.addAll(refusedOrders);
			treatOrders();
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * The logged in courier can tell the system he delivered the order he accepted. It updates its stats and the system.
	 */
	public void deliveredOrder() {
		if (current_user instanceof Courier) {
			Order last = ((Courier) current_user).getCurrent_order();
			if (last != null) {
				completedOrders.add(last);
				last.getCustomer().addOrder(last);
				((Courier) current_user).deliveredOrder();
			} else {
				throw new IllegalAccessError("You don't have any order to deliver.");
			}
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Allows for a manager to change the fidelity plan of a customer
	 * 
	 * @param username username of the customer
	 * @param cardType card type to associate with the customer
	 */
	public void associateCard(String username, String cardType) {
		if (current_user instanceof Manager) {
			if (users.containsKey(username)) {
				Customer cust = (Customer) users.get(username);
				if (cardType.equalsIgnoreCase("Basic")) {
					cust.setFidCardPlanBasic();
				} else if (cardType.equalsIgnoreCase("Points")) {
					cust.setFidCardPlanPoints();
				} else if (cardType.equalsIgnoreCase("Lottery")) {
					cust.setFidCardPlanLottery();
				} else {
					throw new IllegalArgumentException("This card type does not exist. Please enter either : Basic, Points or Lottery.");
				}
				System.out.println("Customer " + username+ "'s fidelity card set to " + cardType);
			} else {
				throw new IllegalArgumentException("This username does not exist.");
			}
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Prints the list of couriers sorted by most deliveries for a Manager
	 */
	public void showCourierDeliveries() {
		if (current_user instanceof Manager) {
			if (registeredCouriers.isEmpty()) {
				throw new IllegalStateException("There are currently no couriers registered. Please try again later");
			}
			
			ArrayList<Courier> temp_list = new ArrayList<>(registeredCouriers);
			Collections.sort(temp_list, new Comparator<Courier>() {
				@Override
				public int compare(Courier c1, Courier c2) {
					return Integer.compare(c1.getCounterOrder(), c2.getCounterOrder());
				}
			});
			
			System.out.println("Here are the couriers sorted by delivery count :");
			for (Courier courier : temp_list) {
				System.out.println(courier.getName() + " " + courier.getSurname() + " (" + courier.getUsername()+ ") nb of delivery : " + courier.getCounterOrder());
			}
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Prints the list of restaurants sorted by number of deliveries for a Manager
	 */
	public void showRestaurantTop() {
		if (current_user instanceof Manager) {
			if (registeredRestaurants.isEmpty()) {
				throw new IllegalStateException("There are currently no restaurants registered. Please try again later");
			}
			
			ArrayList<Restaurant> temp_list = new ArrayList<>(registeredRestaurants);
			Collections.sort(temp_list, new Comparator<Restaurant>() {
				@Override
				public int compare(Restaurant r1, Restaurant r2) {
					return Integer.compare(r1.getDeliveredOrders().size(), r2.getDeliveredOrders().size());
				}
			});
			
			System.out.println("Here are the restaurants sorted by number of orders delivered ("+ registeredRestaurants.size() +") :");
			for (Restaurant rest : temp_list) {
				System.out.println(rest.getName() + " (" + rest.getUsername() + ") nb of delivery : " + rest.getDeliveredOrders().size());
			}
			
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Prints the list of registered customer for the logged in manager
	 */
	public void showCustomers() {
		if (current_user instanceof Manager) {
			if (registeredCustomers.isEmpty()) {
				throw new IllegalStateException("There are currently no customers registered. Please try again later");
			}
			
			System.out.println("Here is the list of all customers (" + registeredCustomers.size() + ")");
			for (Customer cust : registeredCustomers) {
				System.out.println(cust.getName() + " " + cust.getSurname() + " (" + cust.getUsername() + ")");
			}
		}
	}
	
	/**
	 * Displays to a customer, a manager or a restaurant the menu of a restaurant
	 * @param restaurantName the name of the restaurant
	 */
	public void showMenuItem(String restaurantName) {
		if (!(current_user instanceof Courier)) {
			Restaurant restaurant = getRestaurantByName(restaurantName);
			if (restaurant == null) {
				throw new IllegalArgumentException("This restaurant doesn't exist. Try another name.");
			}
			Menu menu = restaurant.getRestaurantMenu();
			System.out.println(restaurantName + " menu contains :");
			
			// Displays all dishes if they exist
			if (menu.getAllDishes().size() > 0) {
				if (menu.getStarterMenu().size() > 0) {
					System.out.println("Starters :");
					for (Starter starter : menu.getStarterMenu()) {
						System.out.println(starter);
					}
				}
				if (menu.getMainMenu().size() > 0) {
					System.out.println("Main dishes :");
					for (MainDish main : menu.getMainMenu()) {
						System.out.println(main);
					}
				}
				if (menu.getDessertMenu().size() > 0) {
					System.out.println("Desserts :");
					for (Dessert dessert : menu.getDessertMenu()) {
						System.out.println(dessert);
					}
				}
			}
			
			// Display all meals if they exist
			if (menu.getAllMeals().size() > 0) {
				if (menu.getListOfFullMeals().size() > 0) {
					System.out.println("Full meals :");
					for (FullMeal fullmeal : menu.getListOfFullMeals()) {
						System.out.println(fullmeal);
					}
				}
				if (menu.getListOfHalfMeals().size() > 0) {
					System.out.println("Half meals :");
					for (HalfMeal halfmeal : menu.getListOfHalfMeals()) {
						System.out.println(halfmeal);
					}
				}
			}
			
			// Displays meals of the week
			if (menu.getMealOftheWeek().size() > 0) {
				System.out.println("Meals of the week :");
				for (Meal m : menu.getMealOftheWeek()) {
					System.out.println(m.getName());
				}
			}
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Prints the total profit of the system for a Manager
	 */
	public void showTotalProfit() {
		if (current_user instanceof Manager) {
			if (completedOrders.isEmpty()) {
				throw new IllegalStateException("No order has been completed thus no profit has been made. Try again later.");
			}
			
			double total = 0;
			for (Order order : completedOrders) {
				total += order.getOrderProfit();
			}
			System.out.println("Total profit : " + total + "€");
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Prints the total profit between 2 dates using the dd-MM-yyyy for a Manager
	 * 
	 * @param startDateStr starting date
	 * @param endDateStr ending date
	 */
	public void showTotalProfit(String startDateStr, String endDateStr) {
		if (current_user instanceof Manager) {
			if (completedOrders.isEmpty()) {
				throw new IllegalStateException("No order has been completed thus no profit has been made. Try again later.");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar startDate = Calendar.getInstance();
	        Calendar endDate = Calendar.getInstance();
	        
	        try {
	            Date start = sdf.parse(startDateStr);
	            Date end = sdf.parse(endDateStr);
	            startDate.setTime(start);
	            endDate.setTime(end);
	        } catch (ParseException e) {
	            throw new IllegalArgumentException("Invalid date format, please enter the date using the dd-MM-yyyy format");
	        }
	        
	        double total = 0;
	        for (Order order : completedOrders) {
	        	Calendar orderDate = order.getDate();
	        	
	        	if ((orderDate.equals(startDate) || orderDate.after(startDate)) && 
	                    (orderDate.equals(endDate) || orderDate.before(endDate))) {
	        		
	                    total += order.getOrderProfit();
	                }
	        }
	        
	        System.out.println("Total profit from " + startDateStr + " to " + endDateStr + " is : " + total);
		} else {
			unauthorizedCommand();
		}
	}
	
	/**
	 * Getters and setters
	 */

	/**
	 * @return the serviceFee
	 */
	public double getServiceFee() {
		return serviceFee;
	}
	

	/**
	 * @param serviceFee the serviceFee to set
	 */
	public void setServiceFee(double serviceFee) {
		if (current_user instanceof Manager) {
			this.serviceFee = serviceFee;			
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * @return the markupPercentage
	 */
	public double getMarkupPercentage() {
		return markupPercentage;
	}

	/**
	 * @param markupPercentage the markupPercentage to set
	 */
	public void setMarkupPercentage(double markupPercentage) {
		if (current_user instanceof Manager) {
			this.markupPercentage = markupPercentage;			
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * @return the deliveryCost
	 */
	public double getDeliveryCost() {
		return deliveryCost;
	}

	/**
	 * @param deliveryCost the deliveryCost to set
	 */
	public void setDeliveryCost(double deliveryCost) {
		if (current_user instanceof Manager) {
			this.deliveryCost = deliveryCost;			
		} else {
			unauthorizedCommand();
		}
	}

	/**
	 * @return the registeredRestaurants
	 */
	public List<Restaurant> getRegisteredRestaurants() {
		return registeredRestaurants;
	}

	/**
	 * @return the registeredCustomers
	 */
	public List<Customer> getRegisteredCustomers() {
		return registeredCustomers;
	}

	/**
	 * @return the registeredCourier
	 */
	public List<Courier> getRegisteredCourier() {
		return registeredCouriers;
	}

	/**
	 * @return the registeredManager
	 */
	public List<Manager> getRegisteredManager() {
		return registeredManagers;
	}

	/**
	 * @return the current_order
	 */
	public Order getCurrent_order() {
		return current_order;
	}

	/**
	 * @param current_order the current_order to set
	 */
	public void setCurrent_order(Order current_order) {
		this.current_order = current_order;
	}
	

	/**
	 * @return the current_user
	 */
	public User getCurrent_user() {
		return current_user;
	}

	/**
	 * @param current_user the current_user to set
	 */
	public void setCurrent_user(User current_user) {
		this.current_user = current_user;
	}

	/**
	 * @return the registeredCouriers
	 */
	public ArrayList<Courier> getRegisteredCouriers() {
		return registeredCouriers;
	}

	/**
	 * @param registeredCouriers the registeredCouriers to set
	 */
	public void setRegisteredCouriers(ArrayList<Courier> registeredCouriers) {
		this.registeredCouriers = registeredCouriers;
	}

	/**
	 * @return the registeredManagers
	 */
	public List<Manager> getRegisteredManagers() {
		return registeredManagers;
	}

	/**
	 * @param registeredManagers the registeredManagers to set
	 */
	public void setRegisteredManagers(List<Manager> registeredManagers) {
		this.registeredManagers = registeredManagers;
	}

	/**
	 * @return the users
	 */
	public HashMap<String, User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	/**
	 * @return the ordersToComplete
	 */
	public List<Order> getOrdersToComplete() {
		return ordersToComplete;
	}

	/**
	 * @param ordersToComplete the ordersToComplete to set
	 */
	public void setOrdersToComplete(List<Order> ordersToComplete) {
		this.ordersToComplete = ordersToComplete;
	}

	/**
	 * @return the completedOrders
	 */
	public List<Order> getCompletedOrders() {
		return completedOrders;
	}

	/**
	 * @param completedOrders the completedOrders to set
	 */
	public void setCompletedOrders(List<Order> completedOrders) {
		this.completedOrders = completedOrders;
	}

	/**
	 * @return the dpolicy
	 */
	public DeliveryPolicy getDpolicy() {
		return dpolicy;
	}

	/**
	 * @param dpolicy the dpolicy to set
	 */
	public void setDpolicy(DeliveryPolicy dpolicy) {
		this.dpolicy = dpolicy;
	}

	/**
	 * @return the observers
	 */
	public ArrayList<Observer> getObservers() {
		return observers;
	}

	/**
	 * @param observers the observers to set
	 */
	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}

	/**
	 * @param registeredRestaurants the registeredRestaurants to set
	 */
	public void setRegisteredRestaurants(List<Restaurant> registeredRestaurants) {
		this.registeredRestaurants = registeredRestaurants;
	}

	/**
	 * @param registeredCustomers the registeredCustomers to set
	 */
	public void setRegisteredCustomers(List<Customer> registeredCustomers) {
		this.registeredCustomers = registeredCustomers;
	}
	
	/**
	 * Adds a customer to the list of registered observers (who want to receive notifications)
	 */
	@Override
	public String registerObserver(Observer observer) {
		if (!(observer instanceof Customer && current_user instanceof Customer)) {
			throw new WrongUserException("Log in as a customer to change preferences.");
		}
		observers.add(observer);
		((Customer) observer).setWantNotif(true);
		return "Successfully registered to be notified of special offers";
	}
	
	/**
	 * Removes a customer from the list of registered observers (who want to receive notifications)
	 */
	@Override
	public String removeObserver(Observer observer) {
		if (!(observer instanceof Customer && current_user instanceof Customer)) {
			throw new WrongUserException("Log in as a customer to change preferences.");
		}
		observers.remove(observer);
		((Customer) observer).setWantNotif(false);
		return "Successfully unregistered to be notified of special offers";
	}
	
	/**
	 * Sets a special meal for the restaurant logged in, and notifies registered customers
	 * @param mealName
	 */
	public void setSpecialOffer(String mealName) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).setSpecialOffer(mealName);
			Meal m = ((Restaurant) current_user).getRestaurantMenu().getMealByName(mealName);
			notifyObservers(m);
		} else {
			throw new WrongUserException("Log in as a restaurant to set a special offer.");
		}
	}

	/**
	 * Removes a meal from the special meal offer of the logged on restaurant
	 * @param mealName
	 */
	public void removeSpecialOffer(String mealName) {
		if (current_user instanceof Restaurant) {
			((Restaurant) current_user).removeFromSpecialOffer(mealName);
		} else {
			throw new WrongUserException("Log in as a restaurant to remove a special offer.");
		}
	}
	
	/**
	 * Notifies the customers who agreed to receive notifications
	 */
	@Override
	public String notifyObservers(Meal meal) {
			
			for (Observer ob : observers) {
				ob.update(((Restaurant) current_user), meal);
			}
			return "All registered customers have been notified";
	}


}

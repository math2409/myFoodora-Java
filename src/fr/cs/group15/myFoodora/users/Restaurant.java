/**
 * A subclass of User representing the Restaurant object. It contains the methods used in the CLUI
 * to manage the restaurant (add/remove Meal/Dish, set special offer, ...)
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.users;

import java.util.ArrayList;
import java.util.List;

import fr.cs.group15.myFoodora.System.Order;
import fr.cs.group15.myFoodora.restaurantComponents.*;

public class Restaurant extends User{
	private Address location;
	private double genericDiscountFactor;
	private double specialDiscountFactor;
	private Menu restaurantMenu;
	private List<Order> deliveredOrders;

	private AbstractFactory dishFactory = FactoryProducer.getFactory("dish");
	private AbstractFactory mealFactory = FactoryProducer.getFactory("meal");

	/**
	 * @param name of the restaurant
	 * @param username of the restaurant
	 * @param password of the restaurant
	 * @param location of the restaurant
	 */
	public Restaurant(String name, String username, String password, Address location) {
		super(name, username, password);
		this.location = location;
		this.genericDiscountFactor = 0.05;
		this.specialDiscountFactor = 0.10;
		this.restaurantMenu = new Menu();
		this.deliveredOrders = new ArrayList<Order>();
	}

	/**
	 * @return the location
	 */
	public Address getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Address location) {
		this.location = location;
	}

	/**
	 * @return the genericDiscountFactor
	 */
	public double getGenericDiscountFactor() {
		return genericDiscountFactor;
	}

	/**
	 * @param genericDiscountFactor the genericDiscountFactor to set
	 */
	public void setGenericDiscountFactor(double genericDiscountFactor) {
		this.genericDiscountFactor = genericDiscountFactor;
	}

	/**
	 * @return the specialDiscountFactor
	 */
	public double getSpecialDiscountFactor() {
		return specialDiscountFactor;
	}

	/**
	 * @param specialDiscountFactor the specialDiscountFactor to set
	 */
	public void setSpecialDiscountFactor(double specialDiscountFactor) {
		this.specialDiscountFactor = specialDiscountFactor;
	}

	/**
	 * @return the restaurantMenu
	 */
	public Menu getRestaurantMenu() {
		return restaurantMenu;
	}
	
	/**
	 * @param restaurantMenu the restaurantMenu to set
	 */
	public void setRestaurantMenu(Menu restaurantMenu) {
		this.restaurantMenu = restaurantMenu;
	}
	
	
	/**
	 * @return the deliveredOrders
	 */
	public List<Order> getDeliveredOrders() {
		return deliveredOrders;
	}
	
	/**
	 * @param deliveredOrders the deliveredOrders to set
	 */
	public void setDeliveredOrders(List<Order> deliveredOrders) {
		this.deliveredOrders = deliveredOrders;
	}
	
	/**
	 * Adds an order the restaurant delivered to the list
	 * @param order
	 */
	public void addOrder(Order order) {
		this.deliveredOrders.add(order);
	}
	

	/**
	 * Set of functions to handle adding or removing dishes to the menu
	 */



	/**
	 * Takes a dish with all its components specified and adds it to the menu
	 * @param dishName the name of the dish to add
	 * @param dishPrice the price of the dish
	 * @param dishDiet the diet it belongs to (standard or vegetarian)
	 * @param dishType the type of dish (starter, maindish (without a blank space) or dessert)
	 * @param glutenFree specifies whether the meal is gluten free (true if it is, false if it is not)
	 */
	public void addDishRestaurantMenu(String dishName, double dishPrice, String dishDiet, String dishType, boolean glutenFree) {
		this.restaurantMenu.addDish(dishFactory.getDish(dishName, dishPrice, dishDiet, dishType, glutenFree));
	}

	/**
	 * Takes a dish with its price and type, and adds it to the menu with a default standard diet and not gluten free
	 * @param dishName the name of the dish to add
	 * @param dishPrice the price of the dish
	 * @param dishType the type of dish (starter, maindish (without a blank space) or dessert)
	 */
	public void addDishRestaurantMenu(String dishName, double dishPrice, String dishType) {
		this.restaurantMenu.addDish(dishFactory.getDish(dishName, dishPrice, dishType));
	}

	/**
	 * Removes a dish from the menu if it exists
	 * @param dishName a string containing the name of the dish
	 */
	public void removeDish(String dishName) {
		Dish dishToRemove = this.restaurantMenu.getDishByName(dishName);
		if (dishToRemove == null) {
			throw new IllegalArgumentException("The dish doesn't exist.");
		}
		this.restaurantMenu.removeDish(dishToRemove);

	}



	/**
	 * Functions to handle meals
	 */


	/**
	 * Creates a meal and adds it to the menu
	 * @param mealName the name of the meal
	 * @param mealType the type of meal (full or half meal)
	 */
	public void createMeal(String mealName, String mealType) {
		this.restaurantMenu.addMeal(mealFactory.getMeal(mealName, mealType));
	}

	/**
	 * Removes a meal from the menu if it exists
	 * @param mealName a string containing the name of the meal
	 */
	public void deleteMeal(String mealName) {
		Meal mealToRemove = this.restaurantMenu.getMealByName(mealName);
		if (mealToRemove == null) {
			throw new IllegalArgumentException("The meal doesn't exist.");
		}
		this.restaurantMenu.removeMeal(mealToRemove);
	}

	/**
	 * Adds a dish to a meal of the menu. If some of them are not in the menu, throws an error.
	 * @param dishName the name of the dish to add
	 * @param mealName the name of the meal where the dish has to go
	 */
	public void addDishToMeal(String dishName, String mealName) {
		Dish dish = restaurantMenu.getDishByName(dishName);
		if (dish == null) {
			throw new IllegalArgumentException("The dish doesn't exist. Try adding it to the menu.");
		}
		Meal meal = restaurantMenu.getMealByName(mealName);
		if (meal == null) {
			throw new IllegalArgumentException("The meal doesn't exist. Try adding it to the menu.");
		}
		meal.addDish(dish);
	}

	/**
	 * Removes a dish from a meal of the menu. If some of them are not in the menu, throws an error.
	 * @param dishName the name of the dish to remove from the meal
	 * @param mealName the name of the meal where you want to remove the dish
	 */
	public void removeDishFromMeal(String dishName, String mealName) {
		Dish dish = restaurantMenu.getDishByName(dishName);
		if (dish == null) {
			throw new IllegalArgumentException("The dish doesn't exist. Try adding it to the menu.");
		}
		Meal meal = restaurantMenu.getMealByName(mealName);
		if (meal == null) {
			throw new IllegalArgumentException("The meal doesn't exist. Try adding it to the menu.");
		}
		meal.removeDish(dish);
	}

	/**
	 * Takes the string name of a meal, adds it to the meal of the week if it is on the menu (throws an error if not)
	 * @param mealOfTheWeekName a string representing the name of the meal to add to meal of the week
	 */
	public void setSpecialOffer(String mealOfTheWeekName) {
		Meal meal = restaurantMenu.getMealByName(mealOfTheWeekName);
		if (meal == null) {
			throw new IllegalArgumentException("The meal doesn't exist. Try adding it to the menu.");
		}
		this.restaurantMenu.addMealOftheWeek(meal);
	}

	/**
	 * Takes the string name of a meal, removes it from the meal of the week if it is on the menu and in the meal of the week offer (throws an error if not)
	 * @param mealOfTheWeekName a string representing the name of the meal to add to meal of the week
	 */
	public void removeFromSpecialOffer(String mealOfTheWeekName) {
		Meal meal = restaurantMenu.getMealByName(mealOfTheWeekName);
		if (meal == null) {
			throw new IllegalArgumentException("The meal doesn't exist. Try adding it to the menu.");
		}
		boolean result = this.restaurantMenu.removeMealOftheWeek(meal);
		if (result == false) {
			throw new IllegalArgumentException("The meal is not in the meal of the week offer.");
		}
	}

	@Override
	public String toString() {
		return "Restaurant located at " + location + ", named " + getName() + " with ID "+ getID();
	}
}

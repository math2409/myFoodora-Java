/**
 * The object representing an order placed by a customer to a restaurant.
 * It allows a better access to the data of the order, such as
 * the customer who ordered, the restaurant who received it, its content,
 * its price and benefits for myFoodora...
 * It is a convenient way to track the order but also for statistical computations.
 */

package fr.cs.group15.myFoodora.System;

import java.util.ArrayList;
import java.util.Calendar;

import fr.cs.group15.myFoodora.policies.FidCardBasic;
import fr.cs.group15.myFoodora.policies.FidCardPoints;
import fr.cs.group15.myFoodora.restaurantComponents.Dish;
import fr.cs.group15.myFoodora.restaurantComponents.Meal;
import fr.cs.group15.myFoodora.users.Courier;
import fr.cs.group15.myFoodora.users.Customer;
import fr.cs.group15.myFoodora.users.Restaurant;

public class Order {
	
	private int ID;
	private static int counter=0;
	private Restaurant restaurant;
	private Customer customer;
	private Courier courier;
	private Calendar date;
	private ArrayList<Dish> dishes;
	private ArrayList<Meal> meals;
	
	private double orderProfit;
	private double orderPrice;
	
	/**
	 * Constructor of the order
	 * 
	 * @param restaurant restaurant where the order is placed
	 * @param customer customer who placed the order
	 */
	public Order(Restaurant restaurant, Customer customer) {
		this.ID = counter++;
		this.restaurant = restaurant;
		this.customer = customer;
		meals = new ArrayList<Meal>();
		dishes = new ArrayList<Dish>();
		this.date = Calendar.getInstance();
		this.courier = null;
		this.orderProfit = 0;
		this.orderPrice = 0;
		}
	
	/**
	 * Add a meal to the list of meal ordered
	 * 
	 * @param m meal to be added
	 */
	public void addMeal(Meal m) {
		meals.add(m);
	}
	
	/**
	 * Add a dish to the list of dish ordered
	 * 
	 * @param d dish to be added
	 */
	public void addDish(Dish d) {
		dishes.add(d);
	}
	
	/**
	 * Method to calculate the price of the Order taking into account : the restaurant's generic discount factor,
	 * the special meal of the week and the fidelity card plan
	 * 
	 * @return the price of the Order
	 */
	public double getPrice() {
		double price = 0.0;
		if (customer.getFidCardPlan() instanceof FidCardBasic) {
			for (int i = 0; i < meals.size(); i++) {
				if (restaurant.getRestaurantMenu().isMealOfTheWeek(meals.get(i))) {
					price += meals.get(i).getPrice() * (1-restaurant.getSpecialDiscountFactor());
				} else {
					price += meals.get(i).getPrice()*(1-restaurant.getGenericDiscountFactor());
				}
			}
		} else {
			for (int i = 0; i < meals.size(); i++) {
				price += meals.get(i).getPrice()*(1-restaurant.getGenericDiscountFactor());
			}
		}
		for (int i = 0; i < dishes.size(); i++) {
			price += dishes.get(i).getPrice();
			}
		if (customer.getFidCardPlan() instanceof FidCardPoints) {
			FidCardPoints fidcardtemp = (FidCardPoints) customer.getFidCardPlan();
			fidcardtemp.setPoints(customer.getPoints());
		}
		price = customer.getFidCardPlan().applyPlan(price);
		return ((double) Math.round(100*price))/100;
	}

	/**
	 * @return the orderPrice
	 */
	public double getOrderPrice() {
		return orderPrice;
	}

	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	/**
	 * @return the orderProfit
	 */
	public double getOrderProfit() {
		return orderProfit;
	}

	/**
	 * @param orderProfit the orderProfit to set
	 */
	public void setOrderProfit(double orderProfit) {
		this.orderProfit = orderProfit;
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the courier
	 */
	public Courier getCourier() {
		return courier;
	}

	/**
	 * @param courier the courier to set
	 */
	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the dishes
	 */
	public ArrayList<Dish> getDishes() {
		return dishes;
	}

	/**
	 * @param dishes the dishes to set
	 */
	public void setDishes(ArrayList<Dish> dishes) {
		this.dishes = dishes;
	}

	/**
	 * @return the meals
	 */
	public ArrayList<Meal> getMeals() {
		return meals;
	}

	/**
	 * @param meals the meals to set
	 */
	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
	
	

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	

	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		Order.counter = counter;
	}

	@Override
	public String toString() {
		return "Order " + ID + " is ordered by " + customer.getName() + " from " + restaurant.getName() + " the " + date +"."
				+ " It is delivered by " + courier.getName();
	}
	
	
	
	
	

	
	
	

}

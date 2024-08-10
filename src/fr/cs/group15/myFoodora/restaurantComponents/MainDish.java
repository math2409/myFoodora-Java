/**
 * A subclass of Dish class representing the MainDish object.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class MainDish extends Dish {

	/**
	 * Constructor for creating a Main Dish with every attribute
	 * 
	 * @param name name of the Main Dish
	 * @param price price of the Main Dish
	 * @param diet type of the Main Dish (Standard/Vegetarian)
	 * @param glutenFree <code> true </code> if gluten free, <code> false </code> if not
	 */
	public MainDish(String name, double price, String diet, boolean glutenFree) {
		super(name, price, diet, glutenFree);
	}
	
	/**
	 * Constructor for creating a Main Dish which is "Standard" and non glutenfree by default
	 * 
	 * @param name name of the Main Dish
	 * @param price price of the Main dish
	 */
	public MainDish(String name, double price) {
		super(name, price, "standard", false);
	}

	@Override
	public String toString() {
		return "Main Dish " + getName() + " costs " + getPrice() + ", is " + getDiet() + " and glutenfree : " + isGlutenFree();
	}
	
	
}

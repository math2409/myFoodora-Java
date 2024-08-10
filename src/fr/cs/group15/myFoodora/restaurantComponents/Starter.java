/**
 * A subclass of Dish class representing the Starter object.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class Starter extends Dish {
	
	/**
	 * Constructor for creating a Starter with every attribute
	 * 
	 * @param name name of the Starter
	 * @param price price of the Starter
	 * @param diet type of the Starter (Standard/Vegetarian)
	 * @param glutenFree <code> true </code> if gluten free, <code> false </code> if not
	 */
	public Starter(String name, double price, String diet, boolean glutenfree) {
		super(name, price, diet, glutenfree);
	}
	
	
	/**
	 * Constructor for creating a Starter which is "Standard" and non glutenfree by default
	 * 
	 * @param name name of the Starter
	 * @param price price of the Starter
	 */
	public Starter(String name, double price) {
		super(name, price, "standard", false);
	}

	@Override
	public String toString() {
		return "Starter " + getName() + " costs " + getPrice() + ", is " + getDiet() + " and glutenfree : " + isGlutenFree();
	}
	
	

}

/**
 * A subclass of Dish class representing the Dessert object.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class Dessert extends Dish {
	
	/**
	 * Constructor for creating a Dessert with every attribute
	 * 
	 * @param name name of the Dessert
	 * @param price price of the Dessert
	 * @param diet type of the Dessert (Standard/Vegetarian)
	 * @param glutenFree <code> true </code> if gluten free, <code> false </code> if not
	 */
	public Dessert(String name, double price, String diet, boolean glutenFree) {
		super(name, price, diet, glutenFree);
	}
	
	/**
	 * Constructor for creating a Dessert which is "Standard" and non glutenfree by default
	 * 
	 * @param name name of the Dessert
	 * @param price price of the Dessert
	 */
	public Dessert(String name, double price) {
		super(name, price, "standard", false);
	}
		
	

	@Override
	public String toString() {
		return "Dessert " + getName() + " costs " + getPrice() + ", is " + getDiet() + " and glutenfree : " + isGlutenFree();
	}
	
}

/**
 * The superclass Meal. It contains the parameters of a Meal, and also contains methods to manage the Dishes inside itself.
 * It also contains methods used in the CLUI which were easier to implement there.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Meal {
	private String name;
	private List<Dish> mealContent;
	private String diet;
	private boolean glutenFree;
	
	/**
	 * Constructor to create a Meal with specified name
	 * 
	 * @param name name of the Meal
	 */
	public Meal(String name) {
		super();
		this.name = name;
		this.diet = "standard";
		this.glutenFree = false;
		this.mealContent = new ArrayList<Dish>();
	}
	
	/**
	 * @return name name of the Meal
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name name to set to the Meal
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the list of Dishes in the Meal
	 */
	public List<Dish> getMealContent() {
		return mealContent;
	}
	
	/**
	 * @param mealContent list of dishes to set to the Meal
	 */
	public void setMealContent(List<Dish> mealContent) {
		this.mealContent = mealContent;
	}

	/**
	 * @return if the Meal is gluten free (true/false)
	 */
	public boolean isGlutenFree() {
		return glutenFree;
	}

	/**
	 * @param glutenFree true/false
	 */
	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}
	
	/**
	 * @return diet the diet of the Meal (Standard or Vegetarian)
	 */
	public String getDiet() {
		return diet;
	}
	
	/**
	 * @param diet diet to set to the Meal
	 */
	public void setDiet(String diet) {
		this.diet = diet;
	}
	
	/**
	 * @return the price of the Meal (it adds all the prices of its contents)
	 */
	public double getPrice() {
		int price = 0;
		
		for(Dish dish : mealContent) {
			price += dish.getPrice();
		}
		return price;
	}
	
	/**
	 * Checks if the meal is full (is overriden in subclasses)
	 * @return true if the meal is full, false if not
	 */
	public boolean isMealFull() {
		return false;
	}
	
	
	/**
	 * Checks if the dish is in the meal.
	 * @param dish the dish to check
	 * @return true if the dish is in the meal, false if not
	 */
	public boolean dishIsIn(Dish dish) {
		for (Dish innerDish : this.mealContent) {
			if (innerDish.getName().equals(dish.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if a dish of the same type (starter...) already is in the meal
	 * @param dish the dish to test
	 * @return true if there is no other instance of the same type in the meal, false if there already is one
	 */
	public boolean typeIsNotIn(Dish dish) {
		for (Dish innerDish : this.mealContent) {
			if (innerDish.getClass().equals(dish.getClass())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds a dish to the meal. If a dish of the same type is in the meal or if the meal is full, throws an IllegalArgumentException.
	 * @param dish the dish to add to the meal
	 */
	public void addDish(Dish dish) {
		if (this.isMealFull()) {
			throw new IllegalArgumentException("The meal is already full. Try removing a dish before adding one.");
		}
		
		if (!this.typeIsNotIn(dish)) {
			throw new IllegalArgumentException("There already is a dish of this type in the meal. Try removing it or adding a meal of another type.");
		}
		this.mealContent.add(dish);
	}
	
	/**
	 * Removes a dish from the meal. Throws an error if the dish is not in the meal.
	 * @param dish the dish to remove
	 */
	public void removeDish(Dish dish) {
		if (!this.dishIsIn(dish)) {
			throw new IllegalArgumentException("This dish is not in the meal.");
		}
		this.mealContent.remove(dish);
	}
	
	@Override
	public String toString() {
		return "The Meal " + name + " contains " + mealContent + ", is " + diet + ", and glutenFree : " + glutenFree
				+ " and costs " + getPrice();
	}

	@Override
	public int hashCode() {
		 return Objects.hash(name, mealContent, diet, glutenFree);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Meal other = (Meal) obj;
	    return Objects.equals(name, other.name) 
	        && Objects.equals(mealContent, other.mealContent) 
	        && Objects.equals(diet, other.diet)
	        && glutenFree == other.glutenFree;
	}
	
	
	
}

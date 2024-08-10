/**
 * A superclass representing dishes, with subclasses instantiated in the restaurant menus
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

import java.util.Objects;

public class Dish {
	private String name;
	private double price;
	private String diet;
	private boolean glutenFree;
	
	public Dish(String name, double price, String diet, boolean glutenFree) {
		super();
		this.name = name;
		this.price = price;
		this.diet = diet;
		this.glutenFree = glutenFree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public boolean isGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(boolean glutenfree) {
		this.glutenFree = glutenfree;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diet, glutenFree, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dish other = (Dish) obj;
		return Objects.equals(diet, other.diet) && glutenFree == other.glutenFree && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		return "The dish " + name + " costs " + price + ", is " + diet + " and gluten free : " + glutenFree;
	}
	
	
	
}

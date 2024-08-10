/**
 * The Dish Factory to override the method declared in the Abstract Factory, it is used to create Dish objects with given parameters.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class DishFactory extends AbstractFactory {
	
	/**
	 * Returns dish of asked type (starter, main dish or dessert) with every parameter 
	 * 
	 * @param dishName name of the dish
	 * @param dishPrice price of the dish
	 * @param dishDiet diet of the dish (Standard or Vegetarian)
	 * @param dishType type of dish (starter etc...)
	 * @param gludenFree is the dish gluten free (true/false)
	 */
	@Override
	public Dish getDish(String dishName, double dishPrice, String dishDiet, String dishType, boolean glutenFree) {
		if (dishType.equalsIgnoreCase("starter")) {
			return new Starter(dishName, dishPrice, dishDiet, glutenFree);
		} else if (dishType.equalsIgnoreCase("maindish")) {
			return new MainDish(dishName, dishPrice, dishDiet, glutenFree);
		} else if (dishType.equalsIgnoreCase("dessert")) {
			return new Dessert(dishName, dishPrice, dishDiet, glutenFree);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns dish of asked type with default Standard diet and not gluten free
	 * 
	 * @param dishName name of the dish
	 * @param dishPrice price of the dish
	 * @param dishType type of dish (starter etc...)
	 */
	@Override
	public Dish getDish(String dishName, double dishPrice, String dishType) {
		if (dishType.equalsIgnoreCase("starter")) {
			return new Starter(dishName, dishPrice);
		} else if (dishType.equalsIgnoreCase("maindish")) {
			return new MainDish(dishName, dishPrice);
		} else if (dishType.equalsIgnoreCase("dessert")) {
			return new Dessert(dishName, dishPrice);
		} else {
			return null;
		}
	}
	
	/**
	 * Method for the other factory
	 */
	@Override
	public Meal getMeal(String mealName, String mealType) {
		return null;
	}
	
	
}

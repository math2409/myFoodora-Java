/**
 * The Meal Factory to override the method declared in the Abstract Factory, it is used to create Meal objects with given parameters.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class MealFactory extends AbstractFactory {

	 /**
	  * Returns a meal of specified type
	  * 
	  * @param mealName name of the Meal
	  * @param mealType string of the type of the Meal ("FullMeal" or "HalfMeal")
	  */
	@Override
	public Meal getMeal(String mealName, String mealType) {
		if (mealType.equalsIgnoreCase("halfmeal")) {
			return new HalfMeal(mealName);
		} else if (mealType.equalsIgnoreCase("fullmeal")) {
			return new FullMeal(mealName);
		} else {
			return null;
		}
	}
	
	
	/**
	 * Methods for the other type of factory
	 */
	@Override
	public Dish getDish(String dishName, double dishPrice, String dishDiet, String dishType, boolean glutenFree) {
		return null;
	}
	
	@Override
	public Dish getDish(String dishName, double dishPrice, String dishType) {
		return null;
	}


}

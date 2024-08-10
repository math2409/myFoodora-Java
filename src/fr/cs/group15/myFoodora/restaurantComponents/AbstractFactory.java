/**
 * Abstract Factory used in the Abstract Factory Pattern to declare the getDish and getMeal methods overridden in respective factories.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;



public abstract class AbstractFactory {

	public abstract Dish getDish(String dishName, double dishPrice, String dishDiet, String dishType, boolean glutenFree);
	public abstract Dish getDish(String dishName, double dishPrice, String dishType);
	
	public abstract Meal getMeal(String mealName, String mealType);

}

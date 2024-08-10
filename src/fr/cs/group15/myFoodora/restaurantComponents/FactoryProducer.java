/**
 * The Factory Producer of the factory pattern, it is used to create a Dish or a Meal factory.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("dish")) {
			return new DishFactory();
		} else if (choice.equalsIgnoreCase("meal")) {
			return new MealFactory();
		} else {
			return null;
		}
	}

}

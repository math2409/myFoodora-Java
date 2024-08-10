/**
 * A subclass of the Meal superclass representing the HalfMeal object. A HalfMeal contains only 2 dishes
 * respectively Starter + Main Dish or Main Dish + Dessert. Therefore, the class checks if the composition is good
 * to avoid weird HalfMeals.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class HalfMeal extends Meal{

	public HalfMeal(String name) {
		super(name);
	}
	
	public void setHalfMeal(MainDish main, Dish secondary) {
		String diet = "standard";
		boolean glutenFree = false;
		
		getMealContent().add(main);
		
		if (secondary instanceof Starter | secondary instanceof Dessert) {
			getMealContent().add(secondary);
		} else { 
			throw new Error(new ClassCastException());
		}
		
		if (main.getDiet() == secondary.getDiet()) {
			diet = main.getDiet();
		}
		
		if (main.isGlutenFree() && secondary.isGlutenFree()) {
			glutenFree = true;
		}
		
		setDiet(diet);
		setGlutenFree(glutenFree);
	}

	@Override
	public boolean isMealFull() {
		return this.getMealContent().size() == 2;
	}
	
}

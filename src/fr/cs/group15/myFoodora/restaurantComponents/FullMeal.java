/**
 * A subclass of the Meal superclass representing the FullMeal object. A FullMeal contains every type of Dish,
 * and therefore this class checks if every type of dish is unique in the Meal created, to avoid having a Meal with
 * 3 starters for example. It also checks if the FullMeal already contains 3 dishes.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

public class FullMeal extends Meal {

	public FullMeal(String name) {
		super(name);
	}
	
	public void setFullMeal(Starter starter, MainDish main, Dessert dessert) {
		String diet = "standard";
		boolean glutenFree = false;
		
		getMealContent().add(starter);
		getMealContent().add(main);
		getMealContent().add(dessert);
		
		if (main.getDiet() == starter.getDiet() && main.getDiet() == dessert.getDiet()) {
			diet = main.getDiet();
		}
		
		if (starter.isGlutenFree() && main.isGlutenFree() && dessert.isGlutenFree()) {
			glutenFree = true;
		}
		
		setDiet(diet);
		setGlutenFree(glutenFree);
	}
	
	/**
	 * Checks if the meal is full
	 * @return true if the meal is full, false if not
	 */
	@Override
	public boolean isMealFull() {
		return this.getMealContent().size() == 3;
	}
	
}



package fr.cs.group15.myFoodora.tests;

import fr.cs.group15.myFoodora.restaurantComponents.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MealFactoryTest {

	@Test
	public void createMealFactory() {
		MealFactory m = new MealFactory();
		assertTrue(m != null);
	}
	
	@Test
	public void getFullMeal() {
		MealFactory mf = new MealFactory();
		Meal fullMeal = mf.getMeal("King's Meal", "FullMeal");
		assertTrue(fullMeal instanceof FullMeal);
	}
	
	@Test
	public void getHalfMeal() {
		MealFactory mf = new MealFactory();
		Meal halfMeal = mf.getMeal("Kebab + Tiramisu", "HalfMeal");
		assertTrue(halfMeal instanceof HalfMeal);
	}
	
	// We do not use the DishFactory because only the MealFactory is being tested
	Starter starter1 = new Starter("Caesar Salad", 8.0, "Standard", false);
	MainDish mainDish1 = new MainDish("Roasted Potatoes", 14.0, "Standard", true);
	Dessert dessert1 = new Dessert("Tiramisu", 8.0, "Vegetarian", false);
	
	@Test
	public void createFullMeal() {
		MealFactory mf = new MealFactory();
		FullMeal fullMeal = (FullMeal) mf.getMeal("King's Meal", "FullMeal");
		assertTrue(fullMeal instanceof FullMeal);
		fullMeal.setFullMeal(starter1, mainDish1, dessert1);
		
		System.out.println(fullMeal.getDiet());
		
		assertTrue(fullMeal.getMealContent().size() == 3);
		assertTrue(fullMeal.getDiet().equalsIgnoreCase("Standard"));
		assertFalse(fullMeal.isGlutenFree());
	}

}

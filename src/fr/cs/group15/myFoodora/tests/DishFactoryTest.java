package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import fr.cs.group15.myFoodora.restaurantComponents.*;

import org.junit.jupiter.api.Test;

class DishFactoryTest {

	@Test
	public void createDishFactory() {
		DishFactory d = new DishFactory();
		assertTrue(d != null);
	}
	
	@Test
	public void getStarter() {
		DishFactory d = new DishFactory();
		Dish starter = d.getDish("Caesar Salad", 9.0, "Standard", "Starter", false);
		assertTrue(starter instanceof Starter);
	}
	
	@Test
	public void getMainDish() {
		DishFactory d = new DishFactory();
		Dish mdish = d.getDish("Gluten-Free Four Cheese Pizza", 13.0, "Vegetarian", "MainDish", true);
		assertTrue(mdish instanceof MainDish);
	}
	
	@Test
	public void getDessert() {
		DishFactory d = new DishFactory();
		Dish dessert = d.getDish("Tiramisu", 7.0, "Vegetarian", "Dessert", false);
		assertTrue(dessert instanceof Dessert);
	}
}

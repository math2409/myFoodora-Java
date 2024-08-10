package fr.cs.group15.myFoodora.tests;

import fr.cs.group15.myFoodora.restaurantComponents.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DishTest {

	@Test
	public void createDish() {
		Dish d = new Dish("Roasted Potatoes", 14.0, "Vegetarian", true);
		System.out.println(d);
	}

	@Test
	void testEqualsAndHashCode() {
		Dish dish1 = new Dish("Pasta salad", 5.0, "Vegetarian", true);
		Dish dish2 = new Dish("Pasta salad", 5.0, "Vegetarian", true);

		// Test equality
		assertEquals(dish1, dish2, "Dishes with same attributes should be equal");
		assertEquals(dish1.hashCode(), dish2.hashCode(), "Hashcodes should be equal for equal objects");

		// Test inequality by changing diet
		Dish dish3 = new Dish("Pasta salad", 5.0, "Standard", true);
		assertNotEquals(dish1, dish3, "Dishes with different diets should not be equal");
		assertNotEquals(dish1.hashCode(), dish3.hashCode(), "Hashcodes should not be equal for unequal objects");

		// Test inequality by changing glutenFree
		Dish dish4 = new Dish("Pasta salad", 5.0, "Vegetarian", false);
		assertNotEquals(dish1, dish4, "Dishes with different glutenFree should not be equal");
		assertNotEquals(dish1.hashCode(), dish4.hashCode(), "Hashcodes should not be equal for unequal objects");

		// Test inequality by changing name
		Dish dish5 = new Dish("Caesar salad", 5.0, "Vegetarian", true);
		assertNotEquals(dish1, dish5, "Dishes with different names should not be equal");
		assertNotEquals(dish1.hashCode(), dish5.hashCode(), "Hashcodes should not be equal for unequal objects");

		// Test inequality by changing price
		Dish dish6 = new Dish("Pasta salad", 6.0, "Vegetarian", true);
		assertNotEquals(dish1, dish6, "Dishes with different prices should not be equal");
		assertNotEquals(dish1.hashCode(), dish6.hashCode(), "Hashcodes should not be equal for unequal objects");
	}
}

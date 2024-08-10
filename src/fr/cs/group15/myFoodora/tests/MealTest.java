package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.restaurantComponents.*;


class MealTest {

	@Test
	void testcreateMeal() {
		Meal m = new Meal("New Menu");
		System.out.println(m);
	}

	@Test
	void testGetPrice() {
		Meal mtest = new Meal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		mtest.setMealContent(content);
		
		assertEquals(17.0,mtest.getPrice());
	}

	

	@Test
	void testDishIsIn() {
		Meal mtest = new Meal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		mtest.setMealContent(content);
		
		assertTrue(mtest.dishIsIn(new Starter("Pasta salad", 5.0)));
		assertFalse(mtest.dishIsIn(new Starter("Caesar salad", 6.0)));
	}

	@Test
	void testTypeIsNotIn() {
		Meal mtest = new Meal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		mtest.setMealContent(content);
		
		assertTrue(mtest.typeIsNotIn(new Dessert("Chocolate cake", 4.0)));
		assertFalse(mtest.typeIsNotIn(new Starter("Caesar salad", 6.0)));
	}

	@Test
	void testAddDish() {
		
		Meal mtest = new Meal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		mtest.setMealContent(content);
		
		Meal m = new Meal("Pasta Meal");
		m.addDish(new Starter("Pasta salad", 5.0));
		m.addDish(new MainDish("Pasta carbonara", 12.0));
		
		assertEquals(mtest.getMealContent(),m.getMealContent());
		
	}

	@Test
	void testRemoveDish() {
		Meal mtest = new Meal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		mtest.setMealContent(content);
		
		Meal m = new Meal("Pasta Meal");
		MainDish dishToRemove = new MainDish("Pasta carbonara", 12.0);
		content.add(dishToRemove);
		m.setMealContent(content);
		m.removeDish(dishToRemove);
		
		assertEquals(mtest.getMealContent(),m.getMealContent());
	}
	
	@Test
    void testEqualsAndHashCode() {
        // Creating dishes
        Dish dish1 = new Starter("Pasta salad", 5.0, "Vegetarian", true);
        Dish dish2 = new MainDish("Pasta carbonara", 12.0, "Standard", false);

        // Creating two identical meal objects
        Meal meal1 = new Meal("Pasta Meal");
        meal1.addDish(dish1);
        meal1.addDish(dish2);
        meal1.setDiet("Standard");
        meal1.setGlutenFree(false);

        Meal meal2 = new Meal("Pasta Meal");
        meal2.addDish(dish1);
        meal2.addDish(dish2);
        meal2.setDiet("Standard");
        meal2.setGlutenFree(false);

        // Test equality
        assertEquals(meal1, meal2, "Meals with the same attributes should be equal");
        assertEquals(meal1.hashCode(), meal2.hashCode(), "Hashcodes should be equal for equal objects");

        // Creating a different meal
        Meal meal3 = new Meal("Different Meal");
        meal3.addDish(new Dessert("Carrot cake", 6.0, "Vegetarian", true));

        // Test inequality
        assertNotEquals(meal1, meal3, "Meals with different attributes should not be equal");
        assertNotEquals(meal1.hashCode(), meal3.hashCode(), "Hashcodes should not be equal for unequal objects");
    }

}

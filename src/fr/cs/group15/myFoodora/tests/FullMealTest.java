package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.restaurantComponents.Dessert;
import fr.cs.group15.myFoodora.restaurantComponents.Dish;
import fr.cs.group15.myFoodora.restaurantComponents.FullMeal;
import fr.cs.group15.myFoodora.restaurantComponents.MainDish;
import fr.cs.group15.myFoodora.restaurantComponents.Starter;

class FullMealTest {

	@Test
	void testFullMeal() {
		FullMeal fm = new FullMeal("Complete meal");
		System.out.println(fm);
	}
	
	@Test
	void testSetFullMeal() {
		FullMeal fmtest = new FullMeal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		content.add(new Dessert("Carrot cake", 7.0));
		fmtest.setMealContent(content);
		
		FullMeal fm = new FullMeal("Pasta Menu");
		fm.setFullMeal(new Starter("Pasta salad", 5.0), new MainDish("Pasta carbonara", 12.0), new Dessert("Carrot cake", 7.0));
		
		assertEquals(fmtest.getMealContent(),fm.getMealContent());
		
		fmtest.setName("Pasta Menu");
		assertEquals(fmtest,fm);
	}
	
	@Test
	void testIsMealFull() {
		FullMeal fmtest = new FullMeal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		content.add(new Dessert("Carrot cake", 7.0));
		fmtest.setMealContent(content);
		
		assertTrue(fmtest.isMealFull());
		
		fmtest.removeDish(new Dessert("Carrot cake", 7.0));
		assertFalse(fmtest.isMealFull());
	}
}

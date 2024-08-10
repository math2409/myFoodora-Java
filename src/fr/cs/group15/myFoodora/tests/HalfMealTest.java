package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.restaurantComponents.Dish;
import fr.cs.group15.myFoodora.restaurantComponents.HalfMeal;
import fr.cs.group15.myFoodora.restaurantComponents.MainDish;
import fr.cs.group15.myFoodora.restaurantComponents.Starter;

class HalfMealTest {

	@Test
	void testIsMealFull() {
		HalfMeal hmtest = new HalfMeal("Pasta test");
		List<Dish> content = new ArrayList<Dish>();
		content.add(new Starter("Pasta salad", 5.0));
		content.add(new MainDish("Pasta carbonara", 12.0));
		hmtest.setMealContent(content);
		
		assertTrue(hmtest.isMealFull());
		
		hmtest.removeDish(new Starter("Pasta salad", 5.0));
		assertFalse(hmtest.isMealFull());
	}

	@Test
	void testHalfMeal() {
		HalfMeal hm = new HalfMeal("Pasta Menu");
		System.out.println(hm);
	}

	

}

package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.restaurantComponents.MainDish;

class MainDishTest {

	@Test
	void createMainDish() {
		MainDish mdish = new MainDish("Fish and Chips", 15.0, "Vegetarian", false);
		System.out.println(mdish);
	}
}

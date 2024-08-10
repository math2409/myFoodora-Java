package fr.cs.group15.myFoodora.tests;

import fr.cs.group15.myFoodora.restaurantComponents.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DessertTest {

	@Test
	public void createDessert() {
		Dessert d = new Dessert("Ice Cream", 4.0, "Vegetarian", true);
		System.out.println(d);
	}

}

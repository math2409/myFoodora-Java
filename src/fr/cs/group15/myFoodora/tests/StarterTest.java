package fr.cs.group15.myFoodora.tests;

import fr.cs.group15.myFoodora.restaurantComponents.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StarterTest {

	@Test
	public void createStarter() {
		Starter s = new Starter("Caesar Salad", 9.0, "Standard", false);
		System.out.println(s);
	}
}

package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.System.Order;
import fr.cs.group15.myFoodora.policies.FidCardLottery;
import fr.cs.group15.myFoodora.policies.FidCardPoints;
import fr.cs.group15.myFoodora.restaurantComponents.Dish;
import fr.cs.group15.myFoodora.restaurantComponents.DishFactory;
import fr.cs.group15.myFoodora.restaurantComponents.Meal;
import fr.cs.group15.myFoodora.restaurantComponents.MealFactory;
import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Customer;
import fr.cs.group15.myFoodora.users.Restaurant;

class FidCardTest {
	
	DishFactory df = new DishFactory();
	Dish starter = df.getDish("Caesar Salad", 9.0, "Standard", "Starter", false);
	Dish maindish = df.getDish("Steak", 11.0, "Standard", "MainDish", false);
	Restaurant rest = new Restaurant("France", "fr", "fr123", new Address(2,3));
	Customer cust = new Customer("Paul", "Pichard", "pp", "pp3", new Address(4,7));
	
	MealFactory mf = new MealFactory();
	Meal meal = mf.getMeal("TestMeal", "HalfMeal");

	
	@Test
	void FidBasic() {
		
		meal.addDish(starter);
		meal.addDish(maindish);

		Order ord1 = new Order(rest, cust);
		Order ord2 = new Order(rest, cust);

		ord1.addDish(starter);
		ord2.addMeal(meal);
		
		assertEquals(ord1.getPrice(), 9.0);
		assertEquals(ord2.getPrice(), 19.0);
		
	}
	
	@Test
	void FidLottery() {
		cust.setFidCardPlanLottery();
		Order ord1 = new Order(rest,cust);
		ord1.addDish(starter);
		
		FidCardLottery fidcardlot = (FidCardLottery) cust.getFidCardPlan();
		
		fidcardlot.setChance(0);
		assertEquals(ord1.getPrice(),9.0);
		
		fidcardlot.setChance(1);
		assertEquals(ord1.getPrice(),0.0);
	}
	
	@Test
	void FidPoints() {
		cust.setFidCardPlanPoints();
		Order ord1 = new Order(rest,cust);
		ord1.addDish(maindish);
		cust.setPoints(120);
		assertEquals(ord1.getPrice(), 9.9);
	}

}

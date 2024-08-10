package fr.cs.group15.myFoodora.users;

import fr.cs.group15.myFoodora.restaurantComponents.Meal;

public interface Observer {
	public void update(Restaurant restaurant, Meal mealOfTheWeek);
}

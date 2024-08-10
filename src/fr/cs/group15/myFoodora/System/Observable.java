package fr.cs.group15.myFoodora.System;

import fr.cs.group15.myFoodora.restaurantComponents.Meal;
import fr.cs.group15.myFoodora.users.Observer;

public interface Observable {
	public String registerObserver(Observer observer);
	public String removeObserver(Observer observer);
	public String notifyObservers(Meal meal);
}

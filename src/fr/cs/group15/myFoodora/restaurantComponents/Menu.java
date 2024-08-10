/**
 * The menu that uses every other component to form a restaurant menu, with dishes, meals,
 * and meals included in the meal of the week special offer
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.restaurantComponents;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<Starter> starterMenu;
	private List<MainDish> mainMenu;
	private List<Dessert> dessertMenu;
	
	private List<HalfMeal> listOfHalfMeals;
	private List<FullMeal> listOfFullMeals;
	
	private List<Meal> mealOftheWeek;
	
	/**
	 * Constructor that creates an empty menu
	 */
	public Menu() {
		super();
		this.starterMenu = new ArrayList<Starter>();
		this.mainMenu = new ArrayList<MainDish>();
		this.dessertMenu = new ArrayList<Dessert>();
		this.listOfHalfMeals = new ArrayList<HalfMeal>();
		this.listOfFullMeals = new ArrayList<FullMeal>();
		this.mealOftheWeek = new ArrayList<Meal>();
	}

	/**
	 * Constructor to set a menu with every entry
	 * @param starterMenu the list of starters
	 * @param mainMenu the list of main dishes
	 * @param dessertMenu the list of desserts
	 * @param listOfHalfMeals the list of half meals
	 * @param listOfFullMeals the list of full meals
	 * @param mealOftheWeek the list of meals of the week
	 */
	public Menu(List<Starter> starterMenu, List<MainDish> mainMenu, List<Dessert> dessertMenu,
			List<HalfMeal> listOfHalfMeals, List<FullMeal> listOfFullMeals, List<Meal> mealOfheWeek) {
		super();
		this.starterMenu = starterMenu;
		this.mainMenu = mainMenu;
		this.dessertMenu = dessertMenu;
		this.listOfHalfMeals = listOfHalfMeals;
		this.listOfFullMeals = listOfFullMeals;
		this.mealOftheWeek = mealOfheWeek;
	}

	/**
	 * @return the starterMenu
	 */
	public List<Starter> getStarterMenu() {
		return starterMenu;
	}

	/**
	 * @param starterMenu the starterMenu to set
	 */
	public void setStarterMenu(List<Starter> starterMenu) {
		this.starterMenu = starterMenu;
	}

	/**
	 * @return the mainMenu
	 */
	public List<MainDish> getMainMenu() {
		return mainMenu;
	}

	/**
	 * @param mainMenu the mainMenu to set
	 */
	public void setMainMenu(List<MainDish> mainMenu) {
		this.mainMenu = mainMenu;
	}

	/**
	 * @return the dessertMenu
	 */
	public List<Dessert> getDessertMenu() {
		return dessertMenu;
	}

	/**
	 * @param dessertMenu the dessertMenu to set
	 */
	public void setDessertMenu(List<Dessert> dessertMenu) {
		this.dessertMenu = dessertMenu;
	}

	/**
	 * @return the listOfHalfMeals
	 */
	public List<HalfMeal> getListOfHalfMeals() {
		return listOfHalfMeals;
	}

	/**
	 * @param listOfHalfMeals the listOfHalfMeals to set
	 */
	public void setListOfHalfMeals(List<HalfMeal> listOfHalfMeals) {
		this.listOfHalfMeals = listOfHalfMeals;
	}

	/**
	 * @return the listOfFullMeals
	 */
	public List<FullMeal> getListOfFullMeals() {
		return listOfFullMeals;
	}

	/**
	 * @param listOfFullMeals the listOfFullMeals to set
	 */
	public void setListOfFullMeals(List<FullMeal> listOfFullMeals) {
		this.listOfFullMeals = listOfFullMeals;
	}

	/**
	 * @return the mealOftheWeek
	 */
	public List<Meal> getMealOftheWeek() {
		return mealOftheWeek;
	}

	/**
	 * @param mealOftheWeek the mealOftheWeek to set
	 */
	public void setMealOftheWeek(List<Meal> mealOftheWeek) {
		this.mealOftheWeek = mealOftheWeek;
	}
	
	/**
	 * Adds a dish to the corresponding menu (starter menu, main menu, or dessert menu)
	 * @param dish to add to the menu
	 */
	public void addDish(Dish dish) {
		if (dish instanceof Dessert) {
			Dessert dessert = (Dessert) dish;
			dessertMenu.add(dessert);
		} else if (dish instanceof Starter) {
			Starter starter = (Starter) dish;
			starterMenu.add(starter);
		} else if (dish instanceof MainDish) {
			MainDish main = (MainDish) dish;
			mainMenu.add(main);
		}
	}
	
	/**
	 * Adds a meal to the corresponding list (list of half meals or list of full meals)
	 * @param meal to add to the menu
	 */
	public void addMeal(Meal meal) {
		if (meal instanceof HalfMeal) {
			HalfMeal halfMeal = (HalfMeal) meal;
			listOfHalfMeals.add(halfMeal);
		} else if (meal instanceof FullMeal) {
			FullMeal fullMeal = (FullMeal) meal;
			listOfFullMeals.add(fullMeal);
		}
	}
	
	/**
	 Removes a dish of the corresponding menu (starter menu, main menu, or dessert menu)
	 * @param dish to remove from the menu
	 */
	public void removeDish(Dish dish) {
		if (dish instanceof Dessert) {
			Dessert dessert = (Dessert) dish;
			dessertMenu.remove(dessert);
		} else if (dish instanceof Starter) {
			Starter starter = (Starter) dish;
			starterMenu.remove(starter);
		} else if (dish instanceof MainDish) {
			MainDish main = (MainDish) dish;
			mainMenu.remove(main);
		}
	}
	
	/**
	 * Removes a meal of the corresponding list (list of half meals or list of full meals)
	 * @param meal to remove from the menu
	 */
	public void removeMeal(Meal meal) {
		if (meal instanceof HalfMeal) {
			HalfMeal halfMeal = (HalfMeal) meal;
			listOfHalfMeals.remove(halfMeal);
		} else if (meal instanceof FullMeal) {
			FullMeal fullMeal = (FullMeal) meal;
			listOfFullMeals.remove(fullMeal);
		}
	}
	
	/**
	 * Adds a dessert to the menu
	 * @param dessert to add
	 * @return true if the dessert was successfully added
	 */
	public boolean addDish(Dessert dessert) {
		return dessertMenu.add(dessert);
	}
	
	/**
	 * Removes a dessert to the menu
	 * @param dessert to remove
	 * @return true if the dessert was successfully removed
	 */
	public boolean removeDish(Dessert dessert) {
		return dessertMenu.remove(dessert);
	}
	
	/**
	 * Adds a starter to the menu
	 * @param starter to add
	 * @return true if the starter was successfully added
	 */
	public boolean addDish(Starter starter) {
		return starterMenu.add(starter);
	}
	
	/**
	 * Removes a starter to the menu
	 * @param starter to remove
	 * @return true if the starter was successfully removed
	 */
	public boolean removeDish(Starter starter) {
		return starterMenu.remove(starter);
	}

	/**
	 * Adds a main dish to the menu
	 * @param main to add
	 * @return true if the main was successfully added
	 */
	public boolean addDish(MainDish main) {
		return mainMenu.add(main);
	}

	/**
	 * Removes a main dish to the menu
	 * @param main to remove
	 * @return true if the main dish was successfully removed
	 */
	public boolean removeDish(MainDish main) {
		return mainMenu.remove(main);
	}
	
	/**
	 * Adds a half meal to the menu
	 * @param halfMeal to add
	 * @return true if the meal was successfully added
	 */
	public boolean addMeal(HalfMeal halfMeal) {
		return listOfHalfMeals.add(halfMeal);
	}
	
	/**
	 * Removes a half meal to the menu
	 * @param halfMeal to remove
	 * @return true if the meal was successfully removed
	 */
	public boolean removeMeal(HalfMeal halfMeal) {
		return listOfHalfMeals.remove(halfMeal);
	}

	/**
	 * Adds a full meal to the menu
	 * @param fullMeal to add
	 * @return true if the meal was successfully added
	 */
	public boolean addMeal(FullMeal fullMeal) {
		return listOfFullMeals.add(fullMeal);
	}
	
	/**
	 * Removes a full meal to the menu
	 * @param fullMeal to remove
	 * @return true if the meal was successfully removed
	 */
	public boolean removeMeal(FullMeal fullMeal) {
		return listOfFullMeals.remove(fullMeal);
	}
	
	/**
	 * Adds a meal of the week to the menu
	 * @param meal to add
	 * @return true if the meal was successfully added
	 */
	public boolean addMealOftheWeek(Meal meal) {
		return mealOftheWeek.add(meal);
	}
	
	/**
	 * Removes a meal of the week to the menu
	 * @param meal to remove
	 * @return true if the meal was successfully removed
	 */
	public boolean removeMealOftheWeek(Meal meal) {
		return mealOftheWeek.remove(meal);
	}
	
	/**
	 * Utilities to access dishes and meals
	 */
	
	/**
	 * Returns the dish corresponding to the string name given, and null if the meal is not in the menu
	 * @param dishName the name of the dish we are looking for
	 * @return the Dish object corresponding to the given name
	 */
	public Dish getDishByName(String dishName) {
		List<Dish> allDishes = this.getAllDishes();
		for(Dish dish : allDishes) {
			if (dish.getName().equalsIgnoreCase(dishName)) {
				return dish;
			}
		}
		return null;
	}
	
	/**
	 * Returns the meal corresponding to the string name given, and null if the meal is not in the menu
	 * @param mealName the name of the meal we are looking for
	 * @return the Meal object corresponding to the given name
	 */
	public Meal getMealByName(String mealName) {
		List<Meal> allMeals = this.getAllMeals();
		for(Meal meal : allMeals) {
			if (meal.getName().equalsIgnoreCase(mealName)) {
				return meal;
			}
		}
		return null;
	}
	
	/**
	 * Checks if a given meal is meal of the week
	 * @param meal the meal to check
	 * @return true if it is meal of the week, false if it is not
	 */
	public boolean isMealOfTheWeek(Meal meal) {
		for(Meal m : this.mealOftheWeek) {
			if (m.getName().equals(meal.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return a concatenated list of all the dishes in the menu 
	 */
	public List<Dish> getAllDishes() {
		List<Dish> allDishes = new ArrayList<Dish>();
		allDishes.addAll(this.starterMenu);
		allDishes.addAll(this.mainMenu);
		allDishes.addAll(this.dessertMenu);
		return allDishes;
	}
	
	/**
	 * 
	 * @return a concatenated list of all the meals in the menu 
	 */
	public List<Meal> getAllMeals() {
		List<Meal> allMeals = new ArrayList<Meal>();
		allMeals.addAll(this.listOfFullMeals);
		allMeals.addAll(this.listOfHalfMeals);
		return allMeals;
	}
	

	@Override
	public String toString() {
		return " The menu contains : starters : " + starterMenu + ", main dishes : " + mainMenu + ", desserts : " + dessertMenu
				+ ", Half Meals : " + listOfHalfMeals + ", Full Meals : " + listOfFullMeals;
	}
	
	
}

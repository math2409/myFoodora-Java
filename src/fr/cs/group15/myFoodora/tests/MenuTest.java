package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.restaurantComponents.Dessert;
import fr.cs.group15.myFoodora.restaurantComponents.FullMeal;
import fr.cs.group15.myFoodora.restaurantComponents.HalfMeal;
import fr.cs.group15.myFoodora.restaurantComponents.MainDish;
import fr.cs.group15.myFoodora.restaurantComponents.Meal;
import fr.cs.group15.myFoodora.restaurantComponents.Menu;
import fr.cs.group15.myFoodora.restaurantComponents.Starter;

class MenuTest {
	
	@Test
	void testAddDishDish() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		Menu menu = new Menu();
		menu.addDish(new MainDish("Pasta Carbonara", 10.0));
		
		assertEquals(menuTest.getMainMenu(),menu.getMainMenu());
		
		menu.addDish(new Starter("Caesar salad", 5.0));
		
		assertEquals(menuTest.getStarterMenu(),menu.getStarterMenu());
		
		menu.addDish(new Dessert("Carrot cake", 5.0));
		
		assertEquals(menuTest.getDessertMenu(),menu.getDessertMenu());
	}

	@Test
	void testAddMealMeal() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		Menu menu = new Menu();
		menu.addMeal(fmealtest);
		assertEquals(menuTest.getListOfFullMeals(),menu.getListOfFullMeals());
		
		menu.addMeal(mealtest);
		assertEquals(menuTest.getListOfHalfMeals(),menu.getListOfHalfMeals());
	}

	@Test
	void testRemoveDishDish() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		stmenu.add(new Starter("Eggs", 5.0));
		mdmenu.add(new MainDish("Fish and chips", 10.0));
		dmenu.add(new Dessert("Chocolate cake", 6.0));
		Menu menu = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		menu.removeDish(new MainDish("Fish and chips", 10.0));
		
		assertEquals(menuTest.getMainMenu(),menu.getMainMenu());
		
		menu.removeDish(new Starter("Eggs", 5.0));
		
		assertEquals(menuTest.getStarterMenu(),menu.getStarterMenu());
		
		menu.removeDish(new Dessert("Chocolate cake", 6.0));
		
		assertEquals(menuTest.getDessertMenu(),menu.getDessertMenu());
	
		
	}

	@Test
	void testRemoveMealMeal() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);

		
		hmMenu.add(new HalfMeal("Test Meal"));
		fmMenu.add(new FullMeal("Test full meal"));
		Menu menu = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		menu.removeMeal(new HalfMeal("Test Meal"));
		
		assertEquals(menuTest.getListOfHalfMeals(), menu.getListOfHalfMeals());
		
		menu.removeMeal(new FullMeal("Test full meal"));
		
		assertEquals(menuTest.getListOfHalfMeals(),menu.getListOfHalfMeals());

	}

	@Test
	void testGetDishByName() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);

		assertEquals(new Starter("Caesar salad", 5.0), menuTest.getDishByName("caesar salad"));
		assertEquals(new MainDish("Pasta Carbonara", 10.0), menuTest.getDishByName("Pasta Carbonara"));
	}

	@Test
	void testGetMealByName() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		assertEquals(fmealtest, menuTest.getMealByName("pasta menu"));
		assertEquals(mealtest, menuTest.getMealByName("Semi menu"));
	}

	@Test
	void testIsMealOfTheWeek() {
		List<Starter> stmenu = new ArrayList<Starter>();
		stmenu.add(new Starter("Caesar salad", 5.0));
		List<MainDish> mdmenu = new ArrayList<MainDish>();
		mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
		List<Dessert> dmenu = new ArrayList<Dessert>();
		dmenu.add(new Dessert("Carrot cake", 5.0));
		List<HalfMeal> hmMenu = new ArrayList<HalfMeal>();
		HalfMeal mealtest = new HalfMeal("Semi menu");
		mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		hmMenu.add(mealtest);
		List<FullMeal> fmMenu = new ArrayList<FullMeal>();
		FullMeal fmealtest = new FullMeal("Pasta menu");
		fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0),new Dessert("Carrot cake", 5.0));
		fmMenu.add(fmealtest);
		List<Meal> motw = new ArrayList<Meal>();
		motw.add(fmealtest);
		Menu menuTest = new Menu(stmenu,mdmenu,dmenu,hmMenu,fmMenu,motw);
		
		assertTrue(menuTest.isMealOfTheWeek(fmealtest));
		assertFalse(menuTest.isMealOfTheWeek(mealtest));
	}


}

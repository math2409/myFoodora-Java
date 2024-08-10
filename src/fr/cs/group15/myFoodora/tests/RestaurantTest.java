package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.restaurantComponents.Dessert;
import fr.cs.group15.myFoodora.restaurantComponents.FullMeal;
import fr.cs.group15.myFoodora.restaurantComponents.HalfMeal;
import fr.cs.group15.myFoodora.restaurantComponents.MainDish;
import fr.cs.group15.myFoodora.restaurantComponents.Meal;
import fr.cs.group15.myFoodora.restaurantComponents.Menu;
import fr.cs.group15.myFoodora.restaurantComponents.Starter;
import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Restaurant;

class RestaurantTest {
	private Restaurant restaurantTest;

    @BeforeEach
    void setUp() {
        restaurantTest = new Restaurant("casa streat", "casa", "123", new Address(1, 2));

        List<Starter> stmenu = new ArrayList<>();
        stmenu.add(new Starter("Caesar salad", 5.0));
        List<MainDish> mdmenu = new ArrayList<>();
        mdmenu.add(new MainDish("Pasta Carbonara", 10.0));
        List<Dessert> dmenu = new ArrayList<>();
        dmenu.add(new Dessert("Carrot cake", 5.0));
        List<HalfMeal> hmMenu = new ArrayList<>();
        HalfMeal mealtest = new HalfMeal("Semi menu");
        mealtest.setHalfMeal(new MainDish("Pasta Carbonara", 10.0), new Dessert("Carrot cake", 5.0));
        hmMenu.add(mealtest);
        List<FullMeal> fmMenu = new ArrayList<>();
        FullMeal fmealtest = new FullMeal("Pasta menu");
        fmealtest.setFullMeal(new Starter("Caesar salad", 5.0), new MainDish("Pasta Carbonara", 10.0), new Dessert("Carrot cake", 5.0));
        fmMenu.add(fmealtest);
        List<Meal> motw = new ArrayList<>();
        motw.add(fmealtest);

        Menu menuTest = new Menu(stmenu, mdmenu, dmenu, hmMenu, fmMenu, motw);

        restaurantTest.setRestaurantMenu(menuTest);
    }

    @Test
    void testAddDishRestaurantMenuStringDoubleStringStringBoolean() {
        restaurantTest.addDishRestaurantMenu("Tomato salad", 5.0, "standard", "starter", false);
        assertTrue(restaurantTest.getRestaurantMenu().getStarterMenu().stream()
                .anyMatch(dish -> dish.getName().equals("Tomato salad") && dish.getPrice() == 5.0));
    }

    @Test
    void testAddDishRestaurantMenuStringDoubleString() {
        restaurantTest.addDishRestaurantMenu("Tomato salad", 5.0, "starter");
        assertTrue(restaurantTest.getRestaurantMenu().getStarterMenu().stream()
                .anyMatch(dish -> dish.getName().equals("Tomato salad") && dish.getPrice() == 5.0));
    }

    @Test
    void testRemoveDish() {
        restaurantTest.addDishRestaurantMenu("Tomato salad", 5.0, "starter");
        restaurantTest.removeDish("Tomato salad");
        assertFalse(restaurantTest.getRestaurantMenu().getStarterMenu().stream()
                .anyMatch(dish -> dish.getName().equals("Tomato salad")));
    }

    @Test
    void testCreateMeal() {
        restaurantTest.createMeal("New Meal", "fullmeal");
        assertTrue(restaurantTest.getRestaurantMenu().getListOfFullMeals().stream()
                .anyMatch(meal -> meal.getName().equals("New Meal")));
    }

    @Test
    void testDeleteMeal() {
        restaurantTest.createMeal("New Meal", "fullmeal");
        restaurantTest.deleteMeal("New Meal");
        assertFalse(restaurantTest.getRestaurantMenu().getListOfFullMeals().stream()
                .anyMatch(meal -> meal.getName().equals("New Meal")));
    }

    @Test
    void testAddDishToMeal() {
        restaurantTest.createMeal("New Meal", "fullmeal");
        restaurantTest.addDishToMeal("Caesar salad", "New Meal");
        Meal meal = restaurantTest.getRestaurantMenu().getMealByName("New Meal");
        System.out.println(meal);
        assertTrue(meal.getMealContent().stream().anyMatch(dish -> dish.getName().equals("Caesar salad")));
    }

    @Test
    void testRemoveDishFromMeal() {
        restaurantTest.createMeal("New Meal", "fullmeal");
        restaurantTest.addDishToMeal("Caesar salad", "New Meal");
        restaurantTest.removeDishFromMeal("Caesar salad", "New Meal");
        Meal meal = restaurantTest.getRestaurantMenu().getMealByName("New Meal");
        assertFalse(meal.getMealContent().stream().anyMatch(dish -> dish.getName().equals("Caesar salad")));
    }

    @Test
    void testSetSpecialOffer() {
        restaurantTest.createMeal("Special Meal", "fullmeal");
        restaurantTest.setSpecialOffer("Special Meal");
        assertTrue(restaurantTest.getRestaurantMenu().getMealOftheWeek().stream()
                .anyMatch(meal -> meal.getName().equals("Special Meal")));
    }

    @Test
    void testRemoveFromSpecialOffer() {
        restaurantTest.createMeal("Special Meal", "fullmeal");
        restaurantTest.setSpecialOffer("Special Meal");
        restaurantTest.removeFromSpecialOffer("Special Meal");
        assertFalse(restaurantTest.getRestaurantMenu().getMealOftheWeek().stream()
                .anyMatch(meal -> meal.getName().equals("Special Meal")));
    }

}

package fr.cs.group15.myFoodora.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.System.Order;
import fr.cs.group15.myFoodora.exception.WrongUserException;
import fr.cs.group15.myFoodora.policies.FastestDelivery;
import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Courier;
import fr.cs.group15.myFoodora.users.Customer;
import fr.cs.group15.myFoodora.users.Manager;
import fr.cs.group15.myFoodora.users.Restaurant;

class CoreTest {
	private Core core;
    private Manager manager;
    private Customer customer;
    private Restaurant restaurant;
    private Courier courier;

    @BeforeEach
    void setUp() {
        core = new Core();
        manager = new Manager("Alice", "Smith", "manager1", "password1");
        customer = new Customer("John", "Doe", "john_doe", "password2", new Address(15,16));
        restaurant = new Restaurant("PizzaPlace", "pizza123", "password3", new Address(12,13));
        courier = new Courier("Bob", "Michel", "bobmichel", "bobpass", new Address(17,18));
        core.getRegisteredManager().add(manager);
        core.getRegisteredCustomers().add(customer);
        core.getRegisteredRestaurants().add(restaurant);
        core.getRegisteredCourier().add(courier);
        core.getUsers().put("bobmichel", courier);
        core.getUsers().put("pizza123", restaurant);
        core.getUsers().put("john_doe", customer);
        core.getUsers().put("manager1", manager);
        
    }

	@Test
    void testLoginSuccessfulAsManager() {
        assertDoesNotThrow(() -> core.logIn("manager1", "password1"));
        assertEquals(manager, core.getCurrent_user());
    }

    @Test
    void testLoginSuccessfulAsCustomer() {
        assertDoesNotThrow(() -> core.logIn("john_doe", "password2"));
        assertEquals(customer, core.getCurrent_user());
    }

    @Test
    void testLoginSuccessfulAsRestaurant() {
        assertDoesNotThrow(() -> core.logIn("pizza123", "password3"));
        assertEquals(restaurant, core.getCurrent_user());
    }

    @Test
    void testLoginFailureWrongUsername() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            core.logIn("wrong_username", "password1");
        });
        assertEquals("Username does not exist", exception.getMessage());
    }

    @Test
    void testLoginFailureWrongPassword() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            core.logIn("manager1", "wrong_password");
        });
        assertEquals("Wrong password", exception.getMessage());
    }

    @Test
    void testLoginAlreadyLoggedIn() {
        assertDoesNotThrow(() -> core.logIn("manager1", "password1"));

        Error exception = assertThrows(IllegalAccessError.class, () -> {
            core.logIn("john_doe", "password2");
        });
        assertEquals("Another user is already logged-in", exception.getMessage());
    }
    
    @Test
    void testRegisterUserAsManager() {
        core.logIn("ceo", "123456789");

        Manager manager = new Manager("Alice2", "Smith2", "manager2", "password2");
        core.registerUser(manager);

        assertEquals(manager, core.getUsers().get("manager2"));
    }

    @Test
    void testRegisterUserAsCustomer() {
        Customer customer = new Customer("John1", "Doe1", "john_doe1", "password7", new Address(20,20));

        core.register(customer);

        assertEquals(customer, core.getUsers().get("john_doe1"));
        assertEquals(customer, core.getCurrent_user());
    }

    @Test
    void testAddDishRestaurantMenu() {
        core.logIn("pizza123", "password3");
        
        String result = core.addDishRestaurantMenu("Margherita", "MainDish", "Vegetarian", false, 12.5);

        assertEquals("Successfully added Margherita to PizzaPlace menu.", result);
    }

    @Test
    void testCreateOrder() {
        core.logIn("john_doe", "password2");
        
        Order order = core.createOrder("PizzaPlace");

        assertNotNull(order);
        assertEquals(restaurant, order.getRestaurant());
    }

    @Test
    void testSetDeliveryPolicyToFastest() {
        // Log in as a Manager and change delivery policy
        core.logIn("ceo", "123456789");
        core.setDeliveryPolicyToFastest();

        // Verify the delivery policy is changed
        assertTrue(core.getDpolicy() instanceof FastestDelivery);
    }
    
}

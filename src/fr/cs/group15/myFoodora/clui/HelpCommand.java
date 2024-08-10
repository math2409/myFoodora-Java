package fr.cs.group15.myFoodora.clui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class HelpCommand implements Command {
	private static HashMap<String, String> commandDescriptions = new HashMap<>();

	static {
        commandDescriptions.put("startup", "startup <> : initializes the system by creating a series of users and menus for the restaurants.");

        commandDescriptions.put("login", "login <username> <password> : Allows a user to login.");

        commandDescriptions.put("logout", "logout <> : Allows the currently logged on user to log off.");

        commandDescriptions.put("registerrestaurant", "registerRestaurant <name> <username> <address> <password> : For the currently logged on manager to add a restaurant with the given name, address (bi-dimensional integer coordinates separated by a comma, such as 15,16), username, and password to the system.");

        commandDescriptions.put("registercustomer", "registerCustomer <firstName> <lastName> <username> <address> <password> : For the currently logged on manager to add a customer with the given first name, last name, username, address (bi-dimensional integer coordinates separated by a comma, such as 15,16), and password to the system.");

        commandDescriptions.put("registercourier", "registerCourier <firstName> <lastName> <username> <position> <password> : For the currently logged on manager to add a courier with the given first name, last name, username, position (bi-dimensional integer coordinates separated by a comma, such as 15,16), and password to the system.");

        commandDescriptions.put("registermanager", "registerManager <firstName> <lastName> <username> <password> : For the currently logged on manager to add another manager with the given first name, last name, username, and password to the system.");

        commandDescriptions.put("register", "register <firstName> <lastName> <username> <address> <password> : For a customer to register himself (without being logged on) with the given first name, last name, username, address (bi-dimensional integer coordinates separated by a comma, such as 15,16), and password to the system.");

        commandDescriptions.put("setphone", "setphone <phoneNumber> : sets the phone number of the currently logged on customer or courier.");
        
        commandDescriptions.put("setmail", "setmail <emailAddress> : sets the email address of the currently logged on customer.");
        
        commandDescriptions.put("setnotifications", "setnotifications <wantNotif> : sets the preferences of the currently logged on customer to receive notifications about special offers (specify true if you do, false if you don't).");
        
        commandDescriptions.put("adddishrestaurantmenu", "addDishRestaurantMenu <dishName> <dishCategory> (<diet> <glutenfree> : both optional) <unitPrice> : For the currently logged on restaurant to add a dish with the given name, category (starter, maindish (no space), dessert), food diet (standard or vegetarian), whether it is gluten free (precise true if it is, false if it is not) and price to the menu of the restaurant.\nYou must precise both the diet and the glutenfree arguments, or none. If you don't, the dish will be standard and not gluten free by default.");

        commandDescriptions.put("removedishrestaurantmenu", "removeDishRestaurantMenu <dishName> : For the currently logged on restaurant to remove a dish with the given name from its menu if it exists.");

        commandDescriptions.put("createmeal", "createMeal <mealName> <mealType> : For the currently logged on restaurant to create a meal with the given name and a given type (fullmeal or halfmeal, without space), and adds it to its menu.");

        commandDescriptions.put("adddishtomeal", "addDishToMeal <dishName> <mealName> : For the currently logged on restaurant to add a dish to a meal with the given name.");

        commandDescriptions.put("removedishfrommeal", "removeDishFromMeal <dishName> <mealName> : For the currently logged on restaurant to remove a dish from a meal with the given name.");

        commandDescriptions.put("showmeal", "showMeal <mealName> : For the currently logged on restaurant to show the dishes in a meal with the given name.");

        commandDescriptions.put("deletemeal", "deleteMeal <mealName> : For the currently logged on restaurant to remove a meal with the given name from its menu.");

        commandDescriptions.put("setspecialoffer", "setSpecialOffer <mealName> : For the currently logged on restaurant to add a meal to the meal-of-the-week special offer.");

        commandDescriptions.put("removefromspecialoffer", "removeFromSpecialOffer <mealName> : For the currently logged on restaurant to remove a meal from the special offer.");

        commandDescriptions.put("createorder", "createOrder <restaurantName> : For the currently logged on customer to create an order from the given restaurant.");

        commandDescriptions.put("additemtoorder", "addItemToOrder <itemName> : For the currently logged on customer to add an item (either a menu item or a meal-deal) to the current order.");

        commandDescriptions.put("endorder", "endOrder <> : For the currently logged on customer to finalize the current order and pay for it. Allows the system to treat it and allocate it to a courier.");

        commandDescriptions.put("changestate", "changeState <dutyState> : For the currently logged on courier to change their state (specify \"onDuty\" or \"offDuty\").");

        commandDescriptions.put("acceptorder", "acceptOrder <orderID> : For the currently logged on courier to accept an order to deliver with its ID.");

        commandDescriptions.put("deliverorder", "deliverOrder <> : For the currently logged on courier to tell the system the previously accepted order was delivered. It won't work if you have not accepted any order!");

        commandDescriptions.put("finddeliverer", "findDeliverer <orderID> : For the currently logged on restaurant to allocate an order to a deliverer by applying the current delivery policy.\nThis command is not necessary since the allocation is automatically performed. It is only written for the benefits of tests.");

        commandDescriptions.put("setdeliverypolicy", "setDeliveryPolicy <delPolicyName> : For the currently logged on MyFoodora manager to set the delivery policy of the system to the one passed as an argument (\"fastest\" or \"fairoccupation\").");

        commandDescriptions.put("associatecard", "associateCard <userName> <cardType> : For the currently logged on MyFoodora manager to associate a fidelity card (\"basic\", \"points\" or \"lottery\") to a user with the given name.");

        commandDescriptions.put("showcourierdeliveries", "showCourierDeliveries <> : For the currently logged on MyFoodora manager to display the list of couriers sorted in decreasing order w.r.t. the number of completed deliveries.");

        commandDescriptions.put("showrestauranttop", "showRestaurantTop <> : For the currently logged on MyFoodora manager to display the list of restaurants sorted in decreasing order w.r.t. the number of delivered orders.");

        commandDescriptions.put("showcustomers", "showCustomers <> : For the currently logged on MyFoodora manager to display the list of customers.");

        commandDescriptions.put("showmenuitem", "showMenuItem <restaurant-name> : For the currently logged on MyFoodora manager to display the menu of a given restaurant.");

        commandDescriptions.put("showtotalprofit", "showTotalProfit (<startDate> <endDate> both optional) : For the currently logged on MyFoodora manager to show the total profit of the system within a time interval if a start date and an end date are specified. If none are specified, show the total profit of the system since creation.");

        commandDescriptions.put("runtest", "runTest <testScenario-file> : For a generic user of the CLUI (no need to login) to execute the list of CLUI commands contained in the testScenario file passed as an argument.");

        commandDescriptions.put("help", "help <> : For a generic user of the CLUI (no need to login) to display the list of available CLUI commands with an indication of their syntax and their action.\nhelp <command> : Display the description of the specified command.");
    }

	@Override
	public void execute(String[] args) {
		if (args.length == 0) {
			try (BufferedReader reader = new BufferedReader(new FileReader("src/fr/cs/group15/myFoodora/text/help.txt"))) {
		        System.out.println("You can type any command of this list.\nThe arguments must be separated by a blank space. The arguments are specified between <>. For any argument, if you need tu put a space, you can specify it between \".");
				String line;
		        while ((line = reader.readLine()) != null) {
		            System.out.println(line);
		        }
		    } catch (IOException e) {
		        System.err.println("Error reading the help file: " + e.getMessage());
		    }
		} else if (args.length == 1) {
			String description = commandDescriptions.get(args[0].toLowerCase());
	        if (description != null) {
	            System.out.println(description);
	        } else {
	            System.out.println("Command not found: " + args[0]);
	        }
		} else {
			throw new IllegalArgumentException("Invalid arguments, type 'help' for a list of commands and help <commandName> for a description of a command.");
		}
	}

}

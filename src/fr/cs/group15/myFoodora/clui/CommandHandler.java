package fr.cs.group15.myFoodora.clui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.exception.WrongUserException;

/**
 * This class handles the command typed by the user, map them to their actions and execute them
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */
public class CommandHandler {
	private final Map<String, Command> commands = new HashMap<>();
	private Core core;

	/**
	 * Creates a list of available commands, mapped to the classes that represent them
	 */
	public CommandHandler() {
		this.core = new Core();
		// Command writing to map a command to a java class :
		// commands.put(String commandName, new CommandInstance());

		commands.put("login", new LoginCommand(core));
		commands.put("logout", new LogOutCommand(core));
		commands.put("registerrestaurant", new RegisterRestaurantCommand(core));
		commands.put("registercustomer", new RegisterCustomerCommand(core));
		commands.put("registercourier", new RegisterCourierCommand(core));
		commands.put("registermanager", new RegisterManagerCommand(core));
		commands.put("register", new RegisterCommand(core));
		commands.put("setphone", new SetPhoneCommand(core));
		commands.put("setmail", new SetMailCommand(core));
		commands.put("setnotifications", new SetNotificationsCommand(core));
		commands.put("adddishrestaurantmenu", new AddDishRestaurantMenuCommand(core));
		commands.put("removedishrestaurantmenu", new RemoveDishRestaurantMenuCommand(core));
		commands.put("createmeal", new CreateMealCommand(core));
		commands.put("adddishtomeal", new AddDishToMealCommand(core));
		commands.put("removedishfrommeal", new RemoveDishFromMealCommand(core));
		commands.put("showmeal", new ShowMealCommand(core));
		commands.put("deletemeal", new DeleteMealCommand(core));
		commands.put("setspecialoffer", new SetSpecialOfferCommand(core));
		commands.put("removefromspecialoffer", new RemoveFromSpecialOffer(core));
		commands.put("createorder", new CreateOrderCommand(core));
		commands.put("additemtoorder", new AddItemToOrderCommand(core));
		commands.put("endorder", new EndOrderCommand(core));
		
		commands.put("changestate", new ChangeStateCommand(core));
		commands.put("acceptorder", new AcceptOrderCommand(core));
		commands.put("deliverorder", new DeliverOrderCommand(core));
		commands.put("finddeliverer", new FindDelivererCommand(core));
		
		commands.put("setdeliverypolicy", new SetDeliveryPolicyCommand(core));
		commands.put("setservicefee", new SetServiceFeeCommand(core));
		commands.put("setmarkuppercentage", new SetMarkupPercentageCommand(core));
		commands.put("setdeliverycost", new SetDeliveryCostCommand(core));
		
		commands.put("associatecard", new AssociateCardCommand(core));
		commands.put("showcourierdeliveries", new ShowCourierDeliveriesCommand(core));
		commands.put("showrestauranttop", new ShowRestaurantTopCommand(core));
		commands.put("showcustomers", new ShowCustomersCommand(core));
		commands.put("showmenuitem", new ShowMenuItemCommand(core));
		commands.put("showtotalprofit", new ShowTotalProfitCommand(core));
		commands.put("runtest", new RunTestCommand(core, this));
		commands.put("help", new HelpCommand());
		commands.put("startup", new StartupCommand(core, this));
	}


	/**
	 * Handles the command typed by the user
	 * @param commandName the command name typed by the user
	 * @param args the arguments typed by the user if they exist
	 */
	public void handleCommand(String commandName, String[] args) {
		Command command = commands.get(commandName.toLowerCase());
		if (command != null) {
			try {
				command.execute(args);
			} catch (IllegalArgumentException e) {
				System.out.println("Illegal argument Error : " + e.getMessage());
			} catch (WrongUserException e) {
				System.out.println(" Wrong user Error : " + e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("NullPointerException : " + e.getMessage());
			} catch (IllegalAccessError e) {
				System.out.println("IllegalAccessError : " + e.getMessage());
			}
		} else {
			System.out.println(commandName + " does not exist. Type 'help' for a list of commands.");
		}
	}

	/**
	 * Allows to read a textfile with the commands typed (runtest textfile.txt or startup)
	 * @param filename
	 */
	public void readTextFile(String filename) {
		String filepath = "src/fr/cs/group15/myFoodora/text/";
		File file = new File(filepath + filename);
		ArrayList<String> commands = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (!line.isEmpty()) {
					commands.add(line);
				}
			}
			System.out.println("Executing commands from : " + filename);
			for (String command : commands) {
				String[] parts = MainCLUI.splitInput(command);
				String commandName = parts[0];
				String[] commandArgs = new String[parts.length - 1];
				System.arraycopy(parts, 1, commandArgs, 0, parts.length - 1);
				handleCommand(commandName, commandArgs);
			}
		} catch (FileNotFoundException e) {
            System.out.println("Invalid filename.");
        }
	}
	
	
	
	
	/**
	 * @return a map of command names to the classes that perform them
	 */
	public Map<String, Command> getCommands() {
		return commands;
	}
}

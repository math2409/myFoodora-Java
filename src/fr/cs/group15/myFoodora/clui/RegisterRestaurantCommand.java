package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Restaurant;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RegisterRestaurantCommand implements Command{
	private Core core;

	/**
	 * @param core
	 */
	public RegisterRestaurantCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 4) {
			throw new IllegalArgumentException("Wrong number of arguments. Type 'help' for a list of commands.");
		}
		String name = args[0];
		String address = args[2];
		String[] dividedAddress = address.split(",");
		int xCoord = Integer.valueOf(dividedAddress[0]);
		int yCoord = Integer.valueOf(dividedAddress[1]);
 		String username = args[1];
		String password = args[3];
		
		Restaurant restaurant = new Restaurant(name, username, password, new Address(xCoord,yCoord));
		
		core.registerUser(restaurant);
		System.out.println("Successfully created a Restaurant (" + username + ")");
	}
	
	
}

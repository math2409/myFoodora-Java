package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Customer;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RegisterCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public RegisterCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 5) {
			throw new IllegalArgumentException("Wrong number of arguments. Type 'help' for a list of commands.");
		}
		String firstName = args[0];
		String lastName = args[1];
		String username = args[2];
		String address = args[3];
		String[] dividedAddress = address.split(",");
		int xCoord = Integer.valueOf(dividedAddress[0]);
		int yCoord = Integer.valueOf(dividedAddress[1]);
		String password = args[4];
		
		Customer customer = new Customer(firstName, lastName, username, password, new Address(xCoord,yCoord));
		
		core.register(customer);
	}
	
	

}

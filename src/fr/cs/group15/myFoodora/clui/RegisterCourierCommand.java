/**
 * 
 */
package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Courier;

/**
 * 
 */
/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RegisterCourierCommand implements Command {
	private Core core;
	

	/**
	 * @param core
	 */
	public RegisterCourierCommand(Core core) {
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
		String position = args[3];
		String[] dividedAddress = position.split(",");
		int xCoord = Integer.valueOf(dividedAddress[0]);
		int yCoord = Integer.valueOf(dividedAddress[1]);
		String password = args[4];
		
		Courier courier = new Courier(firstName, lastName, username, password, new Address(xCoord,yCoord));
		
		core.registerUser(courier);
		System.out.println("Successfully created a Courier (" + username + ")");
	}

}

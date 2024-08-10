package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.users.Manager;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RegisterManagerCommand implements Command {
private Core core;
	
	/**
	 * @param core
	 */
	public RegisterManagerCommand(Core core) {
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
		String password = args[4];
		
		Manager manager = new Manager(firstName, lastName, username, password);
		
		core.registerUser(manager);
		System.out.println("Successfully created a manager (" + username + ")");
	}
}

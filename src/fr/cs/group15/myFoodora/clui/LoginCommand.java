package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * Takes the username and the password and logs in if they both fit with a user
 */
/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class LoginCommand implements Command{
	private Core core;

	/**
	 * @param core
	 */
	public LoginCommand(Core core) {
		super();
		this.core = core;
	}


	@Override
	public void execute(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Enter a username and a password. Type 'help' for a list of commands.");
		}
		
		String username = args[0];
		String password = args[1];
		
		core.logIn(username, password);
	}
	
}

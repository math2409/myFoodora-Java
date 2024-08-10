package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class SetNotificationsCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public SetNotificationsCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Wrong number of arguments. Type 'help' for a list of commands.");
		}
		core.setPreference(Boolean.parseBoolean(args[0]));
		System.out.println("Successfully set the notifications preference to "+ args[0]);
	}
}

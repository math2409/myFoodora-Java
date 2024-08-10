package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RemoveDishRestaurantMenuCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public RemoveDishRestaurantMenuCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter a dish name. Type 'help' for a list of commands.");
		}
		String result = core.removeDishRestaurantMenu(args[0]);
		System.out.println(result);
	}

}

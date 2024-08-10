package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class EndOrderCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public EndOrderCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 0) {
			throw new IllegalArgumentException("No arguments needed. Type 'help' for a list of commands.");
		}
		core.endOrder();
	}

}

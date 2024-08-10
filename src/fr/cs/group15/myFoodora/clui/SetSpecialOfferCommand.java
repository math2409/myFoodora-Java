package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class SetSpecialOfferCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public SetSpecialOfferCommand(Core core) {
		super();
		this.core = core;
	}


	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter the name of a meal. Type 'help' for a list of commands.");
		}
		String mealName = args[0];
		core.setSpecialOffer(mealName);
	}

}

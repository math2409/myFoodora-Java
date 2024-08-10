package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

public class RemoveFromSpecialOffer implements Command {
	private Core core;
	
	/**
	 * @param core
	 */
	public RemoveFromSpecialOffer(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter the name of a meal. Type 'help' for a list of commands.");
		}
		String mealName = args[0];
		core.removeSpecialOffer(mealName);
	}

}

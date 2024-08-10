package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class ShowMealCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public ShowMealCommand(Core core) {
		super();
		this.core = core;
	}
	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter a mealname. Type 'help' for a list of commands.");
		}
		core.showMeal(args[0]);
	}

}

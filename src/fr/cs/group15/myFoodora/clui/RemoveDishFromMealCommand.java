package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RemoveDishFromMealCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public RemoveDishFromMealCommand(Core core) {
		super();
		this.core = core;
	}
	
	@Override
	public void execute(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Enter a mealname.. Type 'help' for a list of commands.");
		}
		String result = core.removeDishFromMeal(args[0], args[1]);
		System.out.println(result);
	}

}
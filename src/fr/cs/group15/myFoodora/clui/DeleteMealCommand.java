package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class DeleteMealCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public DeleteMealCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter a mealname. Type 'help' for a list of commands.");
		}
		String result = core.deleteMeal(args[0]);
		System.out.println(result);
	}

}

package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class AddDishRestaurantMenuCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public AddDishRestaurantMenuCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 5 && args.length != 3) {
			throw new IllegalArgumentException("Enter the name of a dish, its category (either starter, maindish or dessert), its diet (standard, vegetarian) (optional) if it is gluten free (true if it is, false if it isn't) (optional) and its price. Type 'help' for a list of commands.");
		}
		if (args.length == 5) {
			String result = core.addDishRestaurantMenu(args[0], args[1], args[2], Boolean.parseBoolean(args[3]), Double.parseDouble(args[4]));
			System.out.println(result);
		}
		if (args.length == 3) {
			String result = core.addDishRestaurantMenu(args[0], args[1], Double.parseDouble(args[2]));
			System.out.println(result);
		}
	}

}

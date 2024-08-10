package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.System.Order;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class CreateOrderCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public CreateOrderCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Wrong number of arguments. Type 'help' for a list of commands.");
		}
		
		String restaurantName = args[0];
		
		Order current_order = core.createOrder(restaurantName);
		core.setCurrent_order(current_order);
		
		System.out.println("Successfully created an order at "+restaurantName+".");
	}

}

package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;
import fr.cs.group15.myFoodora.users.Courier;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class FindDelivererCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public FindDelivererCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter the ID of the order. Type 'help' for a list of commands.");
		}
		int id = Integer.parseInt(args[0]);
		Courier allocatedCourier = core.findDeliverer(id);
		System.out.println(allocatedCourier.getName() + " was allocated to this order.");
	}
}
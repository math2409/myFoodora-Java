package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class ShowCustomersCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public ShowCustomersCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length > 0) {
			throw new IllegalArgumentException("No argument required. Please try again.");
		}
		
		core.showCustomers();
		
	}
	
	
}

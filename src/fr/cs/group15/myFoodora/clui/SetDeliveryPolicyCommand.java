package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class SetDeliveryPolicyCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public SetDeliveryPolicyCommand(Core core) {
		super();
		this.core = core;
	}
	
	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter a username and a password. Type 'help' for a list of commands.");
		}
		if (args[0].equalsIgnoreCase("fastest")) {
			core.setDeliveryPolicyToFastest();
		} else if (args[0].equalsIgnoreCase("fairoccupation") ) {
			core.setDeliveryPolicyToFairOccupation();
		} else {
			throw new IllegalArgumentException(args[0] + " is not an available delivery policy. Try Fastest or FairOccupation.");
		}
	}
}

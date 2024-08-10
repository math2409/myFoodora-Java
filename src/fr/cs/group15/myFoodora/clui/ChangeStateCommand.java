package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class ChangeStateCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public ChangeStateCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter a duty state (onDuty or offDuty). Type 'help' for a list of commands.");
		}
		if (args[0].equalsIgnoreCase("onDuty")) {
			core.changeState(true);
			System.out.println("Successfully changed state to OnDuty !");
		} else if (args[0].equalsIgnoreCase("offDuty")) {
			core.changeState(false);
			System.out.println("Successfully changed state to OffDuty !");
		} else {
			throw new IllegalArgumentException(args[0] + " is not an accepted state, change for OnDuty or OffDuty");
		}
		
	}
	
	
	

}

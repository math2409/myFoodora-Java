package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class AssociateCardCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public AssociateCardCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Too many arguments, please only specify a username and card type.");
		}
		String username = args[0];
		String cardType = args[1];
		
		core.associateCard(username, cardType);
		
	}
	
	
}

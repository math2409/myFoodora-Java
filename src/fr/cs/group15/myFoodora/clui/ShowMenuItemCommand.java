package fr.cs.group15.myFoodora.clui;
import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class ShowMenuItemCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public ShowMenuItemCommand(Core core) {
		super();
		this.core = core;
	}


	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Enter a restaurant name. Type 'help' for a list of commands.");
		}
		core.showMenuItem(args[0]);
	}

}

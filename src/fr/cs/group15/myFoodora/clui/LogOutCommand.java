package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class LogOutCommand implements Command{
	private Core core;

	/**
	 * @param core
	 */
	public LogOutCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		String result = core.logOut();
		System.out.println(result);
	}
	
	
}

package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class StartupCommand implements Command {
	private Core core;
	private CommandHandler cmdHandler;

	/**
	 * @param core
	 * @param cmdHandler
	 */
	public StartupCommand(Core core, CommandHandler cmdHandler) {
		super();
		this.core = core;
		this.cmdHandler = cmdHandler;
	}

	@Override
	public void execute(String[] args) {
		if (args.length > 0) {
			throw new IllegalArgumentException("No argument required, just type startup");
		}
		cmdHandler.readTextFile("startup.ini");
		
	}
	
	

}

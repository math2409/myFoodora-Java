package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class RunTestCommand implements Command {
	private Core core;
	private CommandHandler cmdHandler;
	
	/**
	 * @param core
	 * @param cmdHandler
	 */
	public RunTestCommand(Core core, CommandHandler cmdHandler) {
		super();
		this.core = core;
		this.cmdHandler = cmdHandler;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Please only enter a filename");
		}
		String filename = args[0];
		cmdHandler.readTextFile(filename);
		
	}


	
	
	

}

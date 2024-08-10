package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class ShowTotalProfitCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public ShowTotalProfitCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length != 0 && args.length != 2) {
			throw new IllegalArgumentException("Invalid arguments, please only cast the command with 0 or 2 arguments (start date and end date under dd-MM-yyyy format)");
		}
		
		if (args.length == 0) {
			core.showTotalProfit();
		}
		
		if (args.length == 2) {
			String startDateStr = args[0];
			String endDateStr = args[1];
			core.showTotalProfit(startDateStr, endDateStr);
		}
	}
	
	

}

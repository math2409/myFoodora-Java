package fr.cs.group15.myFoodora.clui;

import fr.cs.group15.myFoodora.System.Core;

/**
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

public class AcceptOrderCommand implements Command {
	private Core core;

	/**
	 * @param core
	 */
	public AcceptOrderCommand(Core core) {
		super();
		this.core = core;
	}

	@Override
	public void execute(String[] args) {
		if (args.length !=1) {
			throw new IllegalArgumentException("Wrong argument, please enter an order ID");
		}
		int orderID = Integer.valueOf(args[0]);
		core.acceptOrder(orderID);
		}
	}
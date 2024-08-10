/**
 * Interface used in the Strategy pattern to manage the different Fidelity policies. It contains the method bound to be
 * overridden by the policies classes.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */


package fr.cs.group15.myFoodora.policies;

public interface FidCard {
	
	public double applyPlan(double price);

}

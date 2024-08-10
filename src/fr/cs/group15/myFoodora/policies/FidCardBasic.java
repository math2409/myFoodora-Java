/**
 * Implementation of Basic Fidelity Plan of the strategy pattern.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */


package fr.cs.group15.myFoodora.policies;

public class FidCardBasic implements FidCard {

	/**
	 * Constructor of Basic Plan
	 */
	public FidCardBasic() {
		super();
	}
	
	/**
	 * Overridden method to apply the pricing policy. Basic doesn't change the price with a coefficient.
	 * 
	 * @return the price.
	 */
	@Override
	public double applyPlan(double price) {
		return price;
	}

}

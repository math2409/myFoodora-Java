/**
 * Implementation of Lottery Fidelity Plan of the strategy pattern.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.policies;

public class FidCardLottery implements FidCard {
	private double chance; //used to change the probability for the tests
	/**
	 * Constructor of Lottery Plan
	 */
	public FidCardLottery() {
		super();
		chance = 0.05;
	}
	
	/**
	 * Overridden method to apply the pricing policy. Lottery makes the item free 5% of the time.
	 * 
	 * @return 0 if the customer is lucky
	 * @return price if the price stays the same
	 */
	@Override
	public double applyPlan(double price) {
		if (Math.random() <= chance) {
			return 0;
		} else {
			return price;
		}
	}

	/**
	 * @return the chance
	 */
	public double getChance() {
		return chance;
	}

	/**
	 * @param chance the chance to set
	 */
	public void setChance(double chance) {
		this.chance = chance;
	}
	
	

}

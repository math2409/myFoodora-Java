/**
 * Implementation of Points Fidelity Plan of the strategy pattern. This class contains methods to change the amount of points and the discount.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.policies;

public class FidCardPoints implements FidCard {
	
	private int points;
	private double discount;

	/**
	 * Constructor of Points Plan
	 */
	public FidCardPoints() {
		points = 0;
		discount = 0.1;
	}
	
	/**
	 * Overridden method to apply the pricing policy.
	 * 
	 * @return the discounted price if the customer has enough points
	 */
	@Override
	public double applyPlan(double price) {
		if (points >= 100) {
			return (1 - discount)*price;
		} else {
			return price;
		}
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	

}

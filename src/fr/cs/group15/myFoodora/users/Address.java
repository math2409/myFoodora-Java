/**
 * A class to represent the address of a User with 2 int coordinates
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.users;

public class Address {
	
	private int xCoor;
	private int yCoor;
	/**
	 * @param xCoor
	 * @param yCoor
	 */
	public Address(int xCoor, int yCoor) {
		super();
		this.xCoor = xCoor;
		this.yCoor = yCoor;
	}
	
	/**
	 * A class to calculate the distance between this Address and another in input
	 * 
	 * @param other other address
	 * @return the distance
	 */
	public double getDistance(Address other) {
		return Math.sqrt(Math.pow(this.xCoor - other.xCoor, 2) + Math.pow(this.yCoor - other.yCoor, 2));
	}

	/**
	 * @return the xCoor
	 */
	public int getxCoor() {
		return xCoor;
	}

	/**
	 * @param xCoor the xCoor to set
	 */
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	/**
	 * @return the yCoor
	 */
	public int getyCoor() {
		return yCoor;
	}

	/**
	 * @param yCoor the yCoor to set
	 */
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	@Override
	public String toString() {
		return xCoor + ", " + yCoor;
	}

	
	
}

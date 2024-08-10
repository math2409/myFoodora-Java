/**
 * A subclass of User representing the Manager object.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.users;

public class Manager extends User {
	private String surname;

	/**
	 * @param name of the manager
	 * @param surname of the manager
	 * @param username of the manager
	 * @param password of the manager
	 */
	public Manager(String name, String surname, String username, String password) {
		super(name, username, password);
		this.surname = surname;
	}
	
	
}

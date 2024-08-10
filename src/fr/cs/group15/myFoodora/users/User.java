/**
 * Superclass representing every User in the system, it contains the base informations of each user
 * (username, password, etc..."
 */

package fr.cs.group15.myFoodora.users;

public class User {
	private String name;
	private int ID;
	private String username;
	private String password;
	protected static int counter = 0;
	
	public User(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.ID = counter++;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "named " + name + ", with ID " + ID + ".";
	}

	
}

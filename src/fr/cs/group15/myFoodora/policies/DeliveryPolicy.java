/**
 * Interface used in the Strategy pattern to manage the different delivery policies. It declares the method to be overridden in the
 * policies classes.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.policies;

import java.util.ArrayList;

import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Courier;

public interface DeliveryPolicy {
	
	public ArrayList<Courier> applyDeliveryPolicy(ArrayList<Courier> list, Address address);

}

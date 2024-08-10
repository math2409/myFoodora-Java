/**
 * Implementation of the Fair Occupation Delivery Policy of the strategy pattern.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */


package fr.cs.group15.myFoodora.policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Courier;

public class FairOccupationPolicy implements Comparator<Courier>,DeliveryPolicy {
	
	/**
	 * The overriden method to apply the delivery policy.
	 * 
	 * @param list list of couriers registered in the Core
	 * @param address address to deliver the order to
	 * @return the list of available couriers sorted by number of delivered orders, higher to lower
	 */
	@Override
	public ArrayList<Courier> applyDeliveryPolicy(ArrayList<Courier> list, Address address) {
		ArrayList<Courier> onDutyCouriers = new ArrayList<Courier>();
		
		for (Courier courier : list) {
			if (courier.isOnDuty()) {
				onDutyCouriers.add(courier);
			}
		}
		
		Collections.sort(onDutyCouriers, this);
		return onDutyCouriers;
	}
	
	@Override
	public int compare(Courier c1, Courier c2) {
		return Integer.compare(c1.getCounterOrder(), c2.getCounterOrder());
	}

}

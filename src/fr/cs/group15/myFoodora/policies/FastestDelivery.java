/**
 * Implementation of the Fastest Delivery Policy of the strategy pattern.
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

public class FastestDelivery implements Comparator<Courier>,DeliveryPolicy {
	
	private Address referenceAddress;
	
	/**
	 * The overriden method to apply the delivery policy.
	 * 
	 * @param list list of couriers registered in the Core
	 * @param address address to deliver the order to
	 * @return the list of available couriers sorted by nearest to furthest
	 */
	@Override
	public ArrayList<Courier> applyDeliveryPolicy(ArrayList<Courier> list, Address address) {
		
		ArrayList<Courier> onDutyCouriers = new ArrayList<Courier>();
		referenceAddress = address;
		
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
	    if (c1.getPosition() == null || c2.getPosition() == null) {
	        throw new IllegalStateException("Courier positions are null");
	    } else if (referenceAddress == null) {
	    	throw new IllegalStateException("Reference Address is null");
	    }
		Double distance1 = c1.getPosition().getDistance(referenceAddress);
		Double distance2 = c2.getPosition().getDistance(referenceAddress);
		return Double.compare(distance1, distance2);
	}

}

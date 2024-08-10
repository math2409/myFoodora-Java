package fr.cs.group15.myFoodora.tests;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import fr.cs.group15.myFoodora.users.Address;
import fr.cs.group15.myFoodora.users.Courier;
import fr.cs.group15.myFoodora.users.Restaurant;
import fr.cs.group15.myFoodora.policies.*;

class DeliveryPolicyTest {
	
	/*
	 * Creation of a few Couriers of different position to have in a list
	 */
	ArrayList<Courier> listCourier = new ArrayList<>();
	
	Courier courier1 = new Courier("Alice", "alice123", "password1", new Address(3, 5), "Smith", "1234567890", true);
    Courier courier2 = new Courier("Bob", "bob456", "password2", new Address(7, 2), "Johnson", "0987654321", false);
    Courier courier3 = new Courier("Charlie", "charlie789", "password3", new Address(1, 8), "Williams", "1122334455", true);
    Courier courier4 = new Courier("Diana", "diana321", "password4", new Address(6, 3), "Brown", "5566778899", true);
    
    Address add1 = new Address(0,0);
    Address add2 = new Address(3,4);

	@Test
	void FastestDelivery() {
		listCourier.add(courier1);
		listCourier.add(courier2);
		listCourier.add(courier3);
		listCourier.add(courier4);
		
		DeliveryPolicy delPolicy = new FastestDelivery();
		assertEquals(delPolicy.applyDeliveryPolicy(listCourier, new Address(4,3)).size(),3);
		assertEquals(delPolicy.applyDeliveryPolicy(listCourier, new Address(4,3)).get(0).getName(),"Charlie");
	}
	
    
	@Test
	void FairOccupationDelivery() {
		courier1.setCounterOrder(3);
		courier2.setCounterOrder(1);
		courier3.setCounterOrder(0);
		courier4.setCounterOrder(4);
		
		listCourier.add(courier1);
		listCourier.add(courier2);
		listCourier.add(courier3);
		listCourier.add(courier4);

		DeliveryPolicy delPolicy = new FairOccupationPolicy();
		
		assertEquals(delPolicy.applyDeliveryPolicy(listCourier, new Address(4,3)).size(),3);
		assertEquals(delPolicy.applyDeliveryPolicy(listCourier, new Address(4,3)).get(0).getName(),"Diana");
	}
 

}

/**
 * A subclass of User representing the Courier object. This class implements a few of the methods
 * executed via the CLUI, because it was easier to implement them here and not in the Core.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.users;

import java.util.ArrayList;

import fr.cs.group15.myFoodora.System.Order;

public class Courier extends User{
	private Address position;
	private String surname;
	private String phone;
	private int counterOrder;
	private boolean OnDuty;
	private ArrayList<Order> receivedOrders;
	private Order current_order;
	
	/**
	 * @param name of courier
	 * @param username of courier
	 * @param password of courier
	 * @param position : current position of courier
	 * @param surname of courier
	 * @param phone number of courier
	 * @param OnDuty : tells if the courier can receive orders
	 */
	public Courier(String name, String username, String password, Address position, String surname, String phone,
			boolean OnDuty) {
		super(name, username, password);
		this.position = position;
		this.surname = surname;
		this.phone = phone;
		this.OnDuty = OnDuty;
		this.counterOrder = 0;
	}
	/**
	 * @param name of courier
	 * @param username of courier
	 * @param password of courier
	 * @param position : current position of courier
	 * @param surname of courier
	 * @param phone number of courier
	 */
	public Courier(String name, String username, String password, Address position, String surname, String phone) {
		super(name, username, password);
		this.position = position;
		this.surname = surname;
		this.phone = phone;
		this.counterOrder = 0;
		this.OnDuty = true;
	}
	
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 * @param position
	 */
	public Courier(String name, String surname, String username, String password, Address position) {
		super(name, username, password);
		this.position = position;
		this.surname = surname;
		this.phone = "";
		this.counterOrder = 0;
		this.OnDuty = true;
		this.receivedOrders = new ArrayList<Order>();
	}
	
	/**
	 * Method used to print every order a courier has received from the core
	 */
	public void checkOrders() {
		if (this.receivedOrders.size() !=0) {
			System.out.println("You have received these orders : ");
			for (Order order : this.receivedOrders) {
				System.out.println("Order from " + order.getRestaurant().getName() + " at "
				+ order.getRestaurant().getLocation() + ". Order ID : " + order.getID());
			}
		}
	}
	
	/**
	 * Method used by a Courier to accept a certain order he received
	 * 
	 * @param orderID ID of the order (unique)
	 * @return the list of other orders he received that are automatically denied upon acceptance of a particular order, 
	 * so that they can be treated again by the core.
	 */
	public ArrayList<Order> acceptOrder(int orderID) {
		for (Order order : receivedOrders) {
			if (order.getID() == orderID) {
				receivedOrders.remove(order);
				ArrayList<Order> refusedOrders = new ArrayList<>(receivedOrders);
				receivedOrders.clear();
				setOnDuty(false);
				order.setCourier(this);
				setCurrent_order(order);
				System.out.println("Successfully took order " + order.getID() + ". Your state has been changed to off-duty.");
				return refusedOrders;
			}
		}
		throw new IllegalArgumentException("This order isn't in your list of received orders");
	}
	
	/**
	 * Method used by a Courier to inform the system that he has delivered his order.
	 * The method then sets his state as on-duty again and updates his address.
	 */
	public void deliveredOrder() {
		if (current_order != null) {
			this.counterOrder++;
			setOnDuty(true);
			setPosition(current_order.getCustomer().getAddress());
			this.current_order = null;
		} else {
			throw new IllegalAccessError("You currently have no order to deliver.");
		}
	}
	
	/**
	 * @return the position
	 */
	public Address getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Address position) {
		this.position = position;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the OnDuty
	 */
	public boolean isOnDuty() {
		return OnDuty;
	}
	/**
	 * @param OnDuty the OnDuty to set
	 */
	public void setOnDuty(boolean OnDuty) {
		this.OnDuty = OnDuty;
	}
	/**
	 * @return the counterOrder
	 */
	public int getCounterOrder() {
		return counterOrder;
	}
	/**
	 * @param counterOrder the counterOrder to set
	 */
	public void setCounterOrder(int counterOrder) {
		this.counterOrder = counterOrder;
	}
	/**
	 * @return the receivedOrders
	 */
	public ArrayList<Order> getReceivedOrders() {
		return receivedOrders;
	}
	/**
	 * @param receivedOrders the receivedOrders to set
	 */
	public void setReceivedOrders(ArrayList<Order> receivedOrders) {
		this.receivedOrders = receivedOrders;
	}
	
	/**
	 * @return the current_order
	 */
	public Order getCurrent_order() {
		return current_order;
	}
	/**
	 * @param current_order the current_order to set
	 */
	public void setCurrent_order(Order current_order) {
		this.current_order = current_order;
	}
	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addOrder(Order o) {
		return receivedOrders.add(o);
	}
	
	
	
	
	
	
	
}

/**
 * A subclass of User representing the Customer object. This method contains 
 * many constructors to allow the system to be flexible on the parameters wanted.
 * It also contains the methods used for the Observer pattern.
 * 
 * @author Mathias Thirion
 * @author Maxime Leboeuf
 */

package fr.cs.group15.myFoodora.users;


import java.util.ArrayList;
import java.util.List;

import fr.cs.group15.myFoodora.System.Order;
import fr.cs.group15.myFoodora.policies.FidCard;
import fr.cs.group15.myFoodora.policies.FidCardBasic;
import fr.cs.group15.myFoodora.policies.FidCardLottery;
import fr.cs.group15.myFoodora.policies.FidCardPoints;
import fr.cs.group15.myFoodora.restaurantComponents.Meal;

public class Customer extends User implements Observer{
	private Address address;
	private String surname;
	private String email;
	private String phone;
	private int points;
	private boolean wantNotif;
	private FidCard fidCardPlan;
	private List<Message> inBox;
	private List<Order> receivedOrder;
	
	
	/**
	 * @param name of customer
	 * @param username of customer
	 * @param password of customer
	 * @param address of the customer
	 * @param surname of customer
	 * @param email of customer
	 * @param phone of customer
	 * @param wantNotif tells whether or not customer wants to receive notifications
	 */
	public Customer(String name, String username, String password, Address address, String surname, String email,
			String phone, boolean wantNotif) {
		super(name, username, password);
		this.address = address;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.wantNotif = wantNotif;
		this.points = 0;
		this.inBox = new ArrayList<Message>();
		this.receivedOrder = new ArrayList<Order>();
		this.fidCardPlan = new FidCardBasic();
	}

	/**
	 * @param name of customer
	 * @param username of customer
	 * @param password of customer
	 * @param address of the customer
	 * @param surname of customer
	 * @param email of customer
	 * @param phone of customer
	 */
	public Customer(String name, String username, String password, Address address, String surname, String email,
			String phone) {
		super(name, username, password);
		this.address = address;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.wantNotif = false;
		this.points = 0;
		this.inBox = new ArrayList<Message>();
		this.receivedOrder = new ArrayList<Order>();
		this.fidCardPlan = new FidCardBasic();
	}
	
	
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 * @param address
	 */
	public Customer(String name, String surname, String username, String password, Address address) {
		super(name, username, password);
		this.address = address;
		this.surname = surname;
		this.email = "";
		this.phone = "";
		this.wantNotif = false;
		this.points = 0;
		this.inBox = new ArrayList<Message>();
		this.receivedOrder = new ArrayList<Order>();
		this.fidCardPlan = new FidCardBasic();
	}

	/**
	 * Getters and setters for the customer
	 */

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the wantNotif
	 */
	public boolean isWantNotif() {
		return wantNotif;
	}

	/**
	 * @param wantNotif the wantNotif to set
	 */
	public void setWantNotif(boolean wantNotif) {
		this.wantNotif = wantNotif;
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
	 * @return the inBox
	 */
	public List<Message> getInBox() {
		return inBox;
	}

	/**
	 * @param inBox the inBox to set
	 */
	public void setInBox(List<Message> inBox) {
		this.inBox = inBox;
	}

	/**
	 * @return the receivedOrder
	 */
	public List<Order> getReceivedOrder() {
		return receivedOrder;
	}

	/**
	 * @param receivedOrder the receivedOrder to set
	 */
	public void setReceivedOrder(List<Order> receivedOrder) {
		this.receivedOrder = receivedOrder;
	}

	/**
	 * Update for the observer pattern, puts a message in the box of a registered customer
	 */
	@Override
	public void update(Restaurant restaurant, Meal mealOfTheWeek) {
		if (this.wantNotif) {
			double price = Math.round(100*(mealOfTheWeek.getPrice()*(1-restaurant.getSpecialDiscountFactor())))/100;
			String contentMessage = restaurant.getName() + " has a new special offer for " + mealOfTheWeek + " for only " + price + "€ !";
			inBox.add(new Message(contentMessage));
		}
	}

	/**
	 * @return the fidCardPlan
	 */
	public FidCard getFidCardPlan() {
		return fidCardPlan;
	}

	/**
	 * @param fidCardPlan the fidCardPlan to set
	 */
	public void setFidCardPlan(FidCard fidCardPlan) {
		this.fidCardPlan = fidCardPlan;
	}
	
	/**
	 * Sets the fidelity plan to basic.
	 */
	public void setFidCardPlanBasic() {
		FidCardBasic basic = new FidCardBasic();
		setFidCardPlan(basic);
	}
	
	/**
	 * Sets the fidelity plan to points.
	 */
	public void setFidCardPlanPoints() {
		FidCardPoints points = new FidCardPoints();
		setFidCardPlan(points);
	}
	
	/**
	 * Sets the fidelity plan to lottery.
	 */
	public void setFidCardPlanLottery() {
		FidCardLottery lottery = new FidCardLottery();
		setFidCardPlan(lottery);
	}
	
	/**
	 * 
	 * @return number of points for a points fidelity plan.
	 */
	public int getNumberPointsCustomer() {
		if (this.fidCardPlan instanceof FidCardPoints) {
			FidCardPoints fid = (FidCardPoints) this.fidCardPlan;
			return fid.getPoints();
			} else {
				return 0;
			}
	}
	
	/**
	 * 
	 * @param p number of points to add.
	 */
	public void addPointsCustomer(int p) {
		if (this.fidCardPlan instanceof FidCardPoints) {
			FidCardPoints fid = (FidCardPoints) this.fidCardPlan;
			fid.setPoints(fid.getPoints() + p);
		}
	}
	
	/**
	 * 
	 * @param order order to add
	 */
	public void addOrder(Order order) {
		receivedOrder.add(order);
	}

	@Override
	public String toString() {
		return "Customer located at " + address + ", named "+ getName() + surname + ", with ID " + getID();
	}
	
	
}

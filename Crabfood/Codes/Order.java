/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrab;

public class Order {
    private int customerID;
	private Customer customer;
	private Restaurant restaurant;
	private int orderTime;
	private int finishedCookingTime;
	private int deliveryTime;
	private int delaytime;
	private int totalTime;
	private String deliveryName;
	public Order(int customerID, Customer customer, Restaurant restaurant) {
		this.customerID = customerID;
		this.customer = customer;
		this.restaurant = restaurant;
		this.orderTime = customer.getArrivalTime();
		this.finishedCookingTime = this.orderTime + 20;
		this.deliveryTime = restaurant.getLocation().getX() + restaurant.getLocation().getY();
		this.delaytime = 0;
		this.totalTime = 20 + this.deliveryTime;
		this.deliveryName = null;
	}

	public Order() {
		this.customerID = 0;
		this.customer = null;
		this.restaurant = restaurant;
		this.orderTime = 0;
		this.finishedCookingTime = 0;
		this.deliveryTime = restaurant.getLocation().getX() + restaurant.getLocation().getY();
		this.totalTime = 0;
		this.deliveryName = null;
	}

	public void setDeliveryName(String deliveryName) {this.deliveryName = deliveryName;}

	public void setDelayTime(int delaytime){ this.delaytime = delaytime;}

	public String getDeliveryName() {return deliveryName;}
	public int getDelayTime() {return delaytime;}

	public int getCustomerID() {
		return customerID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public int getOrderTime() {
		return orderTime;
	}

	public int getFinishedCookingTime() {
		return finishedCookingTime;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public int getTotalTime() {
		return totalTime;
	}
	
	
}

    


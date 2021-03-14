/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrab;


import java.util.ArrayList;

public class Customer {
    private int arrivalTime;
	private String restaurantName;
	private ArrayList dishName;
	private String special;
	private Location startingpoint;
	
	public Customer(int arrivalTime, String restaurantName, ArrayList dishName,String special,Location startingpoint) {
		this.arrivalTime = arrivalTime;
		this.restaurantName = restaurantName;
		this.dishName = dishName;
		this.special = special;
		this.startingpoint = startingpoint;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public ArrayList getDishName() {
		return dishName;
	}

	public String getSpecial() { return special; }

	public Location getStartingpoint() {return startingpoint; }
	
	
	
}

    


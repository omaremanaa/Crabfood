/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrab;

import java.util.ArrayList;

public class Restaurant {
    private String name;
	private Location location;
	private ArrayList<Dish> dishes;
	private int numberOfDishesCooking;
	
	public Restaurant(String name, Location location, ArrayList<Dish> dishes) {
		this.name = name;
		this.location = location;
		this.dishes = dishes;
		numberOfDishesCooking = 0;
	}
	
	public String getName() {
		return name;
	}


	public Location getLocation() {
		return location;
	}



	public ArrayList<Dish> getDishes() {
		return dishes;
	}

	public void add(Dish dish) {
		dishes.add(dish);
	}
	
	public int getNumberOfDishesCooking() {
		return this.numberOfDishesCooking;
	}
	
	public void setNumberOfDishesCooking(int numberOfDishesCooking) {
		this.numberOfDishesCooking = numberOfDishesCooking;
	}
}

    


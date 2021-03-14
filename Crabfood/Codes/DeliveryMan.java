package testcrab;

import java.util.ArrayList;

public class DeliveryMan {
    private Bag<ArrayList<Dish>> test = new Bag<>();
    private int slot;
    private int deliverytime;
    private String name;


    DeliveryMan(String name){
        this.slot = test.getCurrentSize();
        this.deliverytime = 0;
        this.name = name;
    }

    DeliveryMan(){
        this.slot = test.getCurrentSize();
        this.deliverytime = 0;
        this.name = null;
    }

    public  boolean isDeliveryManAvailable(){
        if (test.isEmpty()){
            return true;
        }
        return false;
    }

/*    public static boolean isDeliveryManFull(){
        if (test.is()){
            return true;
        }
        return false;
    }*/

    public void setDeliveryTime(int deliverytime) { this.deliverytime = deliverytime; }

    public  int getDeliverytime() { return deliverytime; }

    public void setSlotFull(ArrayList<Dish> food){
        test.add(food);
    }

    public  void setSlotEmpty() { test.remove();}

    public  String getDeliveryName() { return name;}





}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class TestCrab {

    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    private static ArrayList<Order> processingOrders = new ArrayList<Order>();
    private static ArrayList<Order> finishedOrders = new ArrayList<Order>();
    private static MyQueue<Order> OrderinQueue = new MyQueue<>();
    private static MyQueue<Order> OrderinQueue2 = new MyQueue<>();
    private static DeliveryMan meow = new DeliveryMan("PewDiePie");
    private static DeliveryMan meow2 = new DeliveryMan("Brad");



    public static void main(String[] args) throws IOException {

        getCustomers("./src/Customer.txt");
        getRestaurants("./src/Input.txt");
        printMap();
        writetoReportFile("./src/reportfile.txt");
        serveCustomers();
        writeToLogFile("./src/logfile.txt");
    }

    public static void getCustomers(String filename) {

        try {
            FileReader fr = new FileReader(new File(filename));
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> dishes = new ArrayList<>();
                String location[];
                int arrivalTime = Integer.parseInt(line);
                location = br.readLine().split(" ");
                Location startingpoint = new Location (Integer.parseInt(location[0]),Integer.parseInt(location[1]));
                String restaurantName = br.readLine();
                String dishName = br.readLine();
                String dishName2 = br.readLine();
                String dishName3 = br.readLine();
                dishes.add(dishName);
                dishes.add(dishName2);
                dishes.add(dishName3);
                dishes.removeAll(Collections.singleton("None"));
                String specialrequirement = br.readLine();
                Customer customer = new Customer(arrivalTime, restaurantName, dishes,specialrequirement,startingpoint);
                customers.add(customer);
                br.readLine();
            }

            br.close();

        } catch (IOException e) {

        }
    }


   public static void getRestaurants(String filename) {

        try {
            FileReader fr = new FileReader(new File(filename));
            BufferedReader br = new BufferedReader(fr);

            String line = "";

            while ((line = br.readLine()) != null) {
                String restaurantName = line;
                String location[];

                location = br.readLine().split(" ");
                Location l1 = new Location(Integer.parseInt(location[0]), Integer.parseInt(location[1]));

                location = br.readLine().split(" ");
                Location l2 = new Location(Integer.parseInt(location[0]), Integer.parseInt(location[1]));

                location = br.readLine().split(" ");
                Location l3 = new Location(Integer.parseInt(location[0]), Integer.parseInt(location[1]));

                location =br.readLine().split(" ");
                Location l4 = new Location(Integer.parseInt(location[0]), Integer.parseInt(location[1]));

                location=br.readLine().split(" ");
                Location l5=new Location(Integer.parseInt(location[0]),Integer.parseInt(location[1]));
                ArrayList<Dish> dishes = new ArrayList<Dish>();

                for (int i = 0; i < 5; i++) {
                    String dishName = br.readLine();
                    int preparationTime = Integer.parseInt(br.readLine());
                    dishes.add(new Dish(dishName, preparationTime));
                }
                br.readLine();

                restaurants.add(new Restaurant(restaurantName, l1, dishes));
                restaurants.add(new Restaurant(restaurantName, l2, dishes));
                restaurants.add(new Restaurant(restaurantName, l3, dishes));
                restaurants.add(new Restaurant(restaurantName, l4, dishes));
                restaurants.add(new Restaurant(restaurantName, l5, dishes));
            }

            br.close();

        } catch (IOException e) {

        }
    }

    public static void printMap() {
        char[][] map = {{'0', '0', '0', '0', '0','0','0','0','0','0'},
       {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'},
        {'0', '0', '0', '0', '0','0','0','0','0','0'}};

        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant r = restaurants.get(i);
            map[r.getLocation().getX()][r.getLocation().getY()] = r.getName().charAt(0);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void serveCustomers() {
        int time = 0;
        int customerID = 1;
        System.out.println(time + ": a new day has started!");

        while (time < 100) {
            for (int i = 0; i < customers.size() + 1; i++) {
                Customer cust = getCustomerAtArrivalTime(time);

                if (cust != null) {
                    System.out.println(time + ": Customer " + customerID + " wants to order " + cust.getDishName() + " with " + cust.getSpecial() + " special requirement" + " from " + cust.getRestaurantName());

                    //Restaurant rest = getLeastBusyRestaurant(cust.getRestaurantName());
                    //rest.setNumberOfDishesCooking(rest.getNumberOfDishesCooking() + 1);
                    Restaurant rest = getNearestRestaurant(cust.getStartingpoint().getX(),cust.getStartingpoint().getY(),cust.getRestaurantName());
                    rest.setNumberOfDishesCooking(rest.getNumberOfDishesCooking() + 1);
                    System.out.println(time + ": The nearest branch of " + rest.getName() + " at (" + rest.getLocation().getX() + ", " + rest.getLocation().getY() + ") takes the order.");
                    if(trafficJam()){
                        rest = getLeastBusyRestaurant(cust.getRestaurantName());
                        rest.setNumberOfDishesCooking(rest.getNumberOfDishesCooking() + 1);
                        System.out.println(time + ": Branch of " + rest.getName() + " at (" + rest.getLocation().getX() + ", " + rest.getLocation().getY() + ") takes the order.");
                    }
                    processingOrders.add(new Order(customerID, cust, rest));
                    customers.remove(cust);
                    customerID++;
                }

            }

            for (int j = 0; j < processingOrders.size(); j++) {
                Order order = processingOrders.get(j);

                if (order != null) {
                    if (order.getFinishedCookingTime() == time) {
                        Restaurant rest = order.getRestaurant();
                        System.out.println(time + ": Branch of " + rest.getName() + " at (" + rest.getLocation().getX() + ", " + rest.getLocation().getY() + ") finished the order and delivering food to customer " + order.getCustomerID());
                        rest.setNumberOfDishesCooking(rest.getNumberOfDishesCooking() -1 );
                        if (meow.isDeliveryManAvailable()){
                            meow.setSlotFull(order.getCustomer().getDishName());
                            order.setDelayTime(0);
                            order.setDeliveryName(meow.getDeliveryName());
                            meow.setDeliveryTime(order.getDeliveryTime());
                            //System.out.println(order.getDeliveryTime());
                            //System.out.println(meow.getDeliveryName());
                            System.out.println("Deliveryhero " + meow.getDeliveryName() + " will deliver your order " + order.getCustomerID());
                        }

                        else if (meow2.isDeliveryManAvailable()){
                            order.setDelayTime(0);
                            order.setDeliveryName(meow2.getDeliveryName());
                            meow2.setSlotFull(order.getCustomer().getDishName());
                            meow2.setDeliveryTime(order.getDeliveryTime());
                            //System.out.println(meow2.getDeliveryName());
                            System.out.println("Deliveryhero " + meow2.getDeliveryName() + " will deliver your order " + order.getCustomerID());
                        }
                        else if (!meow.isDeliveryManAvailable()){
                            order.setDelayTime(meow.getDeliverytime());
                            order.setDeliveryName(meow.getDeliveryName());
                            OrderinQueue.enqueue(order);
                            System.out.println("Sorry customer " + order.getCustomerID() + " your order is delayed due to limited deliveryboy.");
                        }

                        else if(!meow2.isDeliveryManAvailable()){
                            order.setDelayTime((meow2.getDeliverytime()));
                            order.setDeliveryName(meow2.getDeliveryName());
                            OrderinQueue2.enqueue(order);
                            System.out.println("Sorry customer " + order.getCustomerID() + " your order is delayed due to limited deliveryboy.");
                        }

                        }



                    if ((order.getFinishedCookingTime() + order.getDeliveryTime() + order.getDelayTime() ) == time) {
                        Restaurant rest = order.getRestaurant();
                        System.out.println(time + ": The food has been delivered to customer " + order.getCustomerID() + " by DeliveryHero " + order.getDeliveryName());
                        if (order.getDeliveryName().equals(meow.getDeliveryName())){
                            meow.setSlotEmpty();
                            if (!(OrderinQueue.isEmpty())){
                                System.out.println("The next delayed order of " + OrderinQueue.getElement(0).getCustomerID() + " is delivering..");
                                OrderinQueue.dequeue();
                            }
                        }
                         else if (order.getDeliveryName().equals(meow2.getDeliveryName())) {
                            meow2.setSlotEmpty();
                            if (!(OrderinQueue2.isEmpty())){
                                System.out.println("The next delayed order of " + OrderinQueue2.getElement(0).getCustomerID() + " is delivering..");
                                OrderinQueue2.dequeue();
                            }
                        }

                        rest.setNumberOfDishesCooking(rest.getNumberOfDishesCooking() - 1);
                        finishedOrders.add(order);
                        processingOrders.remove(order);
                    }
                } else {
                    break;
                }
            }
            if (processingOrders.size() == 1) {
                if (time == (processingOrders.get(0).getFinishedCookingTime() + processingOrders.get(0).getDeliveryTime() + processingOrders.get(0).getDelayTime())) {
                    System.out.println(time + ": The food has been delivered to customer " + processingOrders.get(0).getCustomerID() + " by DeliveryHero " + processingOrders.get(0).getDeliveryName());
                    if (processingOrders.get(0).getDeliveryName().equals(meow.getDeliveryName())){
                        meow.setSlotEmpty();
                    }
                    else if (processingOrders.get(0).getDeliveryName().equals(meow2.getDeliveryName())){
                        meow2.setSlotEmpty();
                    }
                    finishedOrders.add(processingOrders.get(0));
                    processingOrders.remove(processingOrders.get(0));
                }
            }

            time++;
        }
    }

    public static Customer getCustomerAtArrivalTime(int arrivalTime) {

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getArrivalTime() == arrivalTime) {
                return customers.get(i);
            }
        }

        return null;
    }
    /**
     * when traffic jam happen in a branch, choose another restaurant
     * which is least busy restaurant
     * @param restaurantName
     * @return
     */

    public static Restaurant getLeastBusyRestaurant(String restaurantName) {
        Restaurant tempRestaurants[] = new Restaurant[5];
        int index = 0;
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getName().equals(restaurantName)) {
                tempRestaurants[index] = restaurants.get(i);
                index++;
            }
        }

        for (int i = 0; i < tempRestaurants.length - 1; i++) {
            for (int j = i + 1; j < tempRestaurants.length; j++) {
                if (tempRestaurants[i].getNumberOfDishesCooking() > tempRestaurants[j].getNumberOfDishesCooking()) {
                    Restaurant temp = tempRestaurants[i];
                    tempRestaurants[i] = tempRestaurants[j];
                    tempRestaurants[j] = temp;
                }
            }
        }

        return tempRestaurants[0];
    }

    public boolean cancerOrder(){
        Random nu=new Random();
        int number=nu.nextInt(2);
        if(number==0){
            return false;
        }else{
            System.out.println("WARNING: Traffic jam is happening!! Change to the another branch");
            return true;
        }}

    public static Restaurant getNearestRestaurant(int x, int y,String restaurantname){
        double mindistance = 100;
        int current_nearestrestaurant = 0;
        double distance;
        for (int i = 0; i < restaurants.size(); i++){
            if (restaurantname.equals(restaurants.get(i).getName())){
                distance = Math.sqrt((restaurants.get(i).getLocation().getX() - x)*(restaurants.get(i).getLocation().getX() - x) + (restaurants.get(i).getLocation().getY() - y)*(restaurants.get(i).getLocation().getY() - y) );
                if (distance < mindistance){
                    mindistance = distance;
                    current_nearestrestaurant = i;
                }
            }
        }
        return restaurants.get(current_nearestrestaurant);
    }

    /**
     * this method is charge if happen a traffic jam
     * @return boolean
     */
    public static boolean trafficJam(){
        Random nu=new Random();
        int number=nu.nextInt(2);
        if(number==0){
            return false;
        }else{
            System.out.println("WARNING : Traffic jam is happening!! Changing to the another branch..");
            return true;
        }
    }

    public static void writeToLogFile(String filename) {

        try {
            FileWriter fw = new FileWriter(new File(filename));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("|Customer |Arrival |Order Time |Finished Cooking Time | Delivery Time| Total Time|\n");
            for (int i = 1; i < finishedOrders.size() + 1; i++) {
                for (int j = 0; j < finishedOrders.size(); j++) {
                    Order o = finishedOrders.get(j);

                    if (o.getCustomerID() == i) {
                        bw.write(String.format("|%9s|%8s|%11s|%22s|%14s|%11s| \n", o.getCustomerID(), o.getCustomer().getArrivalTime(), o.getOrderTime(), o.getFinishedCookingTime(),
                                o.getDeliveryTime(), o.getTotalTime()));
                        break;
                    }
                }

            }

            bw.close();

        } catch (IOException e) {

        }
    }

    public static void writetoReportFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < restaurants.size(); i+=5){
            writer.write("|" +restaurants.get(i).getName() +"|" + "\n");
            for (int j = 0; j< customers.size(); j++){
                if (customers.get(j).getRestaurantName().equals(restaurants.get(i).getName())){
                    writer.write("Customer " + j + "\n");
                    writer.write("The Order Time => ");
                    writer.write(String.valueOf(customers.get(j).getArrivalTime()));
                    writer.write("\n");
                    writer.write("The dish ordered => ");
                    writer.write(String.valueOf(customers.get(j).getDishName()));
                    writer.write("\n");
                }
            }
        }
        writer.close();

    }

}

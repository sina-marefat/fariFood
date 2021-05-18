package ir.ac.kntu;

import ir.ac.kntu.inputIo.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Admin {
    private String userName;
    private String password;
    private ArrayList<Costumer> costumers;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Address> addresses;
    private ArrayList<Food> foods;
    private ArrayList<Order> orders;


    public Admin(String userName, String password) {
        foods = new ArrayList<>();
        costumers = new ArrayList<>();
        restaurants = new ArrayList<>();
        deliveries = new ArrayList<>();
        addresses = new ArrayList<>();
        orders = new ArrayList<>();
        this.password = password;
        this.userName = userName;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void addFood(Food food){
        foods.add(food);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Order> getOrders() {
        return new ArrayList<Order>(orders);
    }

    public ArrayList<Costumer> getCostumers() {
        return new ArrayList<Costumer>(costumers);
    }

    public ArrayList<Restaurant> getRestaurants() {
        return new ArrayList<Restaurant>(restaurants);
    }

    public ArrayList<Delivery> getDeliveries() {
        return new ArrayList<Delivery>(deliveries);
    }

    public ArrayList<Address> getAddresses() {
        return new ArrayList<Address>(addresses);
    }

    public ArrayList<Food> getFoods() {
        return new ArrayList<Food>(foods);
    }

    public void addCostumer() {
        Costumer costumer = InputStream.getInstance().newCostumer();
        costumers.add(costumer);
    }

    public void removeRestaurant() {
        InputStream.getInstance().removeRestaurant(this);
    }

    public void removeDeliveries() {
        InputStream.getInstance().removeDeliveries(this);
    }

    public void removeCostumer() {
        InputStream.getInstance().removeCostumer(this);
    }

    public void addRestaurant() throws ParseException {
        Restaurant restaurant = InputStream.getInstance().addRestaurant(this);
        restaurants.add(restaurant);
    }

    public void addDeliveries() {
        Delivery delivery = InputStream.getInstance().addDeliveries(this);
        deliveries.add(delivery);
    }

    public void changeCustomer() {
        InputStream.getInstance().costumerChange(this);
    }

    public void handleDeliveryRmv() {
        InputStream.getInstance().removeDeliverFromRestaurant(this);
    }

    public void handleDeliveryAdd() {
        InputStream.getInstance().addDeliverToRestaurant(this);
    }

    public void handleDeliveryView() {
        InputStream.getInstance().viewDeliveryRestaurants(this);
    }

    public void handleDeliveryOrdersView() {
        InputStream.getInstance().viewDeliveryOrders(this);
    }

    public void handleDeliveryComments() {
        InputStream.getInstance().viewDeliveryComments(this);
    }

}


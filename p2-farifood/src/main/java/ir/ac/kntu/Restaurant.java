package ir.ac.kntu;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Restaurant {
    private String name;
    private Date dateStart;
    private Calendar restaurantCalender;
    private Date dateClose;
    private RestaurantPrice restaurantPrice;
    private Address address;
    private int rating = 5;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Comment> comments;
    private ArrayList<Food> foods;
    private ArrayList<Order> orders;


    public Restaurant(String name, Date dateStart, Date dateClose, RestaurantPrice restaurantPrice, Address address, ArrayList<Food> foods, ArrayList<Delivery> deliveries) {
        setName(name);
        setDateStart(dateStart);
        restaurantCalender = Calendar.getInstance();
        setDateClose(dateClose);
        setRestaurantPrice(restaurantPrice);
        setAddress(address);
        setDeliveries(deliveries);
        comments = new ArrayList<>();
        setFoods(foods);
        deliveryHandle();
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public RestaurantPrice getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(RestaurantPrice restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }


    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void addDeliveries(Delivery delivery) {
        deliveries.add(delivery);
        delivery.addRestaurant(this);
    }

    public Calendar getRestaurantCalender() {
        return restaurantCalender;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment cm) {
        comments.add(cm);
        rating += cm.getRate();
    }

    public void setRestaurantCalender() {
        restaurantCalender.setTime(dateStart);
        restaurantCalender.setTime(dateClose);
        restaurantCalender.add(Calendar.DATE, 1);
    }

    public void addFood(Food food) {
        if (!foods.contains(food)) {
            foods.add(food);
        } else {
            System.out.println("this food is already in menu");
        }
    }

    public void deliveryHandle() {
        for (Delivery delivery : deliveries) {
            delivery.addRestaurant(this);
        }
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}


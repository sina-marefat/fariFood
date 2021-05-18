package ir.ac.kntu;

import java.util.ArrayList;

public class Delivery {
    private DeliveryVehicle deliveryVehicle;
    private DeliveySalary deliverySalary;
    private double salary;
    private ArrayList<DeliveryAvailableDays> deliveryAvailbleDays;
    private ArrayList<Restaurant> restaurants;
    private Boolean isAvailable;
    private ArrayList<Order> orders;
    private ArrayList<Comment> comments;
    private int rating;

    public Delivery(DeliveryVehicle deliveryVehicle, DeliveySalary deliveySalary, double salary, ArrayList<DeliveryAvailableDays> deliveryAvailableDays) {
        setDeliveryVehicle(deliveryVehicle);
        setDeliverySalary(deliveySalary);
        setSalary(salary);
        setDeliveryAvailbleDays(deliveryAvailableDays);
        restaurants = new ArrayList<Restaurant>();
        isAvailable = true;
        orders = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        rating += comment.getRate();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setDeliveryAvailbleDays(ArrayList<DeliveryAvailableDays> deliveryAvailableDays) {
        this.deliveryAvailbleDays = deliveryAvailableDays;
    }

    public DeliveryVehicle getDeliveryVehicle() {
        return deliveryVehicle;
    }

    public void setDeliveryVehicle(DeliveryVehicle deliveryVehicle) {
        this.deliveryVehicle = deliveryVehicle;
    }

    public DeliveySalary getDeliverySalary() {
        return deliverySalary;
    }

    public void setDeliverySalary(DeliveySalary deliveySalary) {
        this.deliverySalary = deliveySalary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<DeliveryAvailableDays> getDeliveyAvailbleDays() {
        return deliveryAvailbleDays;
    }

    public void addAvailbleDays(DeliveryAvailableDays deliveryAvailableDays) {
        this.deliveryAvailbleDays.add(deliveryAvailableDays);
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        if (restaurants.size() <= 2) {
            restaurants.add(restaurant);
        } else {
            System.out.println("this delivery's restaurant is full..!");
        }
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryVehicle=" + deliveryVehicle +
                ", deliveySalary=" + deliverySalary +
                ", salary=" + salary +
                ", deliveyAvailbleDays=" + deliveryAvailbleDays +
                ", restaurants=" + restaurants +
                '}';
    }

    public Boolean getAvailble() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}

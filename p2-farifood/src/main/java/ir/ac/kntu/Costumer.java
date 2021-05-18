package ir.ac.kntu;

import java.util.ArrayList;

public class Costumer {

    private String phoneNumber;
    private Address address;
    private ArrayList<Order> orders;
    private ArrayList<Comment> comments;

    public Costumer(String phoneNumber, Address address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        orders = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Costumer() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "phoneNumber='" + phoneNumber + '\'' + address +
                '}';
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}

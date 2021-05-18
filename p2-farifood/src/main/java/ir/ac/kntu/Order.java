package ir.ac.kntu;

import java.util.ArrayList;

public class Order {
    private ArrayList<Food> foods;
    private Delivery delivery;
    private double price;
    private Restaurant restaurant;
    private Costumer costumer;

    public Order(ArrayList<Food> foods, Delivery delivery, Restaurant restaurant, Costumer costumer) {
        setFoods(foods);
        setDelivery(delivery);
        setRestaurant(restaurant);
        setPrice();
        setCostumer(costumer);
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        int price = 0;
        for (Food food : foods) {
            price += food.getPrice();
        }
        this.price = price;
    }

    public void changeDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(Food food) {
        if (foods.contains(food)) {
            foods.remove(food);
        } else {
            System.out.println("you didnt have this food in your order...!");
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "foods=" + foods +
                ", delivery=" + delivery +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}

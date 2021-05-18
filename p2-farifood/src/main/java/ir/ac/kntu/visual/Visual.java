package ir.ac.kntu.visual;

import ir.ac.kntu.*;

import java.text.ParseException;
import java.util.ArrayList;

public class Visual {

    private Visual() {
    }

    public static void printDeliveries(Admin admin) {
        if (admin.getDeliveries().size() == 0) {
            System.out.println("You dont Have any Existing Delivery define one..!");
        } else {
            for (int i = 0; i < admin.getDeliveries().size(); i++) {
                System.out.println(i + "." + admin.getDeliveries().get(i));
            }
        }
    }

    public static void printRestaurants(Admin admin) {
        if (admin.getRestaurants().size() == 0) {
            System.out.println("No any Restaurants");
        } else {
            for (int i = 0; i < admin.getRestaurants().size(); i++) {
                System.out.println(i + "." + admin.getRestaurants().get(i));
            }
        }
    }

    public static void printOrdered(ArrayList<Restaurant> restaurants) {
        if (restaurants.size() == 0) {
            System.out.println("NO any existing Restaurants..!");
        } else {
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.println(i + "." + restaurants.get(i).getName());
            }
        }
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println();
        }
    }

    public static void printDeliveryRestaurant(Delivery delivery) {
        if (delivery.getRestaurants().size() == 0) {
            System.out.println("no any restaurant");
        } else {
            for (int i = 0; i < delivery.getRestaurants().size(); i++) {
                System.out.println(i + "." + delivery.getRestaurants().get(i));
            }
        }
    }

    public static void printDeliveryOrders(Delivery delivery) {
        if (delivery.getOrders().size() == 0) {
            System.out.println("no any Orders");
        } else {
            for (int i = 0; i < delivery.getOrders().size(); i++) {
                System.out.println(i + "." + delivery.getOrders().get(i));
            }
        }
    }

    public static void printDeliveryComments(Delivery delivery) {
        if (delivery.getComments().size() == 0) {
            System.out.println("no comments yet");
        } else {
            for (int i = 0; i < delivery.getComments().size(); i++) {
                System.out.println(i + "." + delivery.getComments().get(i));
            }
        }
    }

    public static boolean printCustomers(Admin admin) throws ParseException {
        if (admin.getCostumers().size() == 0) {
            System.out.println("No any defined Customer");
            Menu.loginMenu(admin);
            return false;
        } else {
            for (int i = 0; i < admin.getCostumers().size(); i++) {
                System.out.println(i + "." + admin.getCostumers().get(i));
            }
            return true;
        }
    }

    public static void printMenu(Restaurant restaurant, Admin admin) throws ParseException {
        if (restaurant.getFoods().size() == 0) {
            System.out.println("no any food..!");
            Menu.orderMenu(admin);
        }
        System.out.println("which food you want to choose ?");
        for (int i = 0; i < restaurant.getFoods().size(); i++) {
            System.out.println(i + "." + restaurant.getFoods().get(i).getName() + "-" + "price :" + restaurant.getFoods().get(i).getPrice() + "toman");
        }
    }

    public static void printRestaurantDeliveries(Restaurant restaurant, Admin admin) throws ParseException {
        if (restaurant.getDeliveries().size() == 0) {
            System.out.println("No deliveries.please define 1 or choose another restaurant");
            Menu.orderMenu(admin);
        }
        int j = 0;
        for (int i = 0; i < restaurant.getDeliveries().size(); i++) {
            if (restaurant.getDeliveries().get(i).getAvailble()) {
                System.out.println(i + "." + restaurant.getDeliveries().get(i));
                j++;
            }
        }
        if (j == 0) {
            Menu.orderMenu(admin);
        }
    }

    public static void printUserComments(ArrayList<Comment> comments) {
        if (comments == null) {
            System.out.println("NO COMMENTS");
        } else {
            for (int i = 0; i < comments.size(); i++) {
                System.out.println(i + "." + comments.get(i));
            }
        }
    }


    public static void printUserOrders(ArrayList<Order> orders) {
        if (orders == null) {
            System.out.println("NO COMMENTS");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println(i + "." + orders.get(i));
            }
        }
    }

    public static void printAllOrders(Admin admin) {
        if (admin.getOrders().size() == 0) {
            System.out.println("NO ANY ORDERS");
        } else {
            for (int i = 0; i < admin.getOrders().size(); i++) {
                System.out.println(i + "." + admin.getOrders().get(i));
            }
        }
    }

    public static void foodCommentHistory(Food food) {
        if (food.getComments().size() == 0) {
            System.out.println("NO COMMENTS");
        } else {
            for (int i = 0; i < food.getComments().size(); i++) {
                System.out.println(i + "." + food.getComments().get(i));
            }
        }
    }

    public static void printAllFoods(Admin admin) {
        if (admin.getFoods().size() == 0) {
            System.out.println("NO ANY FOOD");
        } else {
            for (int i = 0; i < admin.getFoods().size(); i++) {
                System.out.println(i + "." + admin.getFoods().get(i));
            }
        }
    }
}

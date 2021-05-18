package ir.ac.kntu.inputIo;

import ir.ac.kntu.Admin;
import ir.ac.kntu.Restaurant;

import java.util.ArrayList;

public class Sorting {

    private static Sorting instance = new Sorting();

    public static Sorting getInstance() {
        return instance;
    }

    private Sorting() {
    }

    public ArrayList<Restaurant> orderByRate(Admin admin) {
        ArrayList<Restaurant> orderedRestaurants = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>(admin.getRestaurants());
        if (restaurants.size() == 0) {
            System.out.println("NO RESTAURANT");
            return restaurants;
        }
        while (restaurants.size() != 0) {
            Restaurant restaurantMax = restaurants.get(0);
            int j = 0;
            for (int i = 0; i < restaurants.size(); i++) {
                if (restaurants.get(i).getRating() >= restaurantMax.getRating()) {
                    restaurantMax = admin.getRestaurants().get(i);
                    j = i;
                }
                orderedRestaurants.add(restaurantMax);
                restaurants.remove(j);
            }
        }
        return orderedRestaurants;
    }


    public ArrayList<Restaurant> orderByComments(Admin admin) {
        ArrayList<Restaurant> orderedByComments = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>(admin.getRestaurants());
        if (restaurants.size() == 0) {
            System.out.println("NO RESTAURANT");
            return restaurants;
        }
        while (restaurants.size() != 0) {
            Restaurant restaurantMin = restaurants.get(0);
            int j = 0;
            for (int i = 0; i < restaurants.size(); i++) {
                if (restaurants.get(i).getComments().size() >= restaurantMin.getComments().size()) {
                    restaurantMin = admin.getRestaurants().get(i);
                    j = i;
                }
                orderedByComments.add(restaurantMin);
                restaurants.remove(j);
            }
        }
        return orderedByComments;
    }

    public ArrayList<Restaurant> orderByAverage(Admin admin) {
        ArrayList<Restaurant> ordered = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>(admin.getRestaurants());
        if (restaurants.size() == 0) {
            System.out.println("NO RESTAURANT");
            return restaurants;
        }
        while (restaurants.size() != 0) {
            Restaurant restaurantMin = restaurants.get(0);
            int j = 0;
            for (int i = 0; i < restaurants.size(); i++) {
                if (restaurants.get(i).getOrders().size() >= restaurantMin.getOrders().size()) {
                    restaurantMin = admin.getRestaurants().get(i);
                    j = i;
                }
            }
            ordered.add(restaurantMin);
            restaurants.remove(j);
        }
        return ordered;
    }
}

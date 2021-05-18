package ir.ac.kntu.inputIo;

import ir.ac.kntu.*;
import ir.ac.kntu.visual.Menu;
import ir.ac.kntu.visual.Visual;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class InputFacilities {

    private static InputFacilities instance = new InputFacilities();

    private InputFacilities() {
    }

    public static InputFacilities getInstance() {
        return instance;
    }

    public ArrayList<Comment> getUserComments(Admin admin) throws ParseException {
        Visual.printCustomers(admin);
        System.out.println("-------------");
        System.out.println("choose your customer :");
        int ch = ScannerWrapper.getInstance().nextInt();
        System.out.println("-----------------");
        ArrayList<Comment> comments = null;
        if (ch >= admin.getCostumers().size()) {
            System.out.println("invalid input try again");
            Menu.facilitiesMenu(admin);
        } else {
            comments = admin.getCostumers().get(ch).getComments();
        }
        return comments;
    }

    public ArrayList<Order> getUserOrders(Admin admin) throws ParseException {
        Visual.printCustomers(admin);
        System.out.println("-------------");
        System.out.println("choose your customer :");
        int ch = ScannerWrapper.getInstance().nextInt();
        System.out.println("-----------------");
        ArrayList<Order> orders = null;
        if (ch >= admin.getCostumers().size()) {
            System.out.println("invalid input try again");
            Menu.facilitiesMenu(admin);
        } else {
            orders = admin.getCostumers().get(ch).getOrders();
        }
        return orders;
    }

    public ArrayList<Comment> getRestaurantComment(Admin admin) throws ParseException {
        Visual.printRestaurants(admin);
        System.out.println("---------------");
        System.out.print("please choose your desired restaurant choose(-1) to go back: ");
        int ch = ScannerWrapper.getInstance().nextInt();
        ArrayList<Comment> comments = null;
        if (ch >= admin.getRestaurants().size()) {
            System.out.println("invalid input");
            Menu.facilitiesMenu(admin);
        } else {
            comments = admin.getRestaurants().get(ch).getComments();
        }
        return comments;
    }

    public void deliveryHandleFacility(Admin admin) {
        Visual.printAllOrders(admin);
        System.out.println("----------------------");
        System.out.println("PLEASE CHOOSE ORDER TO CHANGE : ");
    }

    public Order chooseControlOrdersAndComments(Admin admin) throws ParseException {
        Visual.printAllOrders(admin);
        System.out.println("CHOOSE ORDER TO CHANGE choose(-1) to go back: ");
        int ch = ScannerWrapper.getInstance().nextInt();
        Order order = null;
        if (ch >= admin.getOrders().size() || ch < 0) {
            System.out.println("INVALID INPUT");
            Menu.facilitiesMenu(admin);
        } else {
            order = admin.getOrders().get(ch);
        }
        return order;
    }


    public void controlOrdersAndComments(Order order, Admin admin) throws ParseException {
        if (order.getDelivery() == null) {
            System.out.println("this order is pending");

        } else {
            System.out.println("Order is ready to be sent");
            System.out.println("Order is set?");
            System.out.println("1.YES\n2.NO");
            int ch = ScannerWrapper.getInstance().nextInt();
            addComment(ch, admin, order);
        }
    }

    public void addComment(int ch, Admin admin, Order order) throws ParseException {
        if (ch == 2) {
            Menu.controlMenu(admin);
        }
        System.out.println("add comment");
        System.out.println("1.add comment for restaurant");
        System.out.println("2.add comment for food");
        System.out.println("3.add comment for delivery");
        System.out.println("4.main menu");
        handleComments(ch, admin, order);
    }

    public void handleComments(int choose, Admin admin, Order order) throws ParseException {
        System.out.println("--------------");
        System.out.print("Choose your Option : ");
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                Comment restaurantComment = makeComment(order);
                order.getRestaurant().addComment(restaurantComment);
                order.getCostumer().addComment(restaurantComment);
                break;
            case 2:
                for (int i = 0; i < order.getFoods().size(); i++) {
                    System.out.print(order.getFoods().get(i).getName() + ":");
                    Comment comment = makeComment(order);
                    order.getFoods().get(i).addComment(comment);
                    order.getCostumer().addComment(comment);
                }
                break;
            case 3:
                Comment deliveryComment = makeComment(order);
                order.getDelivery().addComment(deliveryComment);
                order.getCostumer().addComment(deliveryComment);
                break;
            case 4:
                Menu.loginMenu(admin);
                break;
            default:
                addComment(choose, admin, order);
                break;

        }
        Menu.loginMenu(admin);
    }

    public Comment makeComment(Order order) {
        System.out.print("enter your comment ! :");
        String comment = ScannerWrapper.getInstance().nextLine();
        System.out.println("---------------");
        System.out.println("enter your rate from 1 to 5 :");
        int rate = ScannerWrapper.getInstance().nextInt();
        Comment cm = new Comment(comment, rate, order.getCostumer());
        return cm;
    }

    public Food addNewFood(Admin admin) {
        System.out.print("food name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        return new Food(name, 0, 0);
    }

    public void handleFoodRestaurant(Food food, Admin admin) {
        System.out.println("do you want to add food to any restaurant?");
        System.out.println("1.YES\n2.NO");
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch == 2) {
            admin.addFood(food);
        } else if (ch == 1) {
            admin.addFood(food);
            Visual.printRestaurants(admin);
            System.out.println("-----------");
            System.out.println("CHOOSE RESTAURANT ADD : ");
            int choose = ScannerWrapper.getInstance().nextInt();
            System.out.println("enter food price :");
            double price = ScannerWrapper.getInstance().nextDouble();
            System.out.println("enter time to make ");
            double timeToMake = ScannerWrapper.getInstance().nextDouble();

        }
    }

    public void checkDeliveryAvailable(Order order, Admin admin) throws ParseException {
        printDelivery(order);
        System.out.println("do you want to add or change delivery ?");
        System.out.println("1.Yes\n2.No");
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                Visual.printRestaurantDeliveries(order.getRestaurant(), admin);
                order.setDelivery(chooseDelivery(order, admin));
                break;
            default:
                Menu.loginMenu(admin);
                break;
        }
    }

    public Delivery chooseDelivery(Order order, Admin admin) throws ParseException {
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch >= order.getRestaurant().getDeliveries().size() || ch < 0) {
            System.out.println("wrong Input");
            Menu.facilitiesMenu(admin);
        }
        return order.getRestaurant().getDeliveries().get(ch);
    }

    public boolean printDelivery(Order order) {
        if (order.getDelivery() == null) {
            System.out.println("THIS ORDER WAS PENDING");
            return false;
        } else {
            System.out.println("now on delivery : " + order.getDelivery());
            return true;
        }
    }

    public void foodCommentChoose(Admin admin) throws ParseException {
        Visual.printAllFoods(admin);
        System.out.println("-----------");
        System.out.print("CHOOSE YOUR FOOD choose(-1) to go back:");
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch >= admin.getFoods().size() || ch < 0) {
            System.out.println("invalid input");
            Menu.facilitiesMenu(admin);
        }
        Visual.foodCommentHistory(admin.getFoods().get(ch));
    }

    public void setOrderByRestaurant(Restaurant restaurant, Admin admin) throws ParseException {
        Visual.printCustomers(admin);
        System.out.println("--------------");
        System.out.println("choose user to set order choose(-1) to go back");
        System.out.print("user : ");
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch >= admin.getCostumers().size() || ch < 0) {
            System.out.println("invalid input");
            Menu.facilitiesMenu(admin);
        }
        Costumer costumer = admin.getCostumers().get(ch);
        ArrayList<Food> foods = InputOrderIo.getInstance().chosenFoods(restaurant);
        System.out.println("Choose deliveries ...");
        System.out.println("---------------");
        System.out.println("-----Available deliveries-----");
        Visual.printRestaurantDeliveries(restaurant, admin);
        Delivery delivery = InputOrderIo.getInstance().chooseDelivery(restaurant, admin);
        delivery.setAvailable(false);
        Order order = new Order(foods, delivery, restaurant, costumer);
        InputOrderIo.getInstance().handleOrders(order, admin);
    }

    public void printAndChooseThreeBestRestaurants(Admin admin) throws ParseException {
        ArrayList<Restaurant> restaurants = Sorting.getInstance().orderByRate(admin);
        for (int i = 0; i < 3; i++) {
            System.out.println(i + "." + restaurants.get(i));
        }
        System.out.println("--------------------------");
        System.out.println("CHOOSE YOUR RESTAURANT choose(-1) to go back: ");
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch >= 3 || ch < 0) {
            System.out.println("invalid input");
            Menu.facilitiesMenu(admin);
        }
        setOrderByRestaurant(restaurants.get(ch), admin);
    }

    public void searchRestaurantByName(Admin admin) throws ParseException {
        System.out.print("ENTER NAME : ");
        String name = ScannerWrapper.getInstance().nextLine();
        Restaurant restaurant = null;
        for (int i = 0; i < admin.getRestaurants().size(); i++) {
            if (admin.getRestaurants().get(i).getName().equals(name)) {
                restaurant = admin.getRestaurants().get(i);
            }
        }
        if (restaurant == null) {
            System.out.println("NOTHING FOUND");
        } else {
            setOrderByRestaurant(restaurant, admin);
        }
    }

    public void filterByRestaurantType(Admin admin) throws ParseException {
        RestaurantPrice[] restaurantPrices = RestaurantPrice.values();
        for (int i = 0; i < restaurantPrices.length; i++) {
            System.out.println(i + "." + restaurantPrices[i]);
        }
        System.out.println("Choose :");
        int ch = ScannerWrapper.getInstance().nextInt();
        RestaurantPrice restaurantPrice;
        if (ch >= 3 || ch < 0) {
            System.out.println("invalid input");
            Menu.facilitiesMenu(admin);
        } else {
            restaurantPrice = restaurantPrices[ch];
            chooseByRestaurantType(restaurantPrice, admin);
        }
    }

    public void chooseByRestaurantType(RestaurantPrice restaurantPrice, Admin admin) throws ParseException {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (int i = 0; i < admin.getRestaurants().size(); i++) {
            if (admin.getRestaurants().get(i).getRestaurantPrice() == restaurantPrice) {
                restaurants.add(admin.getRestaurants().get(i));
            }
        }
        Visual.printOrdered(restaurants);
        System.out.print("CHOOSE ONE RESTAURANT :");
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch >= restaurants.size() || ch < 0) {
            System.out.println("invalid input");
            filterByRestaurantType(admin);
        }
        Restaurant restaurant = restaurants.get(ch);
        setOrderByRestaurant(restaurant, admin);
    }

    public void searchByFood(Admin admin) throws ParseException {
        Visual.printAllFoods(admin);
        System.out.println("--------------");
        System.out.println("food  : ");
        int ch = ScannerWrapper.getInstance().nextInt();
        Food food = null;
        if (ch >= admin.getFoods().size() || ch < 0) {
            System.out.println("invalid input");
        } else {
            food = admin.getFoods().get(ch);
            searchForFoods(admin, food);
        }
    }

    public void searchForFoods(Admin admin, Food food) throws ParseException {
        ArrayList<Restaurant> unsorted = new ArrayList<>();
        for (int i = 0; i < admin.getRestaurants().size(); i++) {
            if (admin.getRestaurants().get(i).getFoods().contains(food)) {
                unsorted.add(admin.getRestaurants().get(i));
            }
        }
        ArrayList<Restaurant> sorted = new ArrayList<>();
        if (unsorted.size() == 0) {
            System.out.println("NO RESTAURANT");
        }
        while (unsorted.size() != 0) {
            Restaurant restaurantMax = unsorted.get(0);
            int j = 0;
            for (int i = 0; i < unsorted.size(); i++) {
                if (unsorted.get(i).getRating() >= restaurantMax.getRating()) {
                    restaurantMax = admin.getRestaurants().get(i);
                    j = i;
                }
                sorted.add(restaurantMax);
                unsorted.remove(j);
            }
        }
        Visual.printOrdered(sorted);
        System.out.println("RESTAURANT :");
        int chooseRes = ScannerWrapper.getInstance().nextInt();
        if (chooseRes >= sorted.size() || chooseRes < 0) {
            System.out.println("invalid Input");
            Menu.facilitiesMenu(admin);
        } else {
            setOrderByRestaurant(sorted.get(chooseRes), admin);
        }
    }

}

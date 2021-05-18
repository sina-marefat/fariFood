package ir.ac.kntu.inputIo;

import ir.ac.kntu.*;
import ir.ac.kntu.visual.Menu;
import ir.ac.kntu.visual.Visual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class InputOrderIo {

    private static InputOrderIo instance = new InputOrderIo();

    private ArrayList<Restaurant> workingOrder = null;

    private Costumer workingCostumer = null;

    private Restaurant workingRestaurant = null;


    private InputOrderIo() {
    }

    public static InputOrderIo getInstance() {
        return instance;
    }

    public void setWorkingRestaurant(Restaurant workingRestaurant) {
        this.workingRestaurant = workingRestaurant;
    }


    public Restaurant getWorkingRestaurant() {
        return workingRestaurant;
    }

    public ArrayList<Restaurant> getWorkingOrder() {
        return workingOrder;
    }

    public void setWorkingOrder(ArrayList<Restaurant> workingOrder) {
        this.workingOrder = workingOrder;
    }

    public Costumer getWorkingCostumer() {
        return workingCostumer;
    }

    public void setWorkingCostumer(Costumer workingCostumer) {
        this.workingCostumer = workingCostumer;
    }

    public void setCustomer(Admin admin) throws ParseException {
        boolean check = Visual.printCustomers(admin);
        if (!check) {
            Menu.loginMenu(admin);
        } else {
            System.out.println("customer : ");
            int ch = ScannerWrapper.getInstance().nextInt();
            this.workingCostumer = admin.getCostumers().get(ch);
        }
    }

    public void setOrder(Admin admin) {
        System.out.println("Your Choice : ");
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                ArrayList<Restaurant> restaurantsAscending = Sorting.getInstance().orderByRate(admin);
                Collections.reverse(restaurantsAscending);
                this.workingOrder = restaurantsAscending;
                break;
            case 2:
                ArrayList<Restaurant> restaurantsDescending = Sorting.getInstance().orderByRate(admin);
                this.workingOrder = restaurantsDescending;
                break;
            case 3:
                ArrayList<Restaurant> commentsAscending = Sorting.getInstance().orderByComments(admin);
                Collections.reverse(commentsAscending);
                this.workingOrder = commentsAscending;
                break;
            case 4:
                ArrayList<Restaurant> commentsDescending = Sorting.getInstance().orderByComments(admin);
                this.workingOrder = commentsDescending;
                break;
            case 5:
                ArrayList<Restaurant> averageOrder = Sorting.getInstance().orderByAverage(admin);
                this.workingOrder = averageOrder;
                break;
            default:
                setOrder(admin);
        }
    }

    public Restaurant chooseRestaurantToOrder(ArrayList<Restaurant> restaurants, Admin admin) throws ParseException {
        System.out.println("--------------------------");
        System.out.println("Please Choose restaurant to order  :");
        System.out.println("******For changing Costumer or Changing restaurant Order Press (-1)*****");
        System.out.println("******For going back to  main menu press (-2)*****");
        Visual.printOrdered(restaurants);
        int ch = ScannerWrapper.getInstance().nextInt();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        Date time = new SimpleDateFormat("HH:mm").parse((formatter.format(date)));
        if (ch >= restaurants.size()) {
            System.out.println("Invalid Input");
            chooseRestaurantToOrder(restaurants, admin);
        }
        if (ch == -1) {
            changeOrderOption(admin);
        } else if (ch == -2) {
            Menu.loginMenu(admin);
        }

        Restaurant restaurant = null;
        if (time.after(restaurants.get(ch).getDateStart()) && time.before(restaurants.get(ch).getDateClose())) {
            restaurant = restaurants.get(ch);
        } else {
            System.out.println("Restaurant at this time is out of Work");
            Menu.orderMenu(admin);
        }
        return restaurant;
    }

    public void changeOrderOption(Admin admin) throws ParseException {
        System.out.println("what you want to change ? ");
        System.out.println("1.Customer\n2.Restaurants Order");
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                workingOrder = null;
                workingCostumer = null;
                Menu.orderMenu(admin);
                break;
            case 2:
                workingCostumer = null;
                Menu.orderMenu(admin);
                break;
            default:
                changeOrderOption(admin);
                break;
        }
    }


    public ArrayList<Food> chosenFoods(Restaurant restaurant) {
        ArrayList<Food> foods = new ArrayList<>();
        System.out.println("Please enter your foods multiple choice with \",\"");
        String choice = ScannerWrapper.getInstance().nextLine();
        if (!choice.matches("\\d*[,]*[\\d,]*")) {
            System.out.println("Invalid Input");
            chosenFoods(restaurant);
        }
        String[] chosen = choice.split(",");
        for (int i = 0; i < chosen.length; i++) {
            if (Integer.valueOf(chosen[i]) > restaurant.getFoods().size()) {
                System.out.println("Invalid Input");
                chosenFoods(restaurant);
            }
        }
        for (int i = 0; i < chosen.length; i++) {
            foods.add(restaurant.getFoods().get(Integer.valueOf(chosen[i])));
        }
        return foods;
    }


    public void makeOrder(Restaurant restaurant, Admin admin) throws ParseException {
        System.out.println("Foods : ");
        Visual.printMenu(restaurant, admin);
        ArrayList<Food> foods = chosenFoods(restaurant);
        System.out.println("Choose deliveries ...");
        System.out.println("---------------");
        System.out.println("-----Available deliveries-----");
        Visual.printRestaurantDeliveries(restaurant, admin);
        Delivery delivery = chooseDelivery(restaurant, admin);
        delivery.setAvailable(false);
        Order order = new Order(foods, delivery, restaurant, workingCostumer);
        handleOrders(order, admin);
    }

    public void handleOrders(Order order, Admin admin) {
        Delivery delivery = order.getDelivery();
        Restaurant restaurant = order.getRestaurant();
        Costumer costumer = order.getCostumer();
        delivery.addOrder(order);
        restaurant.addOrder(order);
        costumer.addOrder(order);
        admin.addOrder(order);

    }

    public Delivery chooseDelivery(Restaurant restaurant, Admin admin) {
        int ch = ScannerWrapper.getInstance().nextInt();
        if (ch >= restaurant.getDeliveries().size()) {
            System.out.println("INVALID INPUT");
            chooseDelivery(restaurant, admin);
        } else {
            return restaurant.getDeliveries().get(ch);
        }
        return null;
    }


}

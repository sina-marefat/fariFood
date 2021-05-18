package ir.ac.kntu.inputIo;

import ir.ac.kntu.*;
import ir.ac.kntu.visual.Visual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InputStream {

    private static InputStream instance = new InputStream();

    public static InputStream getInstance() {
        return instance;
    }

    private InputStream() {
    }

    public Address newAddress() {
        System.out.print("City :");
        String city = ScannerWrapper.getInstance().nextLine();

        System.out.print("Street Name :");
        String streetName = ScannerWrapper.getInstance().nextLine();

        System.out.print("Alley :");
        String alley = ScannerWrapper.getInstance().nextLine();

        System.out.print("Home or (restaurant) No :");
        int homeNum = ScannerWrapper.getInstance().nextInt();

        return new Address(city, streetName, alley, homeNum);
    }

    public Costumer newCostumer() {
        System.out.println("Address : ");
        Address address = newAddress();

        System.out.print("phone number : ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();

        System.out.println("DONE!");

        Costumer costumer = new Costumer(phoneNumber, address);
        return costumer;
    }

    public Restaurant addRestaurant(Admin admin) throws ParseException {
        System.out.print("restaurant name  : ");
        String name = ScannerWrapper.getInstance().nextLine();

        System.out.println("Address : ");
        Address address = newAddress();

        System.out.println("Open time : ");
        Date openTime = addTime();

        System.out.println("close time : ");
        Date closeTime = addTime();

        System.out.println("restaurant type : ");
        RestaurantPrice restaurantPrice = restaurantPrice();

        System.out.println("Foods :");
        ArrayList<Food> foods = handleFoods();

        System.out.println("Deliveries : ");

        ArrayList<Delivery> deliveries = handleDeliveries(admin);
        Restaurant restaurant = new Restaurant(name, openTime, closeTime, restaurantPrice, address, foods, deliveries);

        return restaurant;

    }

    public boolean removeRestaurant(Admin admin) {
        if (admin.getRestaurants().size() == 0) {
            System.out.println("you dont have any restaurants");
            return false;
        }
        System.out.print("Restaurant name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        boolean check = false;
        for (int i = 0; i < admin.getRestaurants().size(); i++) {
            if (admin.getRestaurants().get(i).getName().equals(name)) {
                admin.getRestaurants().remove(admin.getRestaurants().get(i));
                System.out.println("the restaurant removed successfully");
                check = true;
            }
        }
        if (!check) {
            System.out.println("YOU DONT HAVE THIS RESTAURANT");
        }
        return true;
    }

    public void removeDeliveries(Admin admin) {
        System.out.println("Please select Which delivery you want remove...");
        Visual.printDeliveries(admin);
        int choose = ScannerWrapper.getInstance().nextInt();
        if (admin.getDeliveries().size() >= choose) {
            admin.getDeliveries().remove(admin.getDeliveries().get(choose));
            System.out.println("Removed Successfully ..!");
        } else {
            System.out.println("Invalid Input");
        }
    }

    public void removeCostumer(Admin admin) {
        System.out.print("phoneNumber : ");
        String phoneNum = ScannerWrapper.getInstance().nextLine();

        for (int i = 0; i < admin.getCostumers().size(); i++) {
            if (admin.getCostumers().get(i).getPhoneNumber().equals(phoneNum)) {
                admin.getCostumers().remove(i);
                System.out.println("DONE!");
                return;
            }
        }
        System.out.println("Didnt Find !");
    }

    public RestaurantPrice restaurantPrice() {
        RestaurantPrice[] restaurantPrices = RestaurantPrice.values();
        for (int i = 0; i < 3; i++) {
            System.out.println(i + "." + restaurantPrices[i]);
        }
        System.out.println("please choose your Type");
        int choose = ScannerWrapper.getInstance().nextInt();
        RestaurantPrice restaurantPrice = null;
        if (choose <= 3 && choose >= 0) {
            restaurantPrice = restaurantPrices[choose];
            return restaurantPrice;
        } else {
            return restaurantPrice();
        }
    }

    public Date addTime() throws ParseException {
        System.out.print("Please input your time in format of 'HH:mm' ");
        String date = ScannerWrapper.getInstance().nextLine();
        if (!date.matches("\\d{2}:\\d{2}")) {
            System.out.println("your time format is incorrect please input correct time");
            return addTime();
        }
        String[] split = date.split(":");
        Date time = null;
        if (Integer.valueOf(split[0]) >= 0 && Integer.valueOf(split[0]) < 24 && Integer.valueOf(split[1]) >= 0 && Integer.valueOf(split[1]) < 60) {
            time = new SimpleDateFormat("HH:mm").parse(date);
            return time;
        } else {
            System.out.println("your time format is incorrect please input correct time");
            return addTime();
        }
    }

    public Food addFood() throws ParseException {
        System.out.print("Food name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.print("Food price : ");
        double price = ScannerWrapper.getInstance().nextDouble();
        System.out.println("time to make By minutes  : ");
        int date = ScannerWrapper.getInstance().nextInt();
        Food food = new Food(name, date, price);
        return food;
    }

    public ArrayList<Food> handleFoods() throws ParseException {
        System.out.println("Do you want to add Foods ? : ");
        System.out.println("1.Yes\n2.No");
        ArrayList<Food> foods = new ArrayList<>();
        int option = ScannerWrapper.getInstance().nextInt();
        while (option == 1) {
            foods.add(addFood());
            System.out.println("Do you want to add More foods ?");
            System.out.println("1.Yes\n2.No");
            option = ScannerWrapper.getInstance().nextInt();
            while (option > 2) {
                System.out.print("Invalid input Please try Again");
                option = ScannerWrapper.getInstance().nextInt();
            }
        }
        return foods;
    }

    public Delivery addDeliveries(Admin admin) {
        System.out.println("Delivery vehicle : ");
        DeliveryVehicle[] deliveryVehicles = DeliveryVehicle.values();
        for (int i = 0; i < deliveryVehicles.length; i++) {
            System.out.println(i + "." + deliveryVehicles[i]);
        }
        int choose = ScannerWrapper.getInstance().nextInt();
        DeliveryVehicle deliveryVehicle = deliveryVehicles[choose];

        System.out.println("Salary Type : ");
        DeliveySalary[] deliveySalaries = DeliveySalary.values();
        for (int i = 0; i < deliveySalaries.length; i++) {
            System.out.println(i + "." + deliveySalaries[i]);
        }
        int chooseSalary = ScannerWrapper.getInstance().nextInt();
        DeliveySalary deliveySalary = deliveySalaries[chooseSalary];

        System.out.print("Salary amount($ dollar) : ");
        int salary = ScannerWrapper.getInstance().nextInt();

        System.out.println("Add working Days :");
        ArrayList<DeliveryAvailableDays> deliveryAvailbleDays = addDays();
        Delivery delivery = new Delivery(deliveryVehicle, deliveySalary, salary, deliveryAvailbleDays);
        admin.getDeliveries().add(delivery);
        return delivery;
    }

    public ArrayList<DeliveryAvailableDays> addDays() {
        DeliveryAvailableDays[] deliveryAvailbleDays = DeliveryAvailableDays.values();
        System.out.println("Please choose your days . for multiple days use \",\" for exp(1,2,3)");
        for (int i = 0; i < deliveryAvailbleDays.length; i++) {
            System.out.println(i + "." + deliveryAvailbleDays[i]);
        }
        ArrayList<DeliveryAvailableDays> deliveryChoosed = new ArrayList<>();
        String day = ScannerWrapper.getInstance().nextLine();
        String[] days = day.split(",");
        for (String str : days) {
            deliveryChoosed.add(deliveryAvailbleDays[Integer.valueOf(str)]);
        }
        return deliveryChoosed;
    }

    public ArrayList<Delivery> handleDeliveries(Admin admin) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        int next;
        System.out.println("Do you Want to add Deliveries ? ");
        System.out.println("1.Yes\n2.No");
        int continueInt = ScannerWrapper.getInstance().nextInt();
        do {
            if (continueInt == 2) {
                return deliveries;
            } else if (continueInt == 1) {
                break;
            } else if (continueInt != 2 && continueInt != 1) {
                System.out.println("Wrong Input Try again");
            }
            continueInt = ScannerWrapper.getInstance().nextInt();
        } while (continueInt != 2 && continueInt != 1);
        do {
            System.out.println("How you going to add deliveries ?");
            System.out.println("1.existing delivery\n2.new delivery");
            int choose = ScannerWrapper.getInstance().nextInt();
            if (choose == 2) {
                deliveries.add(addDeliveries(admin));
            } else if (choose == 1) {
                Visual.printDeliveries(admin);
                int chooseDelivery = ScannerWrapper.getInstance().nextInt();
                if (deliveryFullCheck(chooseDelivery, admin) != null) {
                    deliveries.add(admin.getDeliveries().get(chooseDelivery));
                }
            }
            System.out.println("Do you want to add more deliveries?");
            System.out.println("1.Yes\n2.No");
            next = ScannerWrapper.getInstance().nextInt();
        } while (next != 2);
        return deliveries;
    }

    public Delivery deliveryFullCheck(int n, Admin admin) {
        if (n > admin.getDeliveries().size()) {
            System.out.println("Wrong input..!");
            return null;
        } else if (admin.getDeliveries().get(n).getRestaurants().size() < 2) {
            return admin.getDeliveries().get(n);
        } else {
            System.out.println("this delivery can not be chosen (full restaurants)");
        }
        return null;
    }

    public void costumerChange(Admin admin) {
        System.out.print("please insert costumer number :");
        String number = ScannerWrapper.getInstance().nextLine();
        Costumer costumer = null;
        for (int i = 0; i < admin.getCostumers().size(); i++) {
            if (admin.getCostumers().get(i).getPhoneNumber().equals(number)) {
                costumer = admin.getCostumers().get(i);
            }
        }
        if (costumer == null) {
            System.out.println("Wrong Number");
            return;
        }
        System.out.println("what do you want to do now ? ");
        System.out.println("1.change number\n2.change address");
        int choose = ScannerWrapper.getInstance().nextInt();
        if (choose == 1) {
            System.out.print("Enter New Number");
            String newNum = ScannerWrapper.getInstance().nextLine();
            costumer.setPhoneNumber(newNum);
        } else if (choose == 2) {
            System.out.println("New Address");
            Address newAddress = newAddress();
            costumer.setAddress(newAddress);
        } else {
            System.out.println("Wrong Input");
        }
    }

    public void removeDeliverFromRestaurant(Admin admin) {
        Visual.printDeliveries(admin);
        System.out.print("please choose your delivery..!");
        int chooseDelivery = ScannerWrapper.getInstance().nextInt();
        Visual.printDeliveryRestaurant(admin.getDeliveries().get(chooseDelivery));
        System.out.println("please choose restaurant to remove..!");
        int chooseRestaurant = ScannerWrapper.getInstance().nextInt();
        if (chooseDelivery >= admin.getDeliveries().size()) {
            System.out.println("invalid input");
        } else if (chooseRestaurant >= admin.getRestaurants().size()) {
            System.out.println("invalid input");
        } else {
            admin.getDeliveries().get(chooseDelivery).getRestaurants().remove(chooseRestaurant);
        }
    }

    public void addDeliverToRestaurant(Admin admin) {
        Visual.printDeliveries(admin);
        System.out.print("please choose your delivery..!");
        int chooseDelivery = ScannerWrapper.getInstance().nextInt();
        Visual.printRestaurants(admin);
        System.out.println("please choose restaurant to add..!");
        int chooseRestaurant = ScannerWrapper.getInstance().nextInt();
        if (chooseDelivery >= admin.getDeliveries().size()) {
            System.out.println("invalid input");
        } else if (admin.getDeliveries().get(chooseDelivery).getRestaurants().size() >= 2) {
            System.out.println("this delivery is full...!");
        } else if (admin.getDeliveries().get(chooseDelivery).getRestaurants().contains(admin.getRestaurants().get(chooseRestaurant))) {
            System.out.println("Already in this restaurant");
        } else if (chooseRestaurant >= admin.getRestaurants().size()) {
            System.out.println("invalid input");
        } else {
            admin.getDeliveries().get(chooseDelivery).addRestaurant(admin.getRestaurants().get(chooseRestaurant));
            System.out.println("Done!");
        }
    }

    public void viewDeliveryRestaurants(Admin admin) {
        Visual.printDeliveries(admin);
        System.out.println("Please choose your delivery .. :");
        int ch = ScannerWrapper.getInstance().nextInt();
        Visual.printDeliveryRestaurant(admin.getDeliveries().get(ch));
        System.out.println("--------------------");
    }

    public void viewDeliveryOrders(Admin admin) {
        Visual.printDeliveries(admin);
        System.out.println("Please choose your delivery .. :");
        int ch = ScannerWrapper.getInstance().nextInt();
        Visual.printDeliveryOrders(admin.getDeliveries().get(ch));
    }

    public void viewDeliveryComments(Admin admin) {
        Visual.printDeliveries(admin);
        System.out.println("Please choose your delivery .. :");
        int ch = ScannerWrapper.getInstance().nextInt();
        Visual.printDeliveryComments(admin.getDeliveries().get(ch));
    }


}

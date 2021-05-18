package ir.ac.kntu.visual;

import ir.ac.kntu.Admin;
import ir.ac.kntu.AdminOptions;
import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.inputIo.InputFacilities;
import ir.ac.kntu.inputIo.InputOrderIo;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu {

    private Menu() {
    }

    public static boolean loginMenu(Admin admin) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dateTimeFormatter.format(now));
        System.out.println("----- Welcome to fariFood App -----");
        System.out.println("1. Admin Menu");
        System.out.println("2. Order Menu");
        System.out.println("3. facilities Menu");
        System.out.println("4. Exit");
        return loginMenuHandler(admin);
    }

    public static boolean loginMenuHandler(Admin admin) throws ParseException {
        int option = ScannerWrapper.getInstance().nextInt();
        switch (option) {
            case 1:
                adminMenu(admin);
                break;
            case 2:
                orderMenu(admin);
                break;
            case 3:
                facilitiesMenu(admin);
                break;
            case 4:
                return false;
            default:
                return true;
        }
        return true;
    }

    public static void facilitiesMenu(Admin admin) throws ParseException {
        System.out.println("--------------------");
        System.out.println("what you want too ?");
        System.out.println("-----------------------");
        System.out.println("1.Change or set a delivery");
        System.out.println("2.restaurant comment history");
        System.out.println("3.Food comment history");
        System.out.println("4.best 3 restaurants");
        System.out.println("5.best restaurant for a special food");
        System.out.println("6.search restaurant by name");
        System.out.println("7.filter restaurants");
        System.out.println("8.costumers comment history");
        System.out.println("9.costumers order history");
        System.out.println("10.main menu");
        facilitiesMenuHandler(admin);
    }

    public static void facilitiesMenuHandler(Admin admin) throws ParseException {
        System.out.println("-------------------------------");
        System.out.println("select action to do :");
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                InputFacilities.getInstance().checkDeliveryAvailable(InputFacilities.getInstance().chooseControlOrdersAndComments(admin), admin);
                break;
            case 2:
                Visual.printUserComments(InputFacilities.getInstance().getRestaurantComment(admin));
                break;
            case 3:
                InputFacilities.getInstance().foodCommentChoose(admin);
                break;
            case 4:
                InputFacilities.getInstance().printAndChooseThreeBestRestaurants(admin);
                break;
            case 5:
                InputFacilities.getInstance().searchByFood(admin);
                break;
            case 6:
                InputFacilities.getInstance().searchRestaurantByName(admin);
                break;
            case 7:
                InputFacilities.getInstance().filterByRestaurantType(admin);
                break;
            case 8:
                Visual.printUserComments(InputFacilities.getInstance().getUserComments(admin));
                break;
            case 9:
                Visual.printUserOrders(InputFacilities.getInstance().getUserOrders(admin));
                break;
            case 10:
                loginMenu(admin);
                break;
            default:
                facilitiesMenu(admin);
        }
    }

    public static void orderMenu(Admin admin) throws ParseException {
        if (InputOrderIo.getInstance().getWorkingCostumer() == null) {
            InputOrderIo.getInstance().setCustomer(admin);
        }
        if (InputOrderIo.getInstance().getWorkingOrder() == null) {
            System.out.println("--------------");
            System.out.println("How you gonna set order?");
            System.out.println("1.Ascending by rate");
            System.out.println("2.Descending by rate");
            System.out.println("3.Ascending by Comment numbers");
            System.out.println("4.Descending by Comment numbers");
            System.out.println("5.Averaging order numbers");
            InputOrderIo.getInstance().setOrder(admin);
        }
        InputOrderIo.getInstance().setWorkingRestaurant(InputOrderIo.getInstance().chooseRestaurantToOrder(InputOrderIo.getInstance().getWorkingOrder(), admin));
        foodOrder(admin);
    }

    public static void foodOrder(Admin admin) throws ParseException {
        InputOrderIo.getInstance().makeOrder(InputOrderIo.getInstance().getWorkingRestaurant(), admin);
        loginMenu(admin);
    }

    public static void adminMenu(Admin admin) throws ParseException {
        System.out.println("--------------------");
        System.out.println("Please use one of these options :");
        System.out.println("0.Control Orders");
        System.out.println("1.Add costumer");
        System.out.println("2.Remove costumer");
        System.out.println("3.Add restaurant");
        System.out.println("4.Remove restaurant");
        System.out.println("5.Add Delivery");
        System.out.println("6.Remove Delivery");
        System.out.println("7.change costumer details");
        System.out.println("8.Review Customers");
        System.out.println("9.Review Deliveries");
        System.out.println("10.Deliveries work handle");
        System.out.println("11.return to first Menu");
        adminMenuHandler(admin);
    }

    public static void adminMenuHandler(Admin admin) throws ParseException {
        int n = ScannerWrapper.getInstance().nextInt();
        AdminOptions userChoice = null;
        for (AdminOptions adminOption : AdminOptions.values()) {
            if (adminOption.getOption() == n - 1) {
                userChoice = adminOption;
            }
        }
        switchAdminChoice(userChoice, admin);
    }

    public static void switchAdminChoice(AdminOptions userChoice, Admin admin) throws ParseException {
        switch (userChoice) {
            case CONTROL_ORDERS:
                controlMenu(admin);
            case ADD_COSTUMER:
                admin.addCostumer();
                break;
            case REMOVE_CUSTOMER:
                admin.removeCostumer();
                break;
            case ADD_RESTAURANT:
                admin.addRestaurant();
                break;
            case REMOVE_RESTAURANT:
                admin.removeRestaurant();
                break;
            case ADD_DELIVERY:
                admin.addDeliveries();
                break;
            case REMOVE_DELIVERY:
                admin.removeDeliveries();
                break;
            case CHANGE_USER:
                admin.changeCustomer();
                break;
            case REVIEW_CUSTOMERS:
                Visual.printCustomers(admin);
                break;
            case REVIEW_DELIVERIES:
                Visual.printDeliveries(admin);
                break;
            case DELIVERY_HANDLE:
                deliveryHandle(admin);
                break;
            case FIRST_MENU:
                loginMenu(admin);
                break;
            default:
        }
        if (checkNext()) {
            System.out.println("--------------------");
            adminMenu(admin);
        } else {
            System.out.println("Admin menu closed successfully..!");
            loginMenu(admin);
        }
    }

    public static void deliveryHandle(Admin admin) throws ParseException {
        System.out.println("what you want to do ?");
        System.out.println("1.remove delivery from restaurant");
        System.out.println("2.add delivery to a restaurant");
        System.out.println("3.view a delivery available restaurant");
        System.out.println("4.view Deliveries orders");
        System.out.println("5.view Deliveries Comments");
        System.out.println("6.last menu");
        deliveryChoice(admin);
    }

    public static void deliveryChoice(Admin admin) throws ParseException {
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                admin.handleDeliveryRmv();
                adminMenu(admin);
                break;
            case 2:
                admin.handleDeliveryAdd();
                adminMenu(admin);
                break;
            case 3:
                admin.handleDeliveryView();
                adminMenu(admin);
                break;
            case 4:
                admin.handleDeliveryOrdersView();
                adminMenu(admin);
            case 5:
                admin.handleDeliveryComments();
                adminMenu(admin);
                break;
            case 6:
                adminMenu(admin);
                break;
            default:
                System.out.println("invalid input");
                deliveryHandle(admin);
                break;
        }
    }


    public static void controlMenu(Admin admin) throws ParseException {
        System.out.println("Please Choose your Option..!");
        System.out.println("1.New Food ");
        System.out.println("2.Control orders and comments");
        System.out.println("3.firstMenu");
        controlMenuHandle(admin);
    }

    public static void controlMenuHandle(Admin admin) throws ParseException {
        System.out.println("--------------");
        System.out.print("CHOOSE OPTION : ");
        int ch = ScannerWrapper.getInstance().nextInt();
        switch (ch) {
            case 1:
                //
                break;
            case 2:
                InputFacilities.getInstance().controlOrdersAndComments(InputFacilities.getInstance().chooseControlOrdersAndComments(admin), admin);
                break;
            case 3:
                Menu.loginMenu(admin);
                break;
            default:
                controlMenuHandle(admin);
                break;
        }
        Menu.loginMenu(admin);
    }

    public static boolean checkNext() {
        System.out.println("--------------------");
        System.out.println("Do you Have Any other changes?");
        System.out.println("1.Yes\n2.No");
        int choose = ScannerWrapper.getInstance().nextInt();
        if (choose == 1) {
            return true;
        } else if (choose == 2) {
            return false;
        } else {
            return checkNext();
        }
    }


}

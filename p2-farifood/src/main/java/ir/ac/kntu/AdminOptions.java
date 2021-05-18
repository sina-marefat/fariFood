package ir.ac.kntu;

public enum AdminOptions {
    CONTROL_ORDERS(-1), ADD_COSTUMER(0), REMOVE_CUSTOMER(1), ADD_RESTAURANT(2), REMOVE_RESTAURANT(3), ADD_DELIVERY(4),
    REMOVE_DELIVERY(5), CHANGE_USER(6), REVIEW_CUSTOMERS(7), REVIEW_DELIVERIES(8), DELIVERY_HANDLE(9), FIRST_MENU(10);
    private int option;

    AdminOptions(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }
}


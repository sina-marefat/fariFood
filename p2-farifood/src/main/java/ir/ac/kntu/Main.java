package ir.ac.kntu;

import ir.ac.kntu.visual.Menu;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Admin admin = new Admin("Admin", "1234");
        if (login(admin) && !Menu.loginMenu(admin)) {
            System.out.println("Thanks for using our service.");
        }
    }

    public static boolean login(Admin admin) {
        System.out.print("UserName :");
        String userName = ScannerWrapper.getInstance().nextLine();
        System.out.print("PassWord :");
        String password = ScannerWrapper.getInstance().nextLine();

        if (admin.getPassword().equals(password) && admin.getUserName().equals(userName)) {
            System.out.println("Login Successfully");
            return true;
        } else {
            System.out.println("Incorrect UserName or PassWord");
            return login(admin);
        }
    }

}
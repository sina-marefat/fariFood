package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Food {
    private String name;
    private int timeToMake;
    private double price;
    private int rate = 5;
    private ArrayList<Comment> comments;

    public Food(String name, int timeToMake, double price) {
        setName(name);
        setTimeToMake(timeToMake);
        setPrice(price);
        comments = new ArrayList<>();

    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        this.rate += comment.getRate();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeToMake() {
        return timeToMake;
    }

    public void setTimeToMake(int timeToMake) {
        this.timeToMake = timeToMake;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }
}

package ir.ac.kntu;

import java.util.ArrayList;

public class Comment {
    private String comment;
    private int rate;
    private Costumer costumer;

    public Comment(String comment, int rate, Costumer costumer) {
        setComment(comment);
        setRate(rate);
        setCostumer(costumer);

    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public String getComment() {
        return comment;
    }

    public int getRate() {
        return rate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRate(int rate) {
        if (rate > 5) {
            this.rate = 5;
        } else if (rate < 1) {
            this.rate = 1;
        } else {
            this.rate = rate;
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", rate=" + rate +
                ", costumer=" + costumer +
                '}';
    }
}

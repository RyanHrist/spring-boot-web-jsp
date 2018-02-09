package application.models;

import org.apache.log4j.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Meals {
    ArrayList<User> guestList = new ArrayList<User>();
    private boolean happened;
    private Host withHost;
    private CancellationPolicy cancellation;
    private Date date;
    private Image image;
    private int capacity;
    private double price;
    private Category category;
    private String description;
    private Time time;


    private boolean processPayment(){


    }
    public boolean cancel(boolean Cancel){
        return Cancel;
    }

    public ArrayList<User> getGuestList() {
        return guestList;
    }
     public boolean getHappened(){
        return happened;
     }

    public Host getWithHost() {
        return withHost;
    }

    public CancellationPolicy getCancellation() {
        return cancellation;
    }
    public Date getDate (){

    }

    public Image getImage() {
        return image;
    }
    public int getCapacity(){

        return capacity;
    }
    public double getPrice(){
        return price;
    }

    public Category getCategory() {
        return category;
    }
    public String getDescription(){
        return description;
    }

    public Time getTime() {
        return time;
    }
}

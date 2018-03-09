package application.models;

import org.apache.log4j.Category;

import java.sql.Time;
import java.util.ArrayList;

public class Meals {
    ArrayList<User> guestList = new ArrayList<User>();
    private boolean happened;
    private int mealID;
    private String mealTitle;
    private String withHost;
    private CancellationPolicy cancellation;
    private String date;
    private String image;
    private int capacity;
    private double price;
    private Category category;
    private String description;
    private Time time;

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    private boolean processPayment(){
        return false;
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

    public String getWithHost() {
        return withHost;
    }

    public CancellationPolicy getCancellation() {
        return cancellation;
    }
    public String getDate (){
        return date;
    }

    public String getImage() {
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

    public void setCancellation(CancellationPolicy cancellation) {
        this.cancellation = cancellation;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGuestList(ArrayList<User> guestList) {
        this.guestList = guestList;
    }

    public void setHappened(boolean happened) {
        this.happened = happened;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setWithHost(String withHost) {
        this.withHost = withHost;
    }
}

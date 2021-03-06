package application.models;

import org.apache.log4j.Category;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Meals {
    ArrayList<User> guestList = new ArrayList<User>();
    private boolean happened;
    private String address;
    private String country;
    private String city;
    private String postal;
    private int mealID;
    private String mealTitle;
    private String withHost;
    private CancellationPolicy cancellation;
    private String cancelBy;
    private double cancelationFee;
    private String date;
    private Time time;
    private String image;
    private int capacity;
    private double price;
    private String category;
    private String description;

    public Meals(){

    }

    // TODO: Check if this is used
    public Meals(String hemail, String dom, String mtitle, String mpicture, int capacity, double pricepp,
                 String category, String description, String cancelationtime, double cancelationfee,
                 String country, String city, String saddress, String postal){
        this.withHost = hemail;
        this.date = dom;
        this.mealTitle = mtitle;
        this.image = mpicture;
        this.capacity = capacity;
        this.price = pricepp;
        this.category = category;
        this.description = description;
        this.cancelBy = cancelationtime;
        this.cancelationFee = cancelationfee;
        this.country = country;
        this.city = city;
        this.address = saddress;
        this.postal = postal;
    }

    public void setCancelBy(String cancelBy){this.cancelBy = cancelBy;}

    public String getCancelBy(){return cancelBy;}

    public void setCancelationFee(double cancelationFee){this.cancelationFee = cancelationFee;}

    public double getCancelationFee(){return cancelationFee;}

    public void setCountry(String country) { this.country = country;}

    public String getCountry() { return country;}

    public void setCity(String city) {this.city = city;}

    public String getCity() {return city;}

    public void setPostal(String postal) {this.postal = postal;}

    public String getPostal() {return postal;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
    public int getCapacity(){ return capacity; }

    public double getPrice(){
        return price;
    }

    public String getCategory() {
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

    public void setCategory(String category) {
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

    public Date getDateAsDate(){
        Date toReturn = new Date(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            toReturn = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public boolean mealHappened(){
        Date dom = getDateAsDate();
        Date now = new Date();
        return dom.before(now);
    }
}

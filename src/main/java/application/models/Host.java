package application.models;

import application.models.enumerations.Country;
import application.models.enumerations.Province;

import java.util.Date;

public class Host {
    private HostRating hostRating;
    private Meals meal;
    private  Location location;
    private User previousGuests;
    private double duration;
    private Date cancellation;

    public HostRating getHostRating() {
        return hostRating;
    }

    public Meals getMeals() {
        return meal;
    }

public Location getLocation(){
    return location;
}

public double getDuration() {
    return duration;
    }
    public User getPreviousGuests(){
        return previousGuests;
    }
    public void setMeals (Meals meal){
        this.meal = meal;
    }
    public void setLocation (Location location){
        this.location = location;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    public void setCancellation(Date cancellation){
        this.cancellation = cancellation;
    }
    //public void calculateRating(){}
}

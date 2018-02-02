package application.models;

import java.util.Date;

public class CancellationPolicy {

    private Date timeBeforeCancellation;
    private double cancellationFee;

    // TODO: make it return double? idk
    public void calculateFee() {

    }

    public Date getTimeBeforeCancellation() {
        return timeBeforeCancellation;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    // TODO
    public void setTimeBeforeCancellation(){

    }

    public double getCancellationFee() {
        return cancellationFee;
    }
}

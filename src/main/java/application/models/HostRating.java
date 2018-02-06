package application.models;

public class HostRating {
    private int totalRating;
    private int mealRating;
    private int cleanlinessRating;
    private int hospitalityRating;
    private String comments;

    public int getTotalRating() {
        return totalRating;
    }

    public int getMealRating() {
        return mealRating;
    }

    public int getCleanlinessRating() {
        return cleanlinessRating;
    }

    public int getHospitalityRating() {
        return hospitalityRating;
    }

    public String getComments() {
        return comments;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}

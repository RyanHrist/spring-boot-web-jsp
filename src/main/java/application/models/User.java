package application.models;

import application.Database;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class User {

    public User() {
    }
    private int userID;
    private String name;
    private String email;
    private String password;
    private String userDescription;
    private GuestRating guestRating;
    private String image;

    //private CreditCardInformation creditCardInformation;
    //this is all for cc info
    private String ccnumber;
    private String cccvv;
    private String cccountry;
    private String ccprovince;
    private String cccity;
    private String ccadress;
    private String ccpostal;
    private Date ccexp;

    //private Country country;
    private String country;
    //private Currency currency;
    private String currency;
    //private Gender gender;
    private String gender;
    //private Language language;
    private String language;

    private String profilePicture;
    private Date dateOfBirth;

    // TODO: Fix this to be array lists
    private Meals pastVisits;
    private Meals futureVisits;

    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getUserDescription(){return userDescription;}

    public GuestRating getGuestRating() {
        return guestRating;
    }

    //private CreditCardInformation getCreditCardInformation() {
    //    return creditCardInformation;
    //}

    public void setImage(String image){this.image = image;}

    public String getImage(){return image;}

    public void setUserID(int userID){this.userID = userID;}

    public int getUserID(){return userID;}

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCccnumber(){
        return ccnumber;
    }

    public String getCccity() {
        return cccity;
    }

    public String getCccountry() {
        return cccountry;
    }

    public String getCcadress() {
        return ccadress;
    }

    public String getCcprovince() {
        return ccprovince;
    }

    public String getCcpostal() {
        return ccpostal;
    }

    public Date getCcexp() {
        return ccexp;
    }

    public String getCccvv() {
        return cccvv;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getLanguage() {
        return language;
    }

    public Meals getPastVisits() {
        return pastVisits;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setUserDescription (String userDescription){
        this.userDescription = userDescription;
    }
    public void setGuestRating(GuestRating guestRating){
        this.guestRating = guestRating;
    }

    //public void setCreditCardInformation(CreditCardInformation creditCardInformation){
    //    this.creditCardInformation = creditCardInformation;
    //}


    public void setCcadress(String ccadress) {
        this.ccadress = ccadress;
    }

    public void setCccity(String cccity) {
        this.cccity = cccity;
    }

    public void setCcnumber(String ccnumber) {
        this.ccnumber = ccnumber;
    }

    public void setCccountry(String cccountry) {
        this.cccountry = cccountry;
    }

    public void setCcprovince(String ccprovince) {
        this.ccprovince = ccprovince;
    }

    public void setCccvv(String cccvv) {
        this.cccvv = cccvv;
    }

    public void setCcexp(Date ccexp) {
        this.ccexp = ccexp;
    }

    public void setCcpostal(String ccpostal) {
        this.ccpostal = ccpostal;
    }

    public void setCountry(String country){
        this.country = country;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    //public void setProfilePicture(Image ){}
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setLanguage(String language){
        this.language = language;
    }
    public void setPastVisits(Meals pastVisits){
        this.pastVisits = pastVisits;
    }
    public void calculateRating(){}

    public ArrayList<Meals> getHostingMeals() throws SQLException, ClassNotFoundException {
        ArrayList<Meals> rtn = new ArrayList<>();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();
        String selectCriteria = "";
        selectCriteria += "where hemail = '" + email + "'";
        ResultSet rs = statement.executeQuery("select * from Meals"
                + selectCriteria + ";");
        while (rs.next()){
            Meals meal = new Meals();
            meal.setMealID(rs.getInt("mealid"));
            meal.setCapacity(rs.getInt("capacity"));
            meal.setWithHost(rs.getString("hemail"));
            meal.setDate(rs.getString("dom"));
            meal.setMealTitle(rs.getString("mtitle"));
            meal.setImage(rs.getString("mpicture"));
            meal.setPrice(rs.getDouble("pricepp"));
            meal.setCategory(rs.getString("cetegory"));
            meal.setDescription(rs.getString("description"));
            meal.setCancelBy(rs.getString("cancelationtime"));
            meal.setCancelationFee(rs.getDouble("cancelationfee"));
            meal.setCountry(rs.getString("country"));
            meal.setCity(rs.getString("city"));
            meal.setAddress(rs.getString("address"));
            meal.setPostal(rs.getString("postal"));
            rtn.add(meal);
        }
        return rtn;
    }

    public ArrayList<Meals> getAttendingMeals() throws SQLException, ClassNotFoundException {
        ArrayList<Meals> rtn = new ArrayList<>();
        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();
        String selectCriteria = "";
        selectCriteria += "where gemail = '" + email + "'";
        ResultSet rs = statement.executeQuery("select * from Attending"
                + selectCriteria + ";");
        while (rs.next()){
            ResultSet theMeal = statement.executeQuery("select * from Meals where mealid ="
                    + rs.getInt("mealid") + ";");
            theMeal.first();
            Meals meal = new Meals();
            meal.setMealID(theMeal.getInt("mealid"));
            meal.setCapacity(theMeal.getInt("capacity"));
            meal.setWithHost(theMeal.getString("hemail"));
            meal.setDate(theMeal.getString("dom"));
            meal.setMealTitle(theMeal.getString("mtitle"));
            meal.setImage(theMeal.getString("mpicture"));
            meal.setPrice(theMeal.getDouble("pricepp"));
            meal.setCategory(theMeal.getString("cetegory"));
            meal.setDescription(theMeal.getString("description"));
            meal.setCancelBy(theMeal.getString("cancelationtime"));
            meal.setCancelationFee(theMeal.getDouble("cancelationfee"));
            meal.setCountry(theMeal.getString("country"));
            meal.setCity(theMeal.getString("city"));
            meal.setAddress(theMeal.getString("address"));
            meal.setPostal(theMeal.getString("postal"));
            rtn.add(meal);
        }
        return rtn;
    }


}
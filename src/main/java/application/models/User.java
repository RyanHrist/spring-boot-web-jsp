package application.models;

import java.awt.*;
import java.util.Date;

public class User {


    public User() {
    }
    private String name;
    private String email;
    private String password;
    private String userDescription;
    private GuestRating guestRating;

    //private CreditCardInformation creditCardInformation;
    //this is all for cc info
    private String ccnumber;
    private String ccvv;
    private String cccountry;
    private String ccprovince;
    private String cccity;
    private String ccadress;
    private String ccpostal;
    private String ccexp;

    //private Country country;
    private String country;
    //private Currency currency;
    private String currency;
    //private Gender gender;
    private String gender;
    //private Language language;
    private String language;

    private Image profilePicture;
    private Date dateOfBirth;

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

    public String getCcexp() {
        return ccexp;
    }

    public String getCcvv() {
        return ccvv;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public Image getImageBanner() {
        return profilePicture;
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

    public void setCcvv(String ccvv) {
        this.ccvv = ccvv;
    }

    public void setCcexp(String ccexp) {
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


}
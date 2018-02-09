package application.models;

import application.models.enumerations.Country;
import application.models.enumerations.Currency;
import application.models.enumerations.Gender;
import application.models.enumerations.Language;
import org.springframework.boot.ImageBanner;

public class User {
    private String name;
    private String email;
    private String password;
    private String userDescription;
    private GuestRating guestRating;
    private CreditCardInformation creditCardInformation;
    private Country country;
    private Currency currency;
    private Image profilePicture;
    private Date dateOfBirth;
    private Gender gender;
    private Language language;
    private Meals pastVisits;
    private Meals futureVisits;

    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getUserDescription(){return userDescription;}

    public GuestRating getGuestRating() {
        return guestRating;
    }

    private CreditCardInformation getCreditCardInformation() {
        return creditCardInformation;
    }

    public Country getCountry() {
        return country;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Image getImageBanner() {
        return imageBanner;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public Language getLanguage() {
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
    private void setPassword (String password){
        this.password = password;
    }

    public void setUserDescription (String userDescription){
        this.userDescription = userDescription;
    }
    public void setGuestRating(GuestRating guestRating){
        this.guestRating = guestRating;
    }
    public void setCreditCardInformation(CreditCardInformation creditCardInformation){
        this.creditCardInformation = creditCardInformation;
    }
    public void setCountry(Country country){
        this.country = country;
    }
    public void setCurrency(Currency currency){
        this.currency = currency;
    }
    //public void setProfilePicture(Image ){}
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }
    public void setLanguage(Language language){
        this.language = language;
    }
    public void setPastVisits(Meals pastVisits){
        this.pastVisits = pastVisits;
    }
    public void calculateRating(){}


}

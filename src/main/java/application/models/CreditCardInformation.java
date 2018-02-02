package application.models;

import java.util.Date;

public class CreditCardInformation {

    private int cardNumber;
    private int cvvNumber;
    private CardType creditCardType;
    private String name;
    private Location billingAddress;
    private Date expiryDate;

    // TODO
    private boolean processCard() {
        return true;
    }

    // TODO
    private boolean requestHold() {
        return true;
    }

    // TODO
    private boolean checkInput() {
        return true;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public CardType getCreditCardType() {
        return creditCardType;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setBillingAddress(Location billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setCreditCardType(CardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public Location getBillingAddress() {
        return billingAddress;
    }

    public String getName() {
        return name;
    }

    private enum CardType {
        VISA, MASTERCARD, OTHERS;
    }
}

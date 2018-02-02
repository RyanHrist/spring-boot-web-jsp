package application.models;

import application.models.enumerations.Country;
import application.models.enumerations.Province;

public class Location {

    private String address;
    private String city;
    private Country country;
    private Province province;
    private String postal;

    public Country getCountry() {
        return country;
    }

    public Province getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

}

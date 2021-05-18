package ir.ac.kntu;

import java.util.Objects;

public class Address {
    private String city;
    private String streetName;
    private String alley;
    private int homeNum;

    public Address(String city, String streetName, String alley, int homeNum) {
        setStreetName(streetName);
        setAlley(alley);
        setCity(city);
        setHomeNum(homeNum);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public int getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(int homeNum) {
        this.homeNum = homeNum;
    }

    @Override
    public String toString() {
        return "Address{" + city + '-' + streetName + '-' + alley + '-' +
                ", homeNum=" + homeNum +
                '}';
    }
}

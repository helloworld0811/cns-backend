package com.cns.backend.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hku on 03.04.17.
 */
public class LoginDetails implements Serializable{


    private static final long serialVersionUID = 54656576545L;

  private Long userId;

  private String password;

  private String userName;

  private String firstName;

  private String lastName;

  private String address;

  private String city;

  private String country;

  private String company;

  private List<Long> loginTimestamps;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Long> getLoginTimestamps() {
        return loginTimestamps;
    }

    public void setLoginTimestamps(List<Long> loginTimestamps) {
        this.loginTimestamps = loginTimestamps;
    }
}

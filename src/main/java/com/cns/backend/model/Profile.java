package com.cns.backend.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hku on 03.04.17.
 */
public class Profile implements Serializable{

    private static final long serialVersionUID = 3214332323563L;

    private Long id;

    private CNSChannel CNSChannel;

    private String name;

    private String address;

    private String city;

    private String country;

    private Long friendsCount;

    private PerformanceMetrics performanceMetrics;

    private UserRole userRole;

    private List<Tag> tag;

    private String language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CNSChannel getCNSChannel() {
        return CNSChannel;
    }

    public void setCNSChannel(CNSChannel CNSChannel) {
        this.CNSChannel = CNSChannel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public PerformanceMetrics getPerformanceMetrics() {
        return performanceMetrics;
    }

    public void setPerformanceMetrics(PerformanceMetrics performanceMetrics) {
        this.performanceMetrics = performanceMetrics;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

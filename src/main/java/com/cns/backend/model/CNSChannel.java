package com.cns.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by hku on 02.04.17.
 */
@Entity
@Table(name = "cnschannel")
public class CNSChannel implements Serializable{

    private static final long serialVersionUID = 21312312323L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ChannelType channelType;

    @Column(unique = true )
    private String channelId;

    private Boolean isVideo=false;

    private Boolean isPicture=false;

    private Boolean isSocial=false;

    private Boolean isImplemented=true;

    private String country="US";

    private String title="";

    @Column(nullable = true)
    private Long publishedTime=null;

    private String description="";

    private String imageUrl="";

    private String titleLocal="";

    private String descriptionLocal="";

    private String additionalInfo="";

    private String language="EN";

    private Boolean isRegistered=false;

    @Column(nullable = true)
    private Long categoryId=null;

    private Boolean isManual=false;

    public CNSChannel()
    {

    }

    public CNSChannel(ChannelType channelType, String channelId, Boolean isVideo, Boolean isPicture, Boolean isSocial) {
        this.channelType = channelType;
        this.channelId = channelId;
        this.isVideo = isVideo;
        this.isPicture = isPicture;
        this.isSocial = isSocial;

    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }

    public Boolean getPicture() {
        return isPicture;
    }

    public void setPicture(Boolean picture) {
        isPicture = picture;
    }

    public Boolean getSocial() {
        return isSocial;
    }

    public void setSocial(Boolean social) {
        isSocial = social;
    }

    public Boolean getImplemented() {
        return isImplemented;
    }

    public void setImplemented(Boolean implemented) {
        isImplemented = implemented;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Long publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitleLocal() {
        return titleLocal;
    }

    public void setTitleLocal(String titleLocal) {
        this.titleLocal = titleLocal;
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getManual() {
        return isManual;
    }

    public void setManual(Boolean manual) {
        isManual = manual;
    }

    public String toString()
    {
        return "id:"+id+"#channelId:"+channelId+"#isVideo:"+isVideo+"#country:"+country+"#tite:"+title+"#description:"+description;
    }
}

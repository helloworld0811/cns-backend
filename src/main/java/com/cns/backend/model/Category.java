package com.cns.backend.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hku on 07.04.17.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable{


    private static final long serialVersionUID=  231241241212L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String categoryName="";

  private String channelCategoryName="";

  @Column(unique = true)
  private String channelCategoryId;

  private ChannelType channelType=null;

  @Column(nullable = true)
  private Long parentCategoryId=null;

  private Boolean isRoot=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getChannelCategoryName() {
        return channelCategoryName;
    }

    public void setChannelCategoryName(String channelCategoryName) {
        this.channelCategoryName = channelCategoryName;
    }

    public String getChannelCategoryId() {
        return channelCategoryId;
    }

    public void setChannelCategoryId(String channelCategoryId) {
        this.channelCategoryId = channelCategoryId;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Boolean getRoot() {
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }
}

package com.cns.backend.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hku on 04.04.17.
 */
@Entity
@Table(name = "performance_metrics")
public class PerformanceMetrics implements Serializable{

    private static final long serialVersionUID = 32143563L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long ownerChannelId;

    private Long timestamp;

    private Long viewsCount;

    private Long commentCount;

    private Long subscribeCount;

    private Long videoCount;

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(Long subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public Long getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Long videoCount) {
        this.videoCount = videoCount;
    }


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerChannelId() {
        return ownerChannelId;
    }

    public void setOwnerChannelId(Long ownerChannelId) {
        this.ownerChannelId = ownerChannelId;
    }
}
